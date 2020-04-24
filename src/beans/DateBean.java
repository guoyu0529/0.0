package beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateBean {
	private String dateTime; // ���ں�ʱ��
	private String week; // ����
	public String getDateTime() {
	// ��ȡ��ǰʱ��
	Date currentDate = Calendar.getInstance().getTime();
	// ʵ���� SimpleDateFormat
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy �� MM �� dd �� HH ʱ mm�� ss ��");
	// ��ʽ������ʱ��
	dateTime = sdf.format(currentDate);
	return dateTime;
	}
	public String getWeek() {
	// ������������
	String[] weeks = { "������", "����һ", "���ڶ�", "������", "������", "������",
	"������" };
	// ��ȡһ���ڵ�ĳ��
	int index = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	week = weeks[index - 1];
	return week;
	}
}
