# PremiumCalculator

Spring Boot microservice which has swagger ui and one endpoint - /countpremium

### General implementation approach:

For every risk type (THEFT, FIRE etc.) separate class is created which holds all the calculation logic. To add a new risk type we are basically:
   -    creating a new class under /risks package (copy/pasting FireRisk or TheftRisk) and making business logic  changes in calculate method
   -    adding new enum value to RiskType enum

no other changes needed.
            

### Running and testing microservice locally:


-   Clone the project
-   Open in Intellij IDEA
-   Run
-   In your browser open http://localhost:8080/swagger-ui.html#/
-   Open PremiumCalculatorApi
-   Choose /countpremium
-   Press button - Try it out
-   Provide your JSON policy object(for instance):
    
            {
              "policyNumber" : "LV20-02-100000-5",
              "policyStatus" : "APPROVED",
              "policyObjects" : [ {
                "name" : "flat",
                "policySubObjects" : [ {
                  "name" : "Table",
                  "sumInsured" : 8.0,
                  "riskType" : "THEFT"
                }, {
                  "name" : "TV",
                  "sumInsured" : 100.0,
                  "riskType" : "FIRE"
                } ]
              } ]
            }
-   Press execute