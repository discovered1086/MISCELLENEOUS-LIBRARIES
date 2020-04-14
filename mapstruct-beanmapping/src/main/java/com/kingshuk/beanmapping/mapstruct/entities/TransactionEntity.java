package com.kingshuk.beanmapping.mapstruct.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "transaction")
@Table(name = "TRANSACTION_MASTER")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class TransactionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817135024822644549L;

	@Id
	@Column(length = 50, name = "TRNSCTN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String transactionId;

	@Version
	private int version;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "TRNSCTN_CD", referencedColumnName = "TRNSCTN_CD", nullable = false)
	private TransactionTypeEntity transactionType;

	@Column(length = 1000, name = "TRNSCTN_DSCRPTN", nullable = false)
	private String transactionDescription;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_ACCT_ID", referencedColumnName = "ACCT_ID", nullable = false)
	private AccountEntity account;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	private CategoryEntity transactionCategory;

	@ManyToOne
	@JoinColumn(name = "TRNSCTN_CRNCY", referencedColumnName = "CRRNCY_CD", nullable = false)
	private CurrencyEntity transactionCurrency;

	@Column(name = "TRNSCTN_AMT", columnDefinition = "NUMBER(20,2)")
	private BigDecimal transactionAmount;

	/*
	 * I just found out that @Temporal can only be used with java.util.Date or
	 * java.sql.Date
	 */
	@Column(name = "TRNSCTN_DT", columnDefinition = "TIMESTAMP")
	@Type(type = "org.hibernate.type.ZonedDateTimeType")
	private ZonedDateTime transactionDate;

	@Column(name = "TRNSCTN_NTS", columnDefinition = "VARCHAR2(4000)")
	private String transactionNotes;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.REMOVE }, fetch = FetchType.EAGER, mappedBy = "parentTransaction")
	private Set<ItemizedTransactionEntity> itemizedTransactions;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactionId).append(transactionDate).append(transactionCurrency)
				.append(transactionCategory).append(transactionAmount).append(transactionType)
				.append(transactionDescription).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransactionEntity) {
			final TransactionEntity transactionEntity = (TransactionEntity) obj;

			boolean transactionEquality = new EqualsBuilder()
					.append(this.transactionDate, transactionEntity.transactionDate)
					.append(this.transactionCurrency, transactionEntity.transactionCurrency)
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
