package com.kingshuk.beanmapping.mapstruct.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FINANCE_SUB_CATEGORY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "findExistingSubCategoryByName", query = "from SubCategoryEntity c where upper(trim(c.subCategoryName))=upper(trim(:subCategoryNameInput))")

public class SubCategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@Id
	@Column(length = 15, name = "SB_CTGRY_ID", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String subCategoryId;

	@Version
	private int version;

	@Column(length = 60, name = "SB_CTGRY_NM", nullable = false)
	private String subCategoryName;

	@Column(length = 100, name = "SB_CTGRY_DESC")
	private String subCategoryDescription;

	@ManyToOne
	@JoinColumn(name = "CTGRY_ID", referencedColumnName = "CTGRY_ID", nullable = false)
	private CategoryEntity parentCategory;

	@Column(name = "SB_CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime subCategoryEffectiveDate;

	@Column(name = "SB_CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime subCategoryTerminationDate;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(subCategoryId).append(subCategoryName).append(subCategoryDescription)
				.append(subCategoryEffectiveDate).append(subCategoryTerminationDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubCategoryEntity) {
			final SubCategoryEntity subCategoryEntity = (SubCategoryEntity) obj;

			boolean subCategoryEquality = new EqualsBuilder().append(this.subCategoryName.trim().toUpperCase(),
					subCategoryEntity.getSubCategoryName().trim().toUpperCase()).isEquals()
					&& Objects.deepEquals(this.parentCategory, subCategoryEntity.getParentCategory());

			return (((Objects.nonNull(this.subCategoryId) && Objects.nonNull(subCategoryId))
					&& this.subCategoryId.equals(subCategoryEntity.getSubCategoryId())) || subCategoryEquality);

		} else {
			return false;
		}
	}

}
