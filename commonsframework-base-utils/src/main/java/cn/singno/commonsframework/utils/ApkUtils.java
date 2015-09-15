/*
 * @(#)ApkUtils.java 2014-5-4 下午1:25:37
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.singno.commonsframework.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.xmlpull.v1.XmlPullParser;

import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

/**
 * <p>File：ApkUtils.java</p>
 * <p>Title: Android文件解析，获取包名，版本号，版本名称等信息</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午4:00:47</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class ApkUtils
{
    private static final Log    loger       = LogFactory.getLog(ApkUtils.class);

    private static final String DEFAULT_XML = "AndroidManifest.xml";
    
    /**
     * 根据APK文件名称及属性名称，取得
     * @param apkPath APK文件路径及名称
     * @param elementName 属性名称
     * @return String 属性值
     */
    public static String getElement(String apkPath,String elementName)
    {
        String element = "";
        if (StringUtils.isNotBlank(apkPath))
        {
            String xmlString = getManifestXMLFromAPK(apkPath);
            if (StringUtils.isNotBlank(xmlString))
            {
                Document document = XmlUtils.loadXML(xmlString);
                if (null != document)
                {
                    Element root = document.getRootElement();
                    if (null != root)
                        element = root.attributeValue(elementName);
                }
            }
        }
        return element;
    }

    // 解析APKxml文件并转换为String
    private static String getManifestXMLFromAPK(String apkPath)
    {
        ZipFile file = null;
        StringBuilder xmlSb = new StringBuilder(100);
        InputStream fileInputStream = null;
        AXmlResourceParser parser = new AXmlResourceParser();
        try
        {
            File apkFile = new File(apkPath);
            file = new ZipFile(apkFile, ZipFile.OPEN_READ);
            ZipEntry entry = file.getEntry(DEFAULT_XML);
            fileInputStream = file.getInputStream(entry);
            parser.open(fileInputStream);
            StringBuilder sb = new StringBuilder(10);
            final String indentStep = "";
            int type;
            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT)
            {
                switch (type)
                {
                    case XmlPullParser.START_DOCUMENT:
                    {
                        log(xmlSb, "<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        break;
                    }
                    case XmlPullParser.START_TAG:
                    {
                        log(false, xmlSb, "%s<%s%s", sb,
                                getNamespacePrefix(parser.getPrefix()),
                                parser.getName());
                        sb.append(indentStep);
                        int namespaceCountBefore = parser
                                .getNamespaceCount(parser.getDepth() - 1);
                        int namespaceCount = parser.getNamespaceCount(parser
                                .getDepth());
                        for (int i = namespaceCountBefore; i != namespaceCount; ++i)
                        {
                            log(xmlSb, "%sxmlns:%s=\"%s\"",
                                    i == namespaceCountBefore ? "  " : sb,
                                    parser.getNamespacePrefix(i),
                                    parser.getNamespaceUri(i));
                        }
                        for (int i = 0, size = parser.getAttributeCount(); i != size; ++i)
                        {
                            log(false, xmlSb, "%s%s%s=\"%s\"", " ",
                                    getNamespacePrefix(parser
                                            .getAttributePrefix(i)),
                                    parser.getAttributeName(i),
                                    getAttributeValue(parser, i));
                        }
                        // log("%s>",sb);
                        log(xmlSb, ">");
                        break;
                    }
                    case XmlPullParser.END_TAG:
                    {
                        sb.setLength(sb.length() - indentStep.length());
                        log(xmlSb, "%s</%s%s>", sb,
                                getNamespacePrefix(parser.getPrefix()),
                                parser.getName());
                        break;
                    }
                    case XmlPullParser.TEXT:
                    {
                        log(xmlSb, "%s%s", sb, parser.getText());
                        break;
                    }
                }
            }
           
        }
        catch (Exception e)
        {
            loger.error(e);
        }finally{
        	 parser.close();
        	 if (fileInputStream!=null) {
 				try {
					fileInputStream.close();
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
 			}
        }
        return xmlSb.toString();
    }

    private static String getNamespacePrefix(String prefix)
    {
        if (prefix == null || prefix.length() == 0)
        {
            return "";
        }
        return prefix + ":";
    }

    private static String getAttributeValue(AXmlResourceParser parser, int index)
    {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        if (type == TypedValue.TYPE_STRING)
        {
            return parser.getAttributeValue(index);
        }
        if (type == TypedValue.TYPE_ATTRIBUTE)
        {
            return String.format("?%s%08X", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_REFERENCE)
        {
            return String.format("@%s%08X", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_FLOAT)
        {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type == TypedValue.TYPE_INT_HEX)
        {
            return String.format("0x%08X", data);
        }
        if (type == TypedValue.TYPE_INT_BOOLEAN)
        {
            return data != 0 ? "true" : "false";
        }
        if (type == TypedValue.TYPE_DIMENSION)
        {
            return Float.toString(complexToFloat(data))
                    + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type == TypedValue.TYPE_FRACTION)
        {
            return Float.toString(complexToFloat(data))
                    + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type >= TypedValue.TYPE_FIRST_COLOR_INT
                && type <= TypedValue.TYPE_LAST_COLOR_INT)
        {
            return String.format("#%08X", data);
        }
        if (type >= TypedValue.TYPE_FIRST_INT
                && type <= TypedValue.TYPE_LAST_INT)
        {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0x%02X>", data, type);
    }

    private static String getPackage(int id)
    {
        if (id >>> 24 == 1)
        {
            return "android:";
        }
        return "";
    }

    private static float complexToFloat(int complex)
    {
        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
    }

    private static final float  RADIX_MULTS[]     = {0.00390625F,
            3.051758E-005F, 1.192093E-007F, 4.656613E-010F};

    private static final String DIMENSION_UNITS[] = {"px", "dip", "sp", "pt",
            "in", "mm", "", ""                    };

    private static final String FRACTION_UNITS[]  = {"%", "%p", "", "", "", "",
            "", ""                                };

    private static void log(StringBuilder xmlSb, String format,
            Object ... arguments)
    {
        log(true, xmlSb, format, arguments);
    }

    private static void log(boolean newLine, StringBuilder xmlSb,
            String format, Object ... arguments)
    {
        xmlSb.append(String.format(format, arguments));
        if (newLine)
            xmlSb.append("\n");
    }
}
