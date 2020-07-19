package premiumcalculator.dto;

import lombok.Data;

import java.util.List;

@Data
public class PolicyObject {
    private String name;
    private List<PolicySubObject> policySubObjects;
}
