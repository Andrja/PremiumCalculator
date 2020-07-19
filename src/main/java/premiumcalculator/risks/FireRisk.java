package premiumcalculator.risks;

import premiumcalculator.dto.RiskType;

import static premiumcalculator.dto.RiskType.FIRE;

public class FireRisk implements CommonRisk {

    public static final RiskType RISK_TYPE = FIRE;
    private static final Double FIRE_COEFFICIENT_DEFAULT = 0.014D;
    private static final Double FIRE_COEFFICIENT = 0.024D;
    private static final Double COEFFICIENT_CHANGE_POINT = 100D;

    @Override
    public Double calculate(Double sum) {
        return sum > COEFFICIENT_CHANGE_POINT
                ? sum * FIRE_COEFFICIENT
                : sum * FIRE_COEFFICIENT_DEFAULT;
    }
}
