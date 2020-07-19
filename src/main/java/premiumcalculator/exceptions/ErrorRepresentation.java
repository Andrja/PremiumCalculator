package premiumcalculator.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRepresentation {
    private ErrorCode code;
    private String message;
}
