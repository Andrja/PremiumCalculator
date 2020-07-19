package premiumcalculator.exceptions;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("internal-server-error", "Internal server error"),
    BAD_REQUEST("bad-request", "Bad request");

    public final String code;
    public final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
