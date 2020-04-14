package com.kingshuk.beanmapping.mapstruct.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "itemizedTransaction")
@Table(name = "ITEMIZED_TRANSACTION")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class ItemizedTransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "ITMZD_TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String itemizedTransactionId;

	@Version
	private int version;

	@Column(length = 1000, name = "ITMZD_TRNSCTN_DSCRPTN", nullable = false)
	private String itemizedTransactionDescription;

	@ManyToOne
	@JoinColumn(name = "ITMZD_TRNSCTN_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	private CurrencyEntity itemizedTransactionCurrency;

	@Column(name = "ITMZD_TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)")
	private BigDecimal itemizedTransactionAmount;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PRNT_TRNSCTN_ID", referencedColumnName = "TRNSCTN_ID", nullable = false, updatable = false)
	private TransactionEntity parentTransaction;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(itemizedTransactionId).append(itemizedTransactionDescription)
				.append(itemizedTransactionCurrency).append(itemizedTransactionAmount).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemizedTransactionEntity) {
			final ItemizedTransactionEntity itemizedTransactionEntity = (ItemizedTransactionEntity) obj;

			boolean transactionEquality = new EqualsBuilder()
					.append(this.itemizedTransactionCurrency,
							itemizedTransactionEntity.getItemizedTransactionCurrency())
					.append(this.itemizedTransactionAmount, itemizedTransactionEntity.getItemizedTransactionAmount())
					.append(this.itemizedTransactionDescription.trim().toUpperCase(),
							itemizedTransactionEntity.getItemizedTransactionDescription().trim().toUpperCase())
					.isEquals()
					&& Objects.deepEquals(this.parentTransaction, itemizedTransactionEntity.getParentTransaction());

			return (((Objects.nonNull(this.itemizedTransactionId)
					&& Objects.nonNull(itemizedTransactionEntity.getItemizedTransactionId()))
					&& this.itemizedTransactionId.equals(itemizedTransactionEntity.getItemizedTransactionId()))
					|| transactionEquality);
		} else {
			return false;
		}
	}

}
