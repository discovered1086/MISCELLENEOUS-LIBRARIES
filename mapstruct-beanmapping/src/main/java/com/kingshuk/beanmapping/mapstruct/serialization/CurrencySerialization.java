package com.kingshuk.beanmapping.mapstruct.serialization;

import java.io.IOException;

import javax.money.CurrencyUnit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CurrencySerialization extends StdSerializer<CurrencyUnit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3533846439119755612L;

	protected CurrencySerialization(Class<CurrencyUnit> t) {
		super(t);
	}

	@SuppressWarnings("unused")
	private CurrencySerialization() {
		this(null);
	}

	@Override
	public void serialize(CurrencyUnit value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("currencyCode", String.valueOf(value.getCurrencyCode()));
		gen.writeStringField("numericCode", String.valueOf(value.getNumericCode()));
		gen.writeEndObject();
	}

}
