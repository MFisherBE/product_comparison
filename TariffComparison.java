import java.util.*;
import java.util.Arrays;

/* *******************************************************************
*   Class:      TariffComparison
*
*   Purpose:    This class drives the program to compare different
*               electricity tariffs and their associated annual
*               costs based on kWh consumption and base costs
*               (where applied). Objects of each product are
*               created, cost calculations are made with object data
*               and user-input for electricity consumption in kWh.
*               A list is then made and sorted with object data
*               and the calculations. The list is displayed to the
*               user at the end of the program.
* ********************************************************************/
public class TariffComparison {

    /*----------------------------------------------------------------
    |   Method:     main
    |
    |   Purpose:    This method drives the Tariff Comparison Program
    |               by driving all other methods that build the
    |               build the product, calculate the costs, and
    |               create and sort the list of products with their
    |               associated prices.
    |---------------------------------------------------------------*/
    public static void main(String[] args){

        // Product List
        ArrayList<ArrayList<String>> prodlist = new ArrayList<>();

        // Initialize variables
        double pacost = 0;          // Product A cost
        double pbcost = 0;          // Product B cost
        double inconsume = 0;       // Input for electricty consumption
        int pqty = 2;               // Product quantity for iterating

        // Drive program
        inconsume = getInput();                     // Get consumption
        pacost = getPACost(inconsume);              // Get annual cost for Product A
        pbcost = getPBTotal(inconsume);             // Get annual cost for Product B
        prodlist = getSortedList(pacost, pbcost);   // Make and sort list of products and annual costs
        displayList(prodlist, pqty);                // Display products with sorted associated prices
    }

    /*----------------------------------------------------------------
    |   Method:     getInput
    |
    |   Purpose:    This method prompts the user for electricity
    |               consumption input and validates the input to
    |               calculate the total annual cost with each Tariff.
    |               Only positive integer values are allowed.
    |
    |   Parameters: None
    |
    |   Returns:    econsume - (electricity consumption)
    |---------------------------------------------------------------*/
    public static double getInput(){

        // Initialize variables
        double econsume = 0;        // Electricity consumption (kWh)
        double input = 0;           // User input
        String getinput = "";       // String to scan for new input if last input was invalid

        // Display user-input prompt
        System.out.print("Please specify consumption (kWh) for Tariff comparison: ");
        Scanner sc = new Scanner(System.in);    // Get consumption amount in kWh


        /*---Input Validation---*/
        while(1==1) {
            if (sc.hasNextInt()) {                      // Is input an whole number?
                input = sc.nextInt();                   // Yes, input is a whole number, check for negative
                if (input <= 0) {                       // Is user input a negative whole number?
                    System.out.print("Please enter a positive number: ");    // Yes, give invalid-input message
                    getinput = sc.nextLine();           // Prompt for input again
                } else {                                // User-input is a positive number
                    econsume = input;                   // Input is valid. Save it in consumption and return it.
                    break;
                }
            } else if (sc.hasNextDouble()) {            // Else, if input is not a whole number
                System.out.print("Please enter a whole number: ");   // Give specific invalid-input message
                getinput = sc.nextLine();               // Prompt for input again
            } else {                                    // Else, if input is not a number
                System.out.print("Please enter a number: "); // Give invalid-input message
                getinput = sc.nextLine();               // Prompt for input again
            }
        }
            return econsume;                        // Input is valid, return kWh consumption value
    }


    /*----------------------------------------------------------------
    |   Method:     getPACost
    |
    |   Purpose:    This method utilizes the calculation method
    |               within the ProductA class to calculate the total
    |               annual cost of the specified electricity
    |               consumption under the Basic Electricity Tariff.
    |
    |   Parameters: econsume - (electricity consumption)
    |
    |   Returns:    pacost - (Product A (Basic Electricity Tariff)
    |               annual costs)
    |---------------------------------------------------------------*/
    public static double getPACost(double econsume){
        ProductA pA = new ProductA();       // Instantiate ProductA object
        double pacost = 0;                  // Initialize Product A annual cost
        pacost = pA.costcalc(econsume);     // Get annual cost for Basic Electricity Tariff
        return pacost;                      // Return annual cost for Basic Electricity Tariff
    }

    /*----------------------------------------------------------------
    |   Method:     getPBCost
    |
    |   Purpose:    This method utilizes the calculation method
    |               within the ProductB class to calculate the total
    |               annual cost of the specified electricity
    |               consumption under the Packaged Tariff.
    |
    |   Parameters: econsume - (electricity consumption)
    |
    |   Returns:    pacost - (Product A (Basic Electricity Tariff)
    |               annual costs)
    |---------------------------------------------------------------*/
    public static double getPBTotal(double econsume){
        ProductB pB = new ProductB();       // Instantiate a ProductB object
        double pbcost = 0;                  // Initialize Product B annual cost
        pbcost = pB.costcalc(econsume);     // Get annual costs for Packaged Tariff
        return pbcost;                      // Return annual cost for Packaged Tariff
    }

