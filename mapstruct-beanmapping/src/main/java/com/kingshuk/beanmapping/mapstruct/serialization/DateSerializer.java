package com.kingshuk.beanmapping.mapstruct.serialization;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateSerializer extends StdSerializer<ZonedDateTime> {

	/**
	 * 
	 */

	private static final long serialVersionUID = -5042219452670668879L;

	@SuppressWarnings("unused")
	private DateSerializer() {
		this(null);
	}

	protected DateSerializer(Class<ZonedDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String date = value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		gen.writeString(date);
	}

}
