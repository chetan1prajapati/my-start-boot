package com.av.rfid.rest.component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {

	private static final Logger logger = LoggerFactory.getLogger(CustomDateSerializer.class);

	public static String dateFormat = "MM/dd/yyyy HH:mm:ss";

	@Override
	public void serialize(Date arg0, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		try {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			String date = df.format(arg0);
			gen.writeString(date);
			logger.debug(date);
		} catch (Exception e) {
			logger.error("Date Format invalid: {} required format: {} ",arg0, dateFormat);
		}

	}

}
