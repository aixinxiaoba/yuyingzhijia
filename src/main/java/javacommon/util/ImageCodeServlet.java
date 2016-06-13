package javacommon.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javacommon.core.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成验证码的Servlet 。
 * 
 * @author wangzg
 * 
 */
public class ImageCodeServlet extends HttpServlet
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = -8162038003202063106L;

	/**
	 * 随机的字符串。
	 */
	private String[] arrayRandom = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "3", "4", "5", "6", "7", "8", "9" };

	/**
	 * doGet。
	 */
	@Override
	protected void doGet(HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException
	{
		int nWidth = 130;
		int nHeight = 50;
		BufferedImage objImage;
		Graphics objGraphics;
		Random objRan;
		StringBuffer sbufSb = new StringBuffer();// 最终生成验证码信息
		Font objFont = new Font("宋体", Font.BOLD, 36);// 设置字体 Font(String name,int style,int size)指定名称、样式和磅值大小
		int[] arrayWid = { 15, 45, 75, 105 }; // 定义验证码位置
		int[] arrayHei = { 35, 40, 30, 28 }; // 定义验证码位置
		
		objResponse.setContentType("image/jpeg");// 设置响应格式
		
		objImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);// 创建图片内存对象 给定长，宽 和格式
		objGraphics = objImage.createGraphics();// 创建画笔
		objRan = new Random();
		
		objGraphics.setColor(Color.gray.brighter());// 先给画笔设置颜色
		objGraphics.fillRect(0, 0, nWidth, nHeight);// 画矩形
		objGraphics.setFont(objFont);
		
		// 绘制四位验证码
		for (int i = 0; i < arrayWid.length; i++)
		{
			String str = arrayRandom[objRan.nextInt(arrayRandom.length)];
			
			sbufSb.append(str);
			objGraphics.setColor(Color.black);
			objGraphics.drawString(str, arrayWid[i], arrayHei[i]);
		}

		// 把生成的验证码放到Session中，登陆时做验证用
		objRequest.getSession().setAttribute(Config.objCOMConfig.getProperty("validateCode"), sbufSb.toString().trim());

		// 绘制干扰线
		for (int i = 0; i < 6; i++)
		{
			objGraphics.setColor(new Color(objRan.nextInt(256), objRan.nextInt(256), objRan.nextInt(256)));
			objGraphics.drawLine(objRan.nextInt(nWidth), objRan.nextInt(nHeight), objRan.nextInt(nWidth), objRan.nextInt(nHeight));
		}

		try
		{
			// 获得输出流
			OutputStream objOs = objResponse.getOutputStream();

			// 将位图转为jpeg 格式传输
			// 使用JPEGImageEncoder 可以一边转换一边输出 吧输出流传入
			JPEGImageEncoder objEncode = JPEGCodec.createJPEGEncoder(objOs);

			// 把BufferedImage对象中的图像信息编码后
			// 向创建该对象(encoder)时指定的输出流输出
			objEncode.encode(objImage);
		}
		catch (IOException e)
		{
			// 暂时不输出错误信息
		}

	}

	/**
	 * doPost。
	 */
	@Override
	protected void doPost(HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		this.doGet(objRequest, objResponse);
	}

}
