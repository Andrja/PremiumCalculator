package premiumcalculator.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.Collections.singletonList;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({RiskTypeFieldNotFound.class,})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<ErrorRepresentation> riskTypeFieldNotFound() {
        ErrorRepresentation error = new ErrorRepresentation(ErrorCode.INTERNAL_SERVER_ERROR,
                "Field - RISK_TYPE hasn't been found in the risk class or class doesn't exist");
        return singletonList(error);
    }
}
