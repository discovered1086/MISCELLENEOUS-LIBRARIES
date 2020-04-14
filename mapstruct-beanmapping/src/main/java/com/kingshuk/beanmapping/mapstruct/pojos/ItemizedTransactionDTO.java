package com.kingshuk.beanmapping.mapstruct.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.money.MonetaryAmount;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@JsonRootName(value = "itemizedTransaction")
@JsonPropertyOrder(value = { "itemizedTransactionId", "itemizedTransactionDescription",
		"itemizedTransactionAmount" }, alphabetic = true)
public class ItemizedTransactionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@JsonProperty(required = true, value = "itemizedTransactionId")
	private String itemizedTransactionId;

	@JsonProperty(required = true, value = "itemizedTransactionDescription")
	private String itemizedTransactionDescription;

	private MonetaryAmount itemizedTransactionAmount;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(itemizedTransactionId).append(itemizedTransactionDescription)
				.append(itemizedTransactionAmount).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemizedTransactionDTO) {
			final ItemizedTransactionDTO itemizedTransactionEntity = (ItemizedTransactionDTO) obj;

			boolean transactionEquality = new EqualsBuilder()
					.append(this.itemizedTransactionAmount, itemizedTransactionEntity.getItemizedTransactionAmount())
					.append(this.itemizedTransactionDescription.trim().toUpperCase(),
							itemizedTransactionEntity.getItemizedTransactionDescription().trim().toUpperCase())
					.isEquals();

			return (((Objects.nonNull(this.itemizedTransactionId)
					&& Objects.nonNull(itemizedTransactionEntity.getItemizedTransactionId()))
					&& this.itemizedTransactionId.equals(itemizedTransactionEntity.getItemizedTransactionId()))
					|| transactionEquality);
		} else {
			return false;
		}
	}

}
