package cn.bdqn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
//日期类型转换为String类型
public class StringToDateConverter implements Converter<String,Date>{
	private String pattern;
	@Override
	public Date convert(String StrArg) {
	
		Date date=null;
		DateFormat df=new SimpleDateFormat(pattern);
		try {
			date=df.parse(StrArg);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public StringToDateConverter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StringToDateConverter(String pattern) {
		super();
		this.pattern = pattern;
	}

}
