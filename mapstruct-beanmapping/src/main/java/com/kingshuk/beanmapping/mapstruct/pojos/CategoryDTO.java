package com.kingshuk.beanmapping.mapstruct.pojos;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.kingshuk.beanmapping.mapstruct.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "transactionCategory")
@JsonPropertyOrder(value = { "categoryId", "categoryName", "categoryDescription", "transactionType",
		"categoryEffectiveDate", "categoryTerminationDate", "subCategories" })
public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@JsonProperty(value = "categoryId")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String categoryId;

	@JsonProperty(required = true, value = "categoryName")
	private String categoryName;

	@JsonProperty(required = false, value = "categoryDescription")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String categoryDescription;

	@JsonProperty(required = true, value = "categoryEffectiveDate")
	private ZonedDateTime categoryEffectiveDate;

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	@JsonProperty(required = false, value = "categoryTerminationDate")
	private ZonedDateTime categoryTerminationDate;

	@JsonProperty(required = false, value = "transactionType")
	private TransactionTypeEnum categoryTransactionType;

	@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
	private Set<SubCategoryDTO> subCategories;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(categoryId).append(categoryName).append(categoryDescription)
				.append(categoryEffectiveDate).append(categoryTerminationDate).append(categoryTransactionType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CategoryDTO) {
			final CategoryDTO categoryDTO = (CategoryDTO) obj;

			boolean categoryEquality = new EqualsBuilder()
					.append(this.categoryName.trim().toUpperCase(), categoryDTO.categoryName.trim().toUpperCase())
					.isEquals() && Objects.deepEquals(this.subCategories, categoryDTO.subCategories);

			return (((Objects.nonNull(this.categoryId) && Objects.nonNull(categoryId))
					&& this.categoryId.equals(categoryDTO.categoryId)) || categoryEquality);

		} else {
			return false;
		}
	}
}
