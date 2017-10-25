package press.wein.home.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateNumber {
	private static long orderNum = 0l;
	private static String date;
	private static long FileNum = 0l;
	private static String fielDate;
	private static String MACHINE_ID = "1";
	/**
	 * 
	 * @Description：生成订单编号
	 * @return
	 */
	public static synchronized String getOrderNo() {
		String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum++;
		Long orderNo = Long.parseLong((date)) * 100000;
		orderNo += orderNum;

		return new StringBuilder(MACHINE_ID).append(orderNo.toString()).toString();
	}

	/**
	 * 
	 * @Description：生成上传到服务器上的文件名
	 * @return
	 */
	public static synchronized String getNewFileName() {
		String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		if (fielDate == null || !fielDate.equals(str)) {
			fielDate = str;
			FileNum = 0l;
		}
		FileNum++;
		long orderNo = Long.parseLong((fielDate)) * 10000;
		orderNo += FileNum;
		return orderNo + "";
	}

	public static void main(String[] args) {
//		for (int i = 0; i < 100000; i++) {
//			System.out.println(getNewFileName());
//		}

		String str = getNewFileName();
		System.out.println(str);
		System.out.println(str.substring(0,4));
		System.out.println(str.substring(4,6));
		System.out.println(str.substring(6,8));
	}
}
