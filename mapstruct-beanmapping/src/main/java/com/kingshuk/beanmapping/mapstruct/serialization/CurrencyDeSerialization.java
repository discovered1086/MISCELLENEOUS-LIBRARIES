package com.kingshuk.beanmapping.mapstruct.serialization;

import java.io.IOException;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CurrencyDeSerialization extends StdDeserializer<CurrencyUnit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5851751132003317821L;

	protected CurrencyDeSerialization(Class<?> vc) {
		super(vc);
	}
	
	@SuppressWarnings("unused")
	private CurrencyDeSerialization() {
		this(null);
	}

	@Override
	public CurrencyUnit deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		JsonNode node = p.getCodec().readTree(p);

		String currencyCode="";

		if (node.get("currencyCode") != null) {
			currencyCode = node.get("currencyCode").asText();
		}

		return Monetary.getCurrency(currencyCode);
	}


}
