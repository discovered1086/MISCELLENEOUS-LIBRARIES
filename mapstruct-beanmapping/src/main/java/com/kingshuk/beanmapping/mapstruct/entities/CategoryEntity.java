package com.kingshuk.beanmapping.mapstruct.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

import com.kingshuk.beanmapping.mapstruct.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Category")
@Table(name = "FINANCE_CATEGORY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "CTGRY_ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String categoryId;

	@Version
	private int version;

	@Column(length = 60, name = "CTGRY_NM", nullable = false, unique = true)

	@NaturalId(mutable = true)
	private String categoryName;

	@Column(length = 100, name = "CTGRY_DESC")
	private String categoryDescription;

	@Column(name = "CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime categoryEffectiveDate;

	@Column(name = "CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime categoryTerminationDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "CTGRY_TRN_TYP")

	private TransactionTypeEnum categoryTransactionType;

	// @ManyToMany(cascade = {CascadeType.MERGE })
	@OneToMany(mappedBy = "parentCategory", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Set<SubCategoryEntity> subCategoryEntities;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(categoryId).append(categoryName).append(categoryDescription)
				.append(categoryEffectiveDate).append(categoryTerminationDate).append(categoryTransactionType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CategoryEntity) {
			final CategoryEntity categoryEntity = (CategoryEntity) obj;

			boolean categoryEquality = new EqualsBuilder().append(this.categoryName.trim().toUpperCase(),
					categoryEntity.getCategoryName().trim().toUpperCase()).isEquals();

			return (((Objects.nonNull(this.categoryId) && Objects.nonNull(categoryId))
					&& this.categoryId.equals(categoryEntity.getCategoryId())) || categoryEquality);

		} else {
			return false;
		}
	}

}
