package za.co.loans.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    @ApiModelProperty(value = "Generated reference number for you application")
    private UUID reference;
    @ApiModelProperty(example = "false", required = true, value = "Indicates whether the validation is a success/failure")
    private Boolean validationStatus;
    @ApiModelProperty(value = "Validation errors")
    private List<String> errors;
    @ApiModelProperty(value = "Validation warnings")
    private List<String> warnings;
}
