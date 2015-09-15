package cn.singno.commonsframework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 名称：CompressUtils.java<br>
 * 描述：压缩/解压缩处理工具类，可进一步抽象和重构，暂时未加编码<br>
 * <pre></pre>
 * @author 周光暖
 * @date 2015-3-17 上午9:00:19
 * @version 1.0.0
 */
public class CompressUtils
{
    private static final int BUFFERED_SIZE = 1024;

    private static final Log loger         = LogFactory.getLog(CompressUtils.class);

    /**
     * 私有构造器
     */
    private CompressUtils()
    {
        super();
    }

    /**
     * 将文件按gzip算法进行压缩<单文件压缩>
     * @param srcFile 要进行压缩的文件
     * @param destFile 压缩后的目标文件
     * @throws IOException IOException
     */
    public static void gzipCompress(String srcFile, String destFile)
            throws IOException
    {
        OutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile), BUFFERED_SIZE);
            out = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), BUFFERED_SIZE));
            IOUtils.copy(is, out);
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将文件按gzip算法进行压缩<单文件压缩>
     * @param srcFile 要进行压缩的文件
     * @param destFile 压缩后的目标文件
     * @throws IOException IOException
     */
    public static void gzipCompress(File srcFile, File destFile) throws IOException
    {
        OutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile), BUFFERED_SIZE);
            out = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), BUFFERED_SIZE));
            IOUtils.copy(is, out);
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将按gzip算法压缩的文件进行解压缩
     * @param srcFile 要解压缩的文件
     * @param destDir 目标目录
     * @throws IOException IOException
     */
    public static void gzipUnCompress(File srcFile, File destDir) throws IOException
    {
        InputStream is = null;
        OutputStream os = null;
        try
        {
            File destFile = new File(destDir, FilenameUtils.getBaseName(srcFile.toString()));
            is = new GzipCompressorInputStream(new BufferedInputStream(new FileInputStream(srcFile), BUFFERED_SIZE));
            os = new BufferedOutputStream(new FileOutputStream(destFile),BUFFERED_SIZE);
            IOUtils.copy(is, os);
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 将文件按BZIP2算法对文件进行压缩<单文件压缩>
     * @param srcFile 要压缩的文件
     * @param destFile 目标文件
     * @throws IOException IOException
     */
    public static void bzip2Compress(String srcFile, String destFile) throws IOException
    {
        OutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile), BUFFERED_SIZE);
            out = new BZip2CompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), BUFFERED_SIZE));
            IOUtils.copy(is, out);
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将文件按BZIP2算法进行解压缩
     * @param srcFile 要解压的文件
     * @param destDir 目标目录
     * @throws IOException IOException
     */
    public static void bzip2UpCompress(File srcFile, File destDir) throws IOException
    {
        InputStream is = null;
        OutputStream os = null;
        try
        {
            File destFile = new File(destDir, FilenameUtils.getBaseName(srcFile.toString()));
            is = new BZip2CompressorInputStream(new BufferedInputStream(
                    new FileInputStream(srcFile), BUFFERED_SIZE));
            os = new BufferedOutputStream(new FileOutputStream(destFile),
                    BUFFERED_SIZE);
            IOUtils.copy(is, os);
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 将多个文件按zip算法进行压缩
     * @param files 文件数组
     * @param destFile 压缩后的目标文件
     */
    public static void zipMutiCompress(File[] srcfile, File destFile)
    {
        byte[] buf = new byte[BUFFERED_SIZE];
        try
        {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                    destFile));
            if (null != srcfile && srcfile.length > 0)
            {
                for (int i = 0; i < srcfile.length; i++)
                {
                    File file = srcfile[i];
                    if (null != file)
                    {
                        FileInputStream in = new FileInputStream(file);
                        out.putNextEntry(new ZipEntry(file.getName()));
                        int len;
                        while ((len = in.read(buf)) > 0)
                        {
                            out.write(buf, 0, len);
                        }
                        out.closeEntry();
                        in.close();
                    }
                }
            }
            out.close();
        }
        catch (IOException e)
        {
            loger.error(e);
        }
    }

    /**
     * 将文件按zip算法进行压缩
     * @param srcFile 要压缩的文件
     * @param destFile 压缩后的目标文件
     */
    public static void zipCompress(File srcFile, File destFile)
    {
        ZipArchiveOutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile),
                    BUFFERED_SIZE);
            out = new ZipArchiveOutputStream(new BufferedOutputStream(
                    new FileOutputStream(destFile), BUFFERED_SIZE));
            ZipArchiveEntry entry = new ZipArchiveEntry(srcFile.getName());
            entry.setSize(srcFile.length());
            out.putArchiveEntry(entry);
            IOUtils.copy(is, out);
            out.closeArchiveEntry();
        }
        catch (FileNotFoundException e)
        {
            loger.error(e);
        }
        catch (IOException e)
        {
            loger.error(e);
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将文件按zip算法进行解压缩
     * @param srcFile 要解压的文件
     * @param destDir 解压后的文件存放路径
     */
    public static void zipUnCompress(File srcFile, File destDir)
    {
        ZipArchiveInputStream is = null;
        try
        {
            is = new ZipArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(srcFile), BUFFERED_SIZE));
            ZipArchiveEntry entry = null;
            while ((entry = is.getNextZipEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                }
                else
                {
                    OutputStream os = null;
                    try
                    {
                        os = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFERED_SIZE);
                        IOUtils.copy(is, os);
                    }
                    finally
                    {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            loger.error(e);
        }
        catch (IOException e)
        {
            loger.error(e);
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * 将文件按ar算法进行压缩
     * @param srcFile 要压缩的文件
     * @param destFile 压缩后的目标文件
     * @throws IOException IOException
     */
    public static void arCompress(File srcFile, File destFile)
            throws IOException
    {
        ArArchiveOutputStream zout = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile),
                    BUFFERED_SIZE);
            zout = new ArArchiveOutputStream(new BufferedOutputStream(
                    new FileOutputStream(destFile), BUFFERED_SIZE));
            zout.putArchiveEntry(new ArArchiveEntry(srcFile, srcFile.getName()));
            IOUtils.copy(is, zout);
            zout.closeArchiveEntry();
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(zout);
        }
    }

    /**
     * 将文件按ar算法进行压缩
     * @param srcFile 要压缩的文件
     * @param destDir 压缩后的目标文件
     * @throws IOException IOException
     */
    public static void arUnCompress(File srcFile, File destDir)
            throws IOException
    {
        ArArchiveInputStream is = null;
        try
        {
            is = new ArArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(srcFile), BUFFERED_SIZE));
            ArArchiveEntry entry = null;
            while ((entry = is.getNextArEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                }
                else
                {
                    BufferedOutputStream bos = null;
                    try
                    {
                        byte[] buffer = new byte[BUFFERED_SIZE];
                        bos = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFERED_SIZE);
                        int len;
                        long size = entry.getSize();
                        while (size > 0)
                        {
                            if (size < BUFFERED_SIZE)
                            {
                                len = is.read(buffer, 0, (int) size);
                                size -= len;
                            }
                            else
                            {
                                len = is.read(buffer);
                                size -= len;
                            }
                            bos.write(buffer, 0, len);
                        }
                    }
                    finally
                    {
                        IOUtils.closeQuietly(bos);
                    }
                }
            }
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * 将文件按cpio算法进行压缩
     * @param srcFile 要压缩的文件
     * @param destFile 压缩后的目标文件
     * @throws IOException IOException
     */
    public static void cpioCompress(File srcFile, File destFile)
            throws IOException
    {
        CpioArchiveOutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile),
                    BUFFERED_SIZE);
            out = new CpioArchiveOutputStream(new BufferedOutputStream(
                    new FileOutputStream(destFile), BUFFERED_SIZE));
            out.putArchiveEntry(new CpioArchiveEntry(srcFile, srcFile.getName()));
            IOUtils.copy(is, out);
            out.closeArchiveEntry();
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将文件按照cpio算法进行解压缩
     * @param srcFile 要解压缩的文件
     * @param destDir 解压缩后文件的存放目录
     * @throws IOException IOException
     */
    public static void cpioUnCompress(File srcFile, File destDir)
            throws IOException
    {
        CpioArchiveInputStream is = null;
        try
        {
            is = new CpioArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(srcFile), BUFFERED_SIZE));
            CpioArchiveEntry entry = null;
            while ((entry = is.getNextCPIOEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                }
                else
                {
                    BufferedOutputStream bos = null;
                    try
                    {
                        byte[] buffer = new byte[BUFFERED_SIZE];
                        bos = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFERED_SIZE);
                        int len;
                        long size = entry.getSize();
                        while (size > 0)
                        {
                            if (size < BUFFERED_SIZE)
                            {
                                len = is.read(buffer, 0, (int) size);
                                size -= len;
                            }
                            else
                            {
                                len = is.read(buffer);
                                size -= len;
                            }
                            bos.write(buffer, 0, len);
                        }
                    }
                    finally
                    {
                        IOUtils.closeQuietly(bos);
                    }
                }
            }
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * 将文件按照tar算法进行压缩
     * @param srcFile 要压缩的文件
     * @param destFile 压缩后的目标文件
     * @throws IOException IOException
     */
    public static void tarCompress(File srcFile, File destFile)
            throws IOException
    {
        TarArchiveOutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile),
                    BUFFERED_SIZE);
            out = new TarArchiveOutputStream(new BufferedOutputStream(
                    new FileOutputStream(destFile), BUFFERED_SIZE));
            TarArchiveEntry entry = new TarArchiveEntry(srcFile.getName());
            entry.setSize(srcFile.length());
            out.putArchiveEntry(entry);
            IOUtils.copy(is, out);
            out.closeArchiveEntry();
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将文件按照tar算法进行解压缩
     * @param srcFile 要解压缩的文件
     * @param destDir 解压缩后的文件存放目录
     * @throws IOException IOException
     */
    public static void tarUnCompress(File srcFile, File destDir)
            throws IOException
    {
        TarArchiveInputStream is = null;
        try
        {
            is = new TarArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(srcFile), BUFFERED_SIZE));
            TarArchiveEntry entry = null;
            while ((entry = is.getNextTarEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                }
                else
                {
                    BufferedOutputStream bos = null;
                    try
                    {
                        byte[] buffer = new byte[BUFFERED_SIZE];
                        bos = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFERED_SIZE);
                        int len;
                        long size = entry.getSize();
                        while (size > 0)
                        {
                            if (size < BUFFERED_SIZE)
                            {
                                len = is.read(buffer, 0, (int) size);
                                size -= len;
                            }
                            else
                            {
                                len = is.read(buffer);
                                size -= len;
                            }
                            bos.write(buffer, 0, len);
                        }
                    }
                    finally
                    {
                        IOUtils.closeQuietly(bos);
                    }
                }
            }
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * 将文件按照jar算法进行压缩
     * @param srcFile 要压缩的文件
     * @param destFile 压缩后的目标文件
     * @throws IOException IOExcetpion
     * @deprecated 该方法不推荐使用，经测试，压缩后文件正常，但不能用jarUnCompress正确解压缩
     */
    public static void jarCompress(File srcFile, File destFile)
            throws IOException
    {
        JarArchiveOutputStream out = null;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(srcFile),
                    BUFFERED_SIZE);
            out = new JarArchiveOutputStream(new BufferedOutputStream(
                    new FileOutputStream(destFile), BUFFERED_SIZE));
            JarArchiveEntry entry = new JarArchiveEntry(srcFile.getName());
            entry.setSize(srcFile.length());
            out.putArchiveEntry(entry);
            out.flush();
            IOUtils.copy(is, out);
            out.closeArchiveEntry();
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 将文件按照jar算法进行解压缩
     * @param srcFile 要解压缩的文件
     * @param destDir 解压缩后的文件存放目录
     * @throws IOException IOException
     */
    public static void jarUnCompress(File srcFile, File destDir)
            throws IOException
    {
        JarArchiveInputStream is = null;
        try
        {
            is = new JarArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(srcFile), BUFFERED_SIZE));
            JarArchiveEntry entry = null;
            while ((entry = is.getNextJarEntry()) != null)
            {
                if (entry.isDirectory())
                {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                }
                else
                {
                    BufferedOutputStream bos = null;
                    try
                    {
                        byte[] buffer = new byte[BUFFERED_SIZE];
                        bos = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFERED_SIZE);
                        int len;
                        long size = entry.getSize();
                        while (size > 0)
                        {
                            if (size < BUFFERED_SIZE)
                            {
                                len = is.read(buffer, 0, (int) size);
                                size -= len;
                            }
                            else
                            {
                                len = is.read(buffer);
                                size -= len;
                            }
                            bos.write(buffer, 0, len);
                        }
                    }
                    finally
                    {
                        IOUtils.closeQuietly(bos);
                    }
                }
            }
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    /**  
     * 压缩  
     *   
     * @param data  待压缩数据  
     * @return byte[] 压缩后的数据  
     */
    public static byte[] zlibCompress(byte[] data)
    {
        byte[] output = new byte[0];
        Deflater compresser = new Deflater();
        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try
        {
            byte[] buf = new byte[1024];
            while (!compresser.finished())
            {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        }
        catch (Exception e)
        {
            output = data;
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(bos);
        }
        compresser.end();
        return output;
    }

    /**  
     * 压缩  
     *   
     * @param data  待压缩数据  
     * @param os   输出流  
     */
    public static void zlibCompress(byte[] data, OutputStream os)
    {
        DeflaterOutputStream dos = new DeflaterOutputStream(os);
        try
        {
            dos.write(data, 0, data.length);
            dos.finish();
            dos.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**  
     * 解压缩  
     *   
     * @param data 待压缩的数据  
     * @return byte[] 解压缩后的数据  
     */
    public static byte[] zlibUnCompress(byte[] data)
    {
        byte[] output = new byte[0];
        Inflater decompresser = new Inflater();
        decompresser.reset();
        decompresser.setInput(data);
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try
        {
            byte[] buf = new byte[1024];
            while (!decompresser.finished())
            {
                int i = decompresser.inflate(buf);
                o.write(buf, 0, i);
            }
            output = o.toByteArray();
        }
        catch (Exception e)
        {
            output = data;
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(o);
        }
        decompresser.end();
        return output;
    }

    /**  
     * 解压缩  
     *   
     * @param is		输入流  
     * @return byte[] 	解压缩后的数据  
     */
    public static byte[] zlibUnCompress(InputStream is)
    {
        InflaterInputStream iis = new InflaterInputStream(is);
        ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
        try
        {
            int i = 1024;
            byte[] buf = new byte[i];
            while ((i = iis.read(buf, 0, i)) > 0)
            {
                o.write(buf, 0, i);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return o.toByteArray();
    }
}
