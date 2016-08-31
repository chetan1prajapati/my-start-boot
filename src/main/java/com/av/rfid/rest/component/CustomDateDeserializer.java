package com.av.rfid.rest.component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
	private static final Logger logger = LoggerFactory.getLogger(CustomDateDeserializer.class);

	public static String dateFormat = "MM/dd/yyyy HH:mm:ss";

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			Date date = formatter.parse(arg0.getText());
			return date;
		} catch (ParseException e) {
			logger.error("Date Format invalid: {} required format: {} ",arg0.getText(), dateFormat);
			throw new IllegalArgumentException("Invalid input date format.");
		}
		//return null;
	}

}
