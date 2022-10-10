/* *******************************************************************
 *   Class:     ProductB
 *
 *   Purpose:   This Class contains the data for the Packaged Tariff
 *              for electricity consumption and also contains the
 *              method to calculate the annual costs for this tariff.
 * ********************************************************************/
public class ProductB {
    String name = "Packaged Tariff";    // Product Name
    double bCost1 = 800;                // Tariff annual base cost (euros) (if consumption (kWh) < 4000 kWh/year)
    double cCost = 0.30;                // Additional consumption costs (euros) (if consumption (kWh) > 4000 kWh/year)
    int baseconsume = 4000;             // Base consumption amount (kWh)

    /*----------------------------------------------------------------
    |   Method:     costcalc
    |
    |   Purpose:    This method calculates the annual electricity
    |               cost in the Packaged Tariff based on the annual
    |               consumption (kWh).
    |
    |   Parameters: consume - (Type: double)
    |                         (Electricity consumpton (kWh))
    |
    |   Returns:    tCostB - (Type: double)
    |                        (Calculated annual cost (Euros)
    |---------------------------------------------------------------*/
    double costcalc(double consume){
        double tCostB = 0;
        if(consume <= baseconsume){
            tCostB = bCost1;
        }
        else{
            tCostB = bCost1 + (cCost*(consume - baseconsume));
        }
        return tCostB;
    }
}
