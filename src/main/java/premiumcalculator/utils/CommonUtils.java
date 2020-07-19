package premiumcalculator.utils;

import premiumcalculator.dto.RiskType;
import premiumcalculator.exceptions.RiskTypeFieldNotFound;
import premiumcalculator.risks.CommonRisk;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtils {

    private static final String PACKAGE_NAME = "premiumcalculator/risks";
    private static final String DOT_SEPARATED_PACKAGE_NAME = "premiumcalculator.risks.";

    public static CommonRisk retrieveAssociatedRiskCalculator(RiskType riskType) {
        List<Class> classes = new ArrayList<>();
        URL upackage = CommonRisk.class.getClassLoader().getResource(PACKAGE_NAME);

        DataInputStream dis = null;
        try {
            dis = new DataInputStream((InputStream) upackage.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = null;

        try {
            while ((line = dis.readLine()) != null) {
                if (line.endsWith(".class")) {
                    try {
                        classes.add(Class.forName(DOT_SEPARATED_PACKAGE_NAME + Arrays.asList(line.split("\\.")).get(0)));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object result = new Object();

        for (Class aClass : classes) {
            List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
            Field field = fields.stream().filter(i -> "RISK_TYPE".equals(i.getName())).findAny().orElse(null);
            if (field != null) {
                Class<?> fieldType = field.getType();
                try {
                    if (fieldType == RiskType.class && field.get(riskType).equals(riskType)) {
                        result = aClass.newInstance();
                        break;
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        if (result.getClass().isInstance(new Object()))
            throw new RiskTypeFieldNotFound();

        return (CommonRisk) result;
    }

}
