package za.co.loans.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.loans.domain.LoanRequest;
import za.co.loans.domain.LoanResponse;
import za.co.loans.service.LoanService;

@Api(tags = "Loans")
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @ApiOperation(value = "Apply for a loan",
            notes = "One loan application request supported in the body.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoanResponse applyForALoan(@RequestBody LoanRequest loanRequest){
        return loanService.applyForALoan(loanRequest);
    }
}
