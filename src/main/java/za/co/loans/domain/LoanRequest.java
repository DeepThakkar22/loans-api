package za.co.loans.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @ApiModelProperty(value = "Name of the applicant", example = "Pontsho")
    private String name;

    @ApiModelProperty(value = "surname of the applicant", example = "Molewa")
    private String surname;

    @ApiModelProperty(value = "ID Number of the applicant", example = "9901025391084")
    private String idNumber;

    @ApiModelProperty(value = "Bank Account")
    private BankAccount bankAccount;
}
