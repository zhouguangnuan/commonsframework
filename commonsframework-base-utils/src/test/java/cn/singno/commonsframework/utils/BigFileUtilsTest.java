/**
 * <p>项目名：</p>
 * <p>包名：cn.singno.commonsframework.utils</p>
 * <p>文件名：StringUtils.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-9-下午1:03:15</p>
 * Copyright (c) 2015singno公司-版权所有
 */
package cn.singno.commonsframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * <p>名称：BigFileUtilsTest.java</p>
 * <p>描述：</p>
 * <pre>
 *    
 * </pre>
 * @author 周光暖
 * @date 2016年8月7日 上午11:28:05
 * @version 1.0.0
 */
public class BigFileUtilsTest
{
	@Test
	public void testName() throws Exception
	{
		String mergeFileName = "C:/Users/Administrator/Desktop/parts/X战警：天启.mkv";
		String mergeFileName2 = "C:/Users/Administrator/Desktop/parts/新建文件夹/X战警：天启.mkv";
		
		String[] extensions = new String[]{"part"};
		File directory = FileUtils.getFile("C:/Users/Administrator/Desktop/parts");
		
		long startTimes = System.currentTimeMillis();
		
		ArrayList<File> list = new ArrayList(FileUtils.listFiles(directory, extensions, Boolean.FALSE));
		Collections.sort(list, new Comparator<File>() {
			public int compare(File f1, File f2) {
				String[] s1 = StringUtils.split(f1.getName(), ".");
				Integer no1 = NumberUtils.stringToInt(s1[s1.length-2], 0);
				String[] s2 = StringUtils.split(f2.getName(), ".");
				Integer no2 = NumberUtils.stringToInt(s2[s2.length-2], 0);
				return no1 - no2;
			}
		});

		for(int i=0; i<list.size(); i++){
			long startPos = i * list.get(0).length();
			RandomAccessFile rFile = new RandomAccessFile(mergeFileName, "rw");
			rFile.seek(startPos);
			FileInputStream fs = new FileInputStream(list.get(i));
			byte[] b = new byte[fs.available()];
			fs.read(b);
			fs.close();
			rFile.write(b);
			rFile.close();
		}
		
		FileUtils.moveFile(new File(mergeFileName), new File(mergeFileName2));
		
		long endTimes = System.currentTimeMillis();
		System.out.println("耗时：" + (endTimes - startTimes));
	}
	
	@Test
	public void testName2() throws Exception
	{
		final String mergeFileName = "C:/Users/Administrator/Desktop/parts/X战警：天启.mkv";
		
		String[] extensions = new String[]{"part"};
		File directory = FileUtils.getFile("C:/Users/Administrator/Desktop/parts");
		
		long startTimes = System.currentTimeMillis();
		
		ArrayList<File> list = new ArrayList(FileUtils.listFiles(directory, extensions, Boolean.FALSE));
		Collections.sort(list, new Comparator<File>() {
			public int compare(File f1, File f2) {
				String[] s1 = StringUtils.split(f1.getName(), ".");
				Integer no1 = NumberUtils.stringToInt(s1[s1.length-2], 0);
				String[] s2 = StringUtils.split(f2.getName(), ".");
				Integer no2 = NumberUtils.stringToInt(s2[s2.length-2], 0);
				return no1 - no2;
			}
		});

		int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 1;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(list.size() * 2);
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
				corePoolSize, 
				maximumPoolSize, 
				keepAliveTime,
				TimeUnit.SECONDS, 
				workQueue);

		for(int i=0; i<list.size(); i++){
			final long startPos = i * list.get(0).length();
			final File part = list.get(i);
			
			threadPool.execute(new Runnable() {
				public void run() {
					RandomAccessFile rFile = null;
					try {
						rFile = new RandomAccessFile(mergeFileName, "rw");
						rFile.seek(startPos);
						FileInputStream fs = new FileInputStream(part);
						byte[] b = new byte[fs.available()];
						fs.read(b);
						fs.close();
						rFile.write(b);
						rFile.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		long endTimes = System.currentTimeMillis();
		System.out.println("耗时：" + (endTimes - startTimes));
	}
	
	@Test
	public void testName3() throws Exception {
		String[] extensions = new String[]{"part"};
		File directory = FileUtils.getFile("C:/Users/Administrator/Desktop/parts");
		
		ArrayList<File> list = new ArrayList(FileUtils.listFiles(directory, extensions, Boolean.FALSE));
		Collections.sort(list, new Comparator<File>() {
			public int compare(File f1, File f2) {
				String[] s1 = StringUtils.split(f1.getName(), ".");
				Integer no1 = NumberUtils.stringToInt(s1[s1.length-2], 0);
				String[] s2 = StringUtils.split(f2.getName(), ".");
				Integer no2 = NumberUtils.stringToInt(s2[s2.length-2], 0);
				return no1 - no2;
			}
		});
		
		System.out.println("======================================");
		for(File f : list){
			System.out.println(f.length());
			System.out.println(f.getName());
		}
		System.out.println("======================================");
	}
	
}
