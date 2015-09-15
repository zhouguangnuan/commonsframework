package cn.singno.commonsframework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;

/**
 * <p>File：IpaUtils.java</p>
 * <p>Title: ipa文件解析，获取版本等信息</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-4 下午3:25:07</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class IpaUtils
{
    private static final Logger logger     = Logger.getLogger(IpaUtils.class);

    private static final int    OUT_BUFFER = 2048;

    private static final String FILE_NAME  = "info.plist";

    private static final String FILE_EXTS  = ".xml";

    private IpaUtils()
    {
        super();
    }

    /*public static void main(String[] args) throws Exception
    {
         String infoFileName=unZipInfoPlist("D:/vMEyeCloud.ipa", "d:/");
         System.out.println(infoFileName);
         analyseInfoPlist(infoFileName);
    }*/

    /**
     * 从IPA文件中解压缩Info.pist文件到指定目录
     * @param ipaFile ipa文件路径及名称
     * @param filePath 解压缩目录
     */
    public static String unZipInfoPlist(String ipaFile, String filePath)
    {
        File file = new File(ipaFile);
        return unZipInfoPlist(file, filePath);
    }

    /**
     * 从IPA文件中解压缩Info.pist文件到指定目录
     * @param ipaFile ipa文件
     * @param filePath 解压缩目录
     * @return String 解压后的文件名称
     */
    public static String unZipInfoPlist(File ipaFile, String filePath)
    {
        String infoFileName = null;
        InputStream inputStream = null;
        ZipInputStream zipInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try
        {
            inputStream = new BufferedInputStream(new FileInputStream(ipaFile));
            zipInputStream = new ZipInputStream(inputStream);
            ZipEntry ze;
            while ((ze = zipInputStream.getNextEntry()) != null)
            {
                if (!ze.isDirectory())
                {
                    String infoName = FilenameUtils.getName(ze.getName())
                            .toLowerCase();
                    if (infoName.equals(FILE_NAME))
                    {
                        int count;
                        byte data[] = new byte[OUT_BUFFER];
                        infoFileName = filePath + infoName;
                        FileOutputStream fos = new FileOutputStream(
                                infoFileName);
                        bufferedOutputStream = new BufferedOutputStream(fos,
                                OUT_BUFFER);
                        while ((count = zipInputStream
                                .read(data, 0, OUT_BUFFER)) != -1)
                        {
                            bufferedOutputStream.write(data, 0, count);
                        }
                        logger.info("file - " + ze.getName() + " : "
                                + ze.getSize() + " bytes");
                        break;
                    }
                }
            }
            zipInputStream.closeEntry();
        }
        catch (FileNotFoundException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(zipInputStream);
            IOUtils.closeQuietly(bufferedOutputStream);
        }
        return infoFileName;
    }

    public static void analyseInfoPlist(String enFile)
    {
        File inFile = new File(enFile);
        File outFile = new File(enFile+FILE_EXTS);
        analyseInfoPlist(inFile, outFile);
    }

    public static void analyseInfoPlist(File enFile, File deFile)
    {
        try
        {
            PropertyListParser.convertToXml(enFile, deFile);
        }
        catch (ParserConfigurationException e)
        {
            logger.error(e);
        }
        catch (ParseException e)
        {
            logger.error(e);
        }
        catch (SAXException e)
        {
            logger.error(e);
        }
        catch (PropertyListFormatException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
        }
    }
}
