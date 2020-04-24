package com.one.stu1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {
	private int w = 150; // ���
	private int h = 70; // �߶�
	private Random r = new Random(); // ���һ�����������
	// ��������Щ����
	private String[] fontNames = { "����", "���Ŀ���", "����", "΢���ź�", "����_GB2312" };

	// ��������Щ��֤�������ַ�
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ";
	// ���ɱ���ɫ
	private Color bgColor = new Color(250, 250, 250);
	// ���ɵ���֤���ı�
	private String text;
	// ���������ɫ
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red, green, blue);
	}
// �����������
		private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index]; // ��������
		int style = r.nextInt(4); // ������ʽ(PLAIN=0,BOLD=1,ITALIC=2,BOLD|ITALIC=3)
		int size = r.nextInt(5) + 50; // �����С(50-54 ֮��)
		return new Font(fontName, style, size);
		}
// ��������
		private void drawLine(BufferedImage image) {
			int num = 3; // ����������
			Graphics2D g2 = (Graphics2D) image.getGraphics(); // �õ�ͼƬ����
	for (int i = 0; i < num; i++) {
		int x1 = r.nextInt(w); // ��� x ����
		int y1 = r.nextInt(h); // ��� y ����
		int x2 = r.nextInt(w); // �յ� x ����
		int y2 = r.nextInt(h); // �յ� y ����
	g2.setStroke(new BasicStroke(1.5F));// ��������������1.5F Ϊ�ߵĿ��
	g2.setColor(Color.BLUE); // ��������ɫ
	g2.drawLine(x1, y1, x2, y2); // ����
			}
		}
// �õ� codes �ĳ����ڵ����������ʹ�� charAt ȡ�������λ���ϵ� codes �е��ַ�
		private char randomChar() {
			int index = r.nextInt(codes.length());
			return codes.charAt(index);
		}
// ����һ����֤���ͼƬ
public BufferedImage createImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics(); // �õ�ͼƬ����
		g2.setColor(bgColor); // ������������ɫ
		g2.fillRect(0, 0, w, h); // ʹ�û������ָ���ľ���
		g2.setColor(Color.RED); // ������������ɫ
		g2.drawRect(0, 0, w - 1, h - 1); // ʹ�û��ʻ���ָ���ľ���
		StringBuilder sb = new StringBuilder();
		// ��ͼ�л��ĸ��ַ�
		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
			float x = i * 1.0F * w / 4; // �����ַ� x ����λ��
			g2.setFont(randomFont()); // ���û�������
			g2.setColor(randomColor()); // ���û�����ɫ
			g2.drawString(s, x, h - 20); // ��ͼƬ��д�ַ�
		}
			text = sb.toString();
			drawLine(image); // ���Ƹ�����
			return image;// ����ͼƬ
		}
// �õ���֤����ı����������������û��������֤�룬�����
public String getText() {
	return text;
}
// ��������Ķ��������ļ���
public static void output(BufferedImage bi, OutputStream output) throws IOException {
	ImageIO.write(bi, "JPEG", output);
	}
}
