package premiumcalculator.dto;

import lombok.Data;

import java.util.List;

@Data
public class Policy {
    private String policyNumber;
    private PolicyStatus policyStatus;
    private List<PolicyObject> policyObjects;
}
