package com.kingshuk.beanmapping.mapstruct.serialization;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateTimeSerializer extends StdSerializer<ZonedDateTime> {

	/**
	 * 
	 */

	private static final long serialVersionUID = -5042219452670668879L;

	@SuppressWarnings("unused")
	private DateTimeSerializer() {
		this(null);
	}

	protected DateTimeSerializer(Class<ZonedDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_DATE_TIME));
	}

}
