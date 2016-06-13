package javacommon.util;

import java.io.*;

/**
 * 对象流工具类 。
 * 
 * @author wangzg
 * 
 */
public class ObjectStreamUtil
{

	/**
	 * 把对象转为字节数组 。
	 * 
	 * @param objObject
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToBytes(Object objObject) throws IOException
	{
		ByteArrayOutputStream objBaos = new ByteArrayOutputStream();
		ObjectOutputStream objOs = new ObjectOutputStream(objBaos);
		
		objOs.writeObject(objObject);

		// 返回结果
		return objBaos.toByteArray();
	}

	/**
	 * 把字节数组转化为对象 。
	 * 
	 * @param arrayBytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object bytesToObject(byte[] arrayBytes) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream objBais = new ByteArrayInputStream(arrayBytes);
		ObjectInputStream objIs = new ObjectInputStream(objBais);

		// 返回结果
		return objIs.readObject();
	}

}