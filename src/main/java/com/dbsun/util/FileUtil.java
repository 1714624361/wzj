package com.dbsun.util;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

/**
 * 文件工具类
 */
@SuppressWarnings("restriction")
public abstract class FileUtil {
	/**
	 * 把内容写入文件
	 *
	 * @param filePath
	 * @param fileContent
	 */
	public static void write(String filePath, String fileContent) {

		try {
			FileOutputStream fo = new FileOutputStream(filePath);
			OutputStreamWriter out = new OutputStreamWriter(fo, "UTF-8");

			out.write(fileContent);

			out.close();
		} catch (IOException ex) {

			System.err.println("Create File Error!");
			ex.printStackTrace();
		}
	}

	/**
	 * 读取文件内容 默认是UTF-8编码
	 *
	 * @param filePath
	 * @return
	 */
	public static String read(String filePath, String code) {
		if (code == null || code.equals("")) {
			code = "UTF-8";
		}
		String fileContent = "";
		File file = new File(filePath);
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), code);
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + "\n";
			}
			read.close();
			read = null;
			reader.close();
			read = null;
		} catch (Exception ex) {
			ex.printStackTrace();
			fileContent = "";
		}
		return fileContent;
	}

	/**
	 * 删除文件或文件夹
	 *
	 * @param filePath
	 */
	public static void delete(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				if (file.isDirectory()) {
					FileUtils.deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 判断该路径是否存在
	 *
	 * @param filepath
	 *            路径
	 * @return 存在true, 反之则false
	 */
	public static boolean exist(String filepath) {
		File file = new File(filepath);

		return file.exists();
	}

	/**
	 * 创建文件夹
	 *
	 * @param filePath
	 */
	public static void createFolder(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception ex) {
			System.err.println("Make Folder Error:" + ex.getMessage());
		}
	}
	/**
	 * 得到文件的扩展名
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {

		int potPos = fileName.lastIndexOf('.') + 1;
		String type = fileName.substring(potPos, fileName.length());
		return type;
	}

	/**
	 * 通过File对象创建文件
	 *
	 * @param file
	 * @param filePath
	 */
	public static void createFile(File file, String filePath) {
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			fileInputStream = new FileInputStream(file);
			byte[] by = new byte[1024];
			int c;
			while ((c = fileInputStream.read(by)) != -1) {
				outputStream.write(by, 0, c);
			}
			outputStream.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
	}

	/**
	 * 获取路径文件内的所有内容
	 *
	 * @param resource
	 *            路径
	 * @return 文件内的所有内容
	 */
	public static String readFile(String resource) {
		InputStream stream = getResourceAsStream(resource);
		String content = readStreamToString(stream);

		return content;

	}

	/**
	 * 根据文件路径获取InputStream
	 *
	 * @param resource
	 * @return
	 */
	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);

		}

		return stream;
	}


	/**
	 * 根据InputStream读取所有内容
	 *
	 * @param stream
	 *            InputStream实例
	 * @return 读取的所有内容
	 */
	public static String readStreamToString(InputStream stream) {
		String fileContent = "";

		try {
			InputStreamReader read = new InputStreamReader(stream, "utf-8");
			BufferedReader reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line + "\n";
			}
			read.close();
			read = null;
			reader.close();
			read = null;
		} catch (Exception ex) {
			fileContent = "";
		}
		return fileContent;
	}

	/**
	 * delete file folder
	 *
	 * @param path
	 */
	public static void removeFile(File path) {

		if (path.isDirectory()) {
			try {
				FileUtils.deleteDirectory(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 获取文件的MD5值
	 * @param file
	 * @return
	 */
	public static String md5(File file) {
		final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e','f' };
		try {
			double filesize = file.length();
			double byteSize = 8192.0D;
			InputStream ins = new FileInputStream(file);
			byte[] buffer = new byte[(int) byteSize];
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			double each = filesize / byteSize;
			int loops = (int) (each / 50.0D);
			int loopCount = 0;
			System.out.print("\n-------------------------------------------------- : 100%" + "\n");
			int len;
			while ((len = ins.read(buffer)) != -1) {
				md5.update(buffer, 0, len);
				if (loops != 0) {
					if (loopCount == loops) {
						System.out.print("-");
						loopCount = 0;
					} else {
						loopCount++;
					}
				}
			}
			if (loops != 0) {
				System.out.println("- : MD5ed");
			} else {
				System.out.println("-------------------------------------------------- : MD5ed");
			}
			ins.close();

			byte[] result = md5.digest();

			StringBuilder sb = new StringBuilder(result.length * 2);
			for (int i = 0; i < result.length; i++) {
				sb.append(hexChar[((result[i] & 0xF0) >>> 4)]);
				sb.append(hexChar[(result[i] & 0xF)]);
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}