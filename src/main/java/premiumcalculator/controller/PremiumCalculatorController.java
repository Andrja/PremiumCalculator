package premiumcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import premiumcalculator.dto.Policy;
import premiumcalculator.service.PremiumCalculator;

@RestController
@Api(tags = {"PremiumCalculatorApi"})
public class PremiumCalculatorController {

    @Autowired
    private PremiumCalculator premiumCalculator;

    @ApiOperation(value = "Count Premium")
    @RequestMapping(value = "/countpremium", method = RequestMethod.POST)
    public ResponseEntity<String> getInboxData(@RequestBody Policy policy) {
        return new ResponseEntity<>(premiumCalculator.calculate(policy) + " EUR", HttpStatus.OK);
    }

}
