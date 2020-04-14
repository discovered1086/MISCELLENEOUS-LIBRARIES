package com.kingshuk.beanmapping.mapstruct.pojos;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.kingshuk.beanmapping.mapstruct.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "transactionType")
public class TransactionTypeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478300589711095031L;

	@JsonProperty(required = true, value = "transactionTypeCode")
	private String transactionTypeCode;

	@JsonProperty(required = true, value = "typeOfTransaction")
	private TransactionTypeEnum typeOfTransaction;

	@JsonProperty(required = true, value = "transactionTypeDefinition")
	private String transactionTypeDefinition;

	@JsonProperty(required = true, value = "transactionTypeEffectiveDate")
	private ZonedDateTime transactionTypeEffectiveDate;

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	@JsonProperty(required = false, value = "transactionTypeTerminationDate")
	private ZonedDateTime transactionTypeTerminationDate;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactionTypeCode).append(typeOfTransaction)
				.append(transactionTypeDefinition).append(transactionTypeEffectiveDate)
				.append(transactionTypeTerminationDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransactionTypeDTO) {
			final TransactionTypeDTO transactionTypeDTO = (TransactionTypeDTO) obj;

			boolean transactionTypeEquality = new EqualsBuilder()
					.append(this.transactionTypeCode, transactionTypeDTO.getTransactionTypeCode())
					.append(this.typeOfTransaction, transactionTypeDTO.getTypeOfTransaction())
					.append(this.transactionTypeDefinition.trim().toUpperCase(),
							transactionTypeDTO.getTransactionTypeDefinition().trim().toUpperCase())
					.isEquals();

			return (((Objects.nonNull(this.transactionTypeCode) && Objects.nonNull(transactionTypeCode))
					&& this.transactionTypeCode.equals(transactionTypeDTO.getTransactionTypeCode()))
					|| transactionTypeEquality);

		} else {
			return false;
		}
	}

}
