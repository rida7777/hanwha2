package com.hanwha.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Date transDate(String sdate) {
		Date sql_date = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date d = sd.parse(sdate);
			sql_date = new Date(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		//sql Date랑 다른 것을 알려줘야 한다.
		
		return sql_date;
	}
}
