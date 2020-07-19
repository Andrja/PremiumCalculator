package premiumcalculator.service;

import org.springframework.stereotype.Service;
import premiumcalculator.dto.Policy;
import premiumcalculator.dto.PolicyObject;
import premiumcalculator.dto.PolicySubObject;
import premiumcalculator.dto.RiskType;
import premiumcalculator.risks.CommonRisk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static premiumcalculator.utils.CommonUtils.retrieveAssociatedRiskCalculator;

@Service
public class PremiumCalculator {

    public Double calculate(Policy policy) {
        Double result = 0D;
        Map<RiskType, List<PolicySubObject>> collectIntoMapByRiskType =
                policy.getPolicyObjects().stream()
                        .map(PolicyObject::getPolicySubObjects)
                        .flatMap(Collection::stream)
                        .collect(Collectors.groupingBy(PolicySubObject::getRiskType));

        for (Map.Entry<RiskType, List<PolicySubObject>> entry : collectIntoMapByRiskType.entrySet()) {
            Double particularRiskSum = entry.getValue().stream()
                    .map(PolicySubObject::getSumInsured)
                    .reduce(
                            0D,
                            (a, b) -> a + b);

            CommonRisk calculateRisk = retrieveAssociatedRiskCalculator(entry.getKey());
            result = result + calculateRisk.calculate(particularRiskSum);
        }

        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


}
