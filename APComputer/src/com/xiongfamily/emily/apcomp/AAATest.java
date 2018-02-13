package com.xiongfamily.emily.apcomp;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class AAATest {

	public static void main(String[] args) {
		

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String dateStr = df.format(Calendar.getInstance().getTime());
		System.out.println(dateStr);
		
//		String fileLocation = "/nfs/PROD/SFTP/bluesheets/finra/FBS000000043214-20170920-134725.txt";
//		int pos = fileLocation.lastIndexOf(".txt");
//	    String sendFileName = fileLocation.substring(0, pos-16) + ".txt"; 
//		int kkk = 0;
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
//
//		
//		String fname ="SEC000000000614-20171127-144359.txt";
//		pos = fname.lastIndexOf(".txt");
//		
//		String time1 = fname.substring(pos-15, pos-11) + "-" + fname.substring(pos-11, pos-9) + "-" +
//				fname.substring(pos-9, pos-7 )+ " " + fname.substring(pos-6, pos-4) + ":" + fname.substring(pos-4, pos-2) + ":" + fname.substring(pos-2, pos);
//		Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(Timestamp.valueOf(time1).getTime() + 1000);
//		String time2 = df.format(cal.getTime());
//		System.out.println(time1 + "----" + time2);
//		
		
		
		
//
//		DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
//		Calendar c = Calendar.getInstance();
//
//		System.out.println(" Today is  " + df2.format(c.getTime()));
//		
//		c.add(Calendar.MONTH, -2);
//		
//		System.out.println(" Today is  " + df2.format(c.getTime()));
//		
//		//c.set(2017, 0, 1); 
//		
//		for (int i=0; i < 12; i++){
//			 System.out.println(" The date is " + c.getTime());
//			 c.add(Calendar.MONTH, 1);
//			 c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
//			 System.out.println(" NOW the first day is " + c.getTime());
//			 c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//			 System.out.println(" NOW the last day is " + c.getTime());
//		}
	}  


}
