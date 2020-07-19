package premiumcalculator.dto;

import lombok.Data;

@Data
public class PolicySubObject {
    private String name;
    private Double sumInsured;
    private RiskType riskType;
}
