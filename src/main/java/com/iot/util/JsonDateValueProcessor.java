package com.iot.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;

public class JsonDateValueProcessor implements JsonValueProcessor {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	
	public Object processObjectValue(String propertyName, Object date,
                                     JsonConfig config) {
		return simpleDateFormat.format(date);
	}

	@Override

	public Object processArrayValue(Object date, JsonConfig config) {
		return simpleDateFormat.format(date);
	}

}