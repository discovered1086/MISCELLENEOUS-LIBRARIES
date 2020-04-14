package com.kingshuk.beanmapping.mapstruct.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import com.kingshuk.beanmapping.mapstruct.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION_TYPE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478300589711095031L;

	@Id
	@Column(name = "TRNSCTN_CD", length = 10)
	private String transactionTypeCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRNSCTN_TYP", length = 10)
	private TransactionTypeEnum typeOfTransaction;

	@Column(name = "TRNSCTN_TYP_DFNTN", length = 200)
	private String transactionTypeDefinition;

	@Column(name = "TRNSCTN_TYP_EFFCTV_DT")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionTypeEffectiveDate;

	@Column(name = "TRNSCTN_TYP_TRMNTN_DT")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionTypeTerminationDate;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactionTypeCode).append(typeOfTransaction)
				.append(transactionTypeDefinition).append(transactionTypeEffectiveDate)
				.append(transactionTypeTerminationDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransactionTypeEntity) {
			final TransactionTypeEntity transactionTypeEntity = (TransactionTypeEntity) obj;

			boolean transactionTypeEquality = new EqualsBuilder()
					.append(this.typeOfTransaction, transactionTypeEntity.getTypeOfTransaction())
					.append(this.transactionTypeDefinition.trim().toUpperCase(),
							transactionTypeEntity.getTransactionTypeDefinition().trim().toUpperCase())
					.isEquals();

			return (((Objects.nonNull(this.transactionTypeCode) && Objects.nonNull(transactionTypeCode))
					&& this.transactionTypeCode.equals(transactionTypeEntity.getTransactionTypeCode()))
					|| transactionTypeEquality);

		} else {
			return false;
		}
	}
}
