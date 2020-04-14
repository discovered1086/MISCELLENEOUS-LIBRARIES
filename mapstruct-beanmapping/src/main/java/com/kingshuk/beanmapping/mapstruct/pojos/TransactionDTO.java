package com.kingshuk.beanmapping.mapstruct.pojos;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.money.MonetaryAmount;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonRootName(value = "transaction")
@JsonPropertyOrder(value = { "transactionId", "transactionDescription", "transactionCurrency", "transactionAmount",
		"transactionType", "transactionDate" }, alphabetic = true)
public class TransactionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@JsonProperty(required = true, value = "transactionId")
	private String transactionId;

	@JsonProperty(required = true, value = "transactionType")
	private TransactionTypeDTO transactionType;

	@JsonProperty(required = true, value = "transactionDescription")
	private String transactionDescription;

	@JsonProperty(required = true, value = "customerAccount")
	private AccountDTO account;

	@JsonProperty(required = true, value = "transactionCategory")
	private CategoryDTO transactionCategory;

	private MonetaryAmount transactionAmount;

	@JsonProperty(required = true, value = "transactionDate")
	private ZonedDateTime transactionDate;

	@JsonProperty(required = false, value = "transactionNotes")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String transactionNotes;

	@JsonProperty(required = false, value = "itemizedTransactions")
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
	private Set<ItemizedTransactionDTO> itemizedTransactions;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactionId).append(transactionDate).append(transactionCategory)
				.append(transactionAmount).append(transactionType).append(transactionDescription).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransactionDTO) {
			final TransactionDTO transactionEntity = (TransactionDTO) obj;

			boolean transactionEquality = new EqualsBuilder()
					.append(this.transactionDate, transactionEntity.transactionDate)
					.append(this.transactionAmount, transactionEntity.transactionAmount)
					.append(this.transactionCategory, transactionEntity.transactionCategory)
					.append(this.account, transactionEntity.account)
					.append(this.transactionDescription.trim().toUpperCase(),
							transactionEntity.transactionDescription.trim().toUpperCase())
					.append(this.transactionType, transactionEntity.transactionType).isEquals();

			return (((Objects.nonNull(this.transactionId) && Objects.nonNull(transactionEntity.transactionId))
					&& this.transactionId.equals(transactionEntity.transactionId)) || transactionEquality);
		} else {
			return false;
		}
	}

}