    /*----------------------------------------------------------------
    |   Method:     getSortedList
    |
    |   Purpose:    This method builds and sorts a list of the
    |               electricity tariff names and their associated
    |               annual costs.
    |
    |   Parameters: pacost - (Type: double)
    |                        (Annual consumption cost for Product A)
    |               pbcost - (Type: double)
    |                        (Annual consumption cost for Product B)
    |
    |   Returns:    sortedList - (Type: ArrayList<ArrayList<String>>)
    |                            (Two dimensional ArrayList with
    |                            (Tariff Names in column 0, and
    |                            Annual costs in column 1.)
    |---------------------------------------------------------------*/
    public static ArrayList<ArrayList<String>>
    getSortedList(double pacost, double pbcost) {
        ProductA pA = new ProductA();           // Instantiate a ProductA object
        ProductB pB = new ProductB();           // Instantiate a ProductB object
        int pqty = 2;                           // Constant for quantity of products (tariffs)
        String[] pNames = new String [pqty];    // Array of tariff names
        Double[] totals = new Double[pqty];     // Array of annual tariff costs
        String[] AnnualCostStr = new String[pqty]; // Array of string values of sorted annual tariff costs
        ArrayList<ArrayList<String>> sortedList = new ArrayList<>(); /* Two dimensional ArrayList of sorted
                                                                        tariff names and annual costs in
                                                                        ascending order of annual costs */

        /*---Populate tariff names---*/
        pNames[0] = pA.name;            // ProductA Name
        pNames[1] = pB.name;            // ProductB Name

        /*---Populate annual tariff costs---*/
        totals[0] = pacost;             // Annual cost of Product A
        totals[1] = pbcost;             // Annual cost of Product B

        /*---Sort tariff name and cost arrays in Ascending order of cost---*/
        for(int i = 0; i <pqty; i++){           // Iterations to get current element
            for(int j=1+i; j < pqty; j++) {     // Iterations to compare next element
                double TempDbl = 0;             // Temporary cost holder for sorting
                String TempStr = "";            // Temporary name holder for sorting
                if(totals[i] > totals [j]){     // Is the the current tariff cost more expensive than the next tariff cost?
                    /*-sort tariff costs-*/
                    TempDbl = totals[i];        // Yes, store the current tariff cost in the temporary holder
                    totals[i] = totals[j];      // Move the less expensive tariff cost higher on the displayed list
                    totals[j] = TempDbl;        // Move the more expensive tariff cost lower on the displayed list

                    /*-sort tariff names-*/
                    TempStr = pNames[i];        // Put the current tariff name in a temporary holder
                    pNames[i] = pNames[j];      // Move the name of the less expensive tariff higher on the displayed list
                    pNames[j] = TempStr;        // Move the name of the more expensive tariff lower on the displayed list
                }
                else{                           // If the current tariff cost < next tariff cost
                                                // Get next comparison
                }
            }
        }

        /*---Convert annual tariff costs to string with two decimal places---*/
        for(int i=0; i < pqty; i++) {
            AnnualCostStr[i] = String.format("%.2f", totals[i]);                    // Convert cost dbl to string
            AnnualCostStr[i] = AnnualCostStr[i].replace(".",",");   // Convert to GER currency symbols
        }

        /*---Populate two dimensional ArrayList with tariff names and annual costs---*/
        for(int i=0; i < pqty; i++) {
            // two dimensional ArrayList(Column 0: Tariff names, Column 1: Annual tariff costs
            sortedList.add(new ArrayList<String>(Arrays.asList(pNames[i], AnnualCostStr[i])));
        }
        return sortedList;      // return sorted list of tariff names and annual costs
    }

    /*----------------------------------------------------------------
    |   Method:     displayList
    |
    |   Purpose:    This method displays the list of tariffs and
    |               the associated annual costs. Tariff names are
    |               displayed in column 0 (the left column) and
    |               the respective annual costs are displayed in
    |               column 1 (the right column).
    |               consumption input and validates the input to
    |               calculate the total annual cost with each Tariff.
    |               Only positive integer values are allowed.
    |
    |   Parameters: TariffList - (Type: ArrayList<ArrayList<String>>)
    |                            (Two dimensional ArrayList with
    |                            (Tariff Names in column 0, and
    |                            Annual costs in column 1.)
    |               pqty - (Type: Int)
    |                      (Quantity of products(tariffs))
    |
    |   Returns:    0
    |---------------------------------------------------------------*/
    public static int displayList
            (ArrayList<ArrayList<String>> TariffList,
             int pqty) {

        /*-Initialize variables-*/
        int Name = 0;               // Name index
        int AnnualCost = 1;         // Annual cost index

        /*---Display list of tariff names and costs---*/
        for(int row = 0; row < pqty; row++){    // Increment on rows so we can index on columns (names and costs)
                System.out.printf
                        ("%-25.25s %-1.1s %-1.12s %-1.5s\n",TariffList.get(row).get(Name)
                        , "€", TariffList.get(row).get(AnnualCost), "/year");
        }
        return 0;                   // return 0 on success
    }
}