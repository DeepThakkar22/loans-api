package za.co.loans.service;

import org.springframework.stereotype.Service;
import za.co.loans.domain.LoanRequest;
import za.co.loans.domain.LoanResponse;
import za.co.loans.domain.ValidationResponse;
import za.co.loans.service.validation.LoanApplicationValidation;

import java.util.UUID;

@Service
public class LoanService {

    private final LoanApplicationValidation validation;

    public LoanService(LoanApplicationValidation validation) {
        this.validation = validation;
    }

    public LoanResponse applyForALoan(LoanRequest loanRequest){
        ValidationResponse validationResponse = validation.validate(loanRequest);
        return LoanResponse.builder()
                .validationStatus(validationResponse.isValid())
                .errors(validationResponse.getErrors())
                .warnings(validationResponse.getWarnings())
                .reference(UUID.randomUUID())
                .build();
    }

}
