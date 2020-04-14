package com.kingshuk.beanmapping.mapstruct.pojos;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "transactionSubCategory")
public class SubCategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7223290830168812286L;

	@JsonProperty(value = "subCategoryId")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String subCategoryId;

	@JsonProperty(required = true, value = "subCategoryName")
	private String subCategoryName;

	@JsonProperty(required = false, value = "subCategoryDescription")
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String subCategoryDescription;

	@JsonProperty(required = true, value = "subCategoryEffectiveDate")
	private ZonedDateTime subCategoryEffectiveDate;

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	@JsonProperty(required = false, value = "subCategoryTerminationDate")
	private ZonedDateTime subCategoryTerminationDate;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(subCategoryId).append(subCategoryName).append(subCategoryDescription)
				.append(subCategoryEffectiveDate).append(subCategoryTerminationDate).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubCategoryDTO) {
			final SubCategoryDTO subCategoryDTO = (SubCategoryDTO) obj;

			boolean subCategoryEquality = new EqualsBuilder().append(this.subCategoryName.trim().toUpperCase(),
					subCategoryDTO.subCategoryName.trim().toUpperCase()).isEquals();

			return (((Objects.nonNull(this.subCategoryId) && Objects.nonNull(subCategoryId))
					&& this.subCategoryId.equals(subCategoryDTO.subCategoryId)) || subCategoryEquality);

		} else {
			return false;
		}
	}

}
