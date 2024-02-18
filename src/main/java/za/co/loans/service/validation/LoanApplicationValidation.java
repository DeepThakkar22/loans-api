package za.co.loans.service.validation;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.joda.time.Years;
import org.springframework.stereotype.Component;
import za.co.loans.domain.Banks;
import za.co.loans.domain.LoanRequest;
import za.co.loans.domain.ValidationResponse;
import za.co.loans.service.utils.IdNumberUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class LoanApplicationValidation {

    public ValidationResponse validate(LoanRequest loanRequest){

        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        validateIdNumber(loanRequest.getIdNumber(), errors);

        validateBankAccount(loanRequest, errors, warnings);

        if(isNull(loanRequest.getName()) || loanRequest.getName().isEmpty()){
            errors.add("Name is required");
        }
        if(isNull(loanRequest.getSurname()) || loanRequest.getSurname().isEmpty()){
            errors.add("Surname is required");
        }

        if (!StringUtils.isAlpha(loanRequest.getName())){
            errors.add("Name must not have any special characters and digits");
        }
        if (!StringUtils.isAlpha(loanRequest.getSurname())){
            errors.add("Surname must not have any special characters and digits");
        }

        return ValidationResponse.builder()
                .errors(errors)
                .warnings(warnings)
                .build();
    }

    private void validateBankAccount(LoanRequest loanRequest, List<String> errors, List<String> warnings) {
        if(nonNull(loanRequest.getBankAccount()) && nonNull(loanRequest.getBankAccount().getAccountNumber())
                && loanRequest.getBankAccount().getAccountNumber().trim().length() != 10){
            errors.add("Bank account number must be 10 digits");
        }

        if(nonNull(loanRequest.getBankAccount()) && nonNull(loanRequest.getBankAccount().getBankName())
                && loanRequest.getBankAccount().getBankName().equals(Banks.MOLEWA_BANK)){
            warnings.add("Refer to compliance");
        }
    }

    private void validateIdNumber(String idNumber, List<String> errors) {
        if(nonNull(idNumber) && idNumber.trim().length() != 13){
            errors.add("ID Number must be a valid South African ID number (13 digits)");
        } else if(IdNumberUtils.INSTANCE.getAge(idNumber).isLessThan(Years.years(18))) {
            errors.add("The client must be 18 years or older");
        }
    }
}
