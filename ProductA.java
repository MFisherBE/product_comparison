/* *******************************************************************
 *   Class:     ProductA
 *
 *   Purpose:   This Class contains the data for the Basic Electricity
 *              Tariff for electricity consumption and also contains
 *              the method to calculate the annual costs for this
 *              tariff.
 * ********************************************************************/
public class ProductA {
    String name = "Basic Electricity Tariff";   // Product name
    double bCost1 = 5;                          // Tariff monthly base cost (euros)
    double months = 12;                         // Month constant for calculation
    double cCost = 0.22;                        // Additional consumption (euros)/kWh

    /*----------------------------------------------------------------
    |   Method:     costcalc
    |
    |   Purpose:    This method calculates the annual electricity
    |               cost in the Basic Electricity Tariff based on the
    |               annual consumption (kWh).
    |
    |   Parameters: consume - (Type: double)
    |                         (Electricity consumpton (kWh))
    |
    |   Returns:    tCostA - (Type: double)
    |                        (Calculated annual cost (euros))
    |---------------------------------------------------------------*/
    public double costcalc(double consume) {
        double tCostA=0;        // initialize total yearly cost
        tCostA = (cCost*consume)+(bCost1*months);   // calculate yearly cost
        return tCostA;          // return yearly cost
    }
}