package com.gyxsh.utils.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverters extends StrutsTypeConverter{

	private DateFormat dateFormat;
	
	{
		dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}
	
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		if(arg2==Date.class){
			try {
				if(arg1[0]==""){
					return null;
				}else{
					return dateFormat.parse(arg1[0]);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		if(arg1 instanceof Date){
			return dateFormat.format((Date)arg1);
		}
		return null;
	}

}
