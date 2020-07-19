package premiumcalculator.risks;

import premiumcalculator.dto.RiskType;

import static premiumcalculator.dto.RiskType.THEFT;

public class TheftRisk implements CommonRisk {

    public static final RiskType RISK_TYPE = THEFT;
    private static final Double THEFT_COEFFICIENT_DEFAULT = 0.11D;
    private static final Double THEFT_COEFFICIENT = 0.05D;
    private static final Double COEFFICIENT_CHANGE_POINT = 15D;

    @Override
    public Double calculate(Double sum) {
        return sum > COEFFICIENT_CHANGE_POINT
                ? sum * THEFT_COEFFICIENT
                : sum * THEFT_COEFFICIENT_DEFAULT;
    }
}
