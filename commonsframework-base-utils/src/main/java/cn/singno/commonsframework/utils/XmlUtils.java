package cn.singno.commonsframework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.singno.commonsframework.constants.DefaultSystemConst;


/**
 * <p>File：XmlUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午4:00:34</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class XmlUtils
{
	private static final Log loger = LogFactory.getLog(XmlUtils.class);

	/**
	 * 私有构造器
	 */
	private XmlUtils()
	{
		super();
	}

	/**
	 * 从包含xml文件内容的url取得Document对象
	 * 
	 * @param url 		url地址
	 * @return Document 	Document
	 */
	public static Document getAsXML(String url)
	{
		try
		{
			return getAsXML(new URL(url));
		} catch (MalformedURLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从URL取得Document
	 * 
	 * @param url 		URL
	 * @return Document 	Document
	 */
	public static Document getAsXML(URL url)
	{
		SAXReader reader = new SAXReader();
		try
		{
			return reader.read(url);
		} catch (DocumentException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将xml格式的字符串转化为Document
	 * 
	 * @param xmlAsString 		xml格式的字符串
	 * @return Document 		Document
	 */
	public static Document loadXML(String xmlAsString)
	{
		SAXReader reader = new SAXReader();
		StringReader sr = new StringReader(xmlAsString);
		try
		{
			return reader.read(sr);
		} catch (DocumentException e)
		{
			loger.warn("Could not parse: " + xmlAsString);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从File对象得到Document
	 * 
	 * @param file 		File
	 * @return Document 	Document
	 */
	public static Document getAsXML(File file)
	{
		try
		{
			SAXReader xmlReader = new SAXReader();
			return xmlReader.read(file);
		} catch (DocumentException e)
		{
			throw new RuntimeException(file.getAbsoluteFile() + " threw " + e);
		}
	}

	/**
	 * 将指定的xml文件转化为Document
	 * 
	 * @param filename 		文件名称
	 * @return Document 		Document
	 */
	public static Document loadXMLFile(String filename)
	{
		return loadXMLFile(new File(filename));
	}

	/**
	 * 将指定的xml文件转化为Document
	 * 
	 * @param file 		File对象
	 * @return Document 	Document
	 */
	public static Document loadXMLFile(File file)
	{
		SAXReader reader = new SAXReader();
		try
		{
			FileReader fr = new FileReader(file);
			return reader.read(fr);
		} catch (DocumentException e)
		{
			throw new RuntimeException(e);
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将Document写入文件
	 * 
	 * @param doc 			Document
	 * @param file  			File
	 * @throws IOException	IOException
	 */
	public static void write(Document doc, File file) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, DefaultSystemConst.DEFAULT_UNICODE);
		write(doc, osw);
	}

	/**
	 * 将Document
	 * 
	 * @param doc 			Document
	 * @param writer 			Writer
	 * @throws IOException 	IOException
	 */
	public static void write(Document doc, Writer writer)
			throws IOException
	{
		OutputFormat fo = OutputFormat.createPrettyPrint();
		fo.setEncoding(DefaultSystemConst.DEFAULT_UNICODE); // XML encoding
								// name
		fo.setIndent(true);
		fo.setIndent(true);
		fo.setTrimText(false);
		fo.setXHTML(true);
		XMLWriter xw = new XMLWriter(fo);
		xw.setWriter(writer);
		xw.write(doc);
		xw.flush();
		xw.close();
	}

	/**
	 * 格式化XML文档
	 * 
	 * @param document 	xml文档
	 * @param charset 	字符串的编码
	 * @param istrans 		是否对属性和元素值进行转换
	 * @return 格式化后XML字符串
	 */
	public static String formatXml(Document document, String charset,
			boolean istrans)
	{
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charset);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		xw.setEscapeText(istrans);
		try
		{
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (IOException e)
		{
			// System.out.println("格式化XML文档发生异常，请检查！");
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * 根据xml字符串进行格式化
	 * 
	 * @param xmlString 	xml字符串
	 * @param charset		字符编码
	 * @param istrans 		是否对属性和元素值进行转换
	 * @return String 		转换后的xml格式字符串
	 */
	public static String formatXml(String xmlString, String charset,
			boolean istrans)
	{
		Document document = loadXML(xmlString);
		return formatXml(document, charset, istrans);
	}

	/**
	 * 根据xml文件名称及元素名称
	 * 
	 * @param fileName 		xml文件路径名称
	 * @param elementName 	节点名称
	 * @return String 			节点值
	 */
	public static String getElementText(String fileName, String elementName)
	{
		File file = new File(fileName);
		return getElementText(file, elementName);
	}

	@SuppressWarnings("unchecked")
	public static String getElementText(File file, String elementName)
	{
		String elementText = null;
		Document document = loadXMLFile(file);
		Element rootElement = document.getRootElement();
		for (Iterator<Element> iterator = rootElement.elementIterator(); iterator
				.hasNext();)
		{
			Element element = (Element) iterator.next();
			if (element.getName().equals(elementName))
			{
				elementText = element.getTextTrim();
			}
		}
		return elementText;
	}
}
