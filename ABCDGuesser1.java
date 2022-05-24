import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program uses multiple methods to get positive value from user to
 * approximate with the charming theory using the jager formula, as part of the
 * formula user also is asked to enter 4 positive real numbers that arent one,
 * Once this develops the constant value is returned to main as well as the 4
 * personal numbers the user choose to be returned and stored into an array Main
 * method then uses a big nested loop to increment and decrement the exponent
 * values that were given and also are stored in an array The loop keeps looping
 * through until an approximation is finally derived thats within the desired
 * relative error, program then outputs approximation value as well as the
 * exponents used to derive it, and the user inputs
 *
 * @author Brian Rochford
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        //initial message for user to enter constant
        out.print(
                "Enter a positive real number to approximate as a constant: ");
        //declaring as string to test and convert to double if its a positive number
        String constant = in.nextLine();
        //initializing variable to use in loop for checking if user input
        //is a positive number
        double isItPos = 0;
        //loop that continues to ask user for positive real number and
        //continues to loop until user enters a positive number
        while (isItPos <= 0) {
            //conditional to parse string input to double
            if (FormatChecker.canParseDouble(constant)) {
                //setting value of input to initial variable for return
                isItPos = Double.parseDouble(constant);
                //conditional if this value is less than or equal to 0,
                //program keeps asking for a valid input
                if (isItPos <= 0) {
                    out.print(
                            "Please enter a positive real number to approximate: ");
                    constant = in.nextLine();
                    //once valid input is entered method returns the pos number
                } else {
                    return isItPos;
                }
            }
        }
        return isItPos;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */

    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        //initial message to user for first personal number
        out.print("Enter a personal number: ");
        //initializing as string to check and convert if its valid
        String w = in.nextLine();
        //initializing variable to used in loop for validity of numbers value
        double posNonOne = 0;
        //loops to get each of the 4 required user numbers, continues to loop until
        //user enters a non one and positive number
        //value of a number is returned to main and stored in array,
        //method is called again for next number and repeats process until
        //last method is called
        while (posNonOne <= 0 || posNonOne == 1.0) {
            //check if user input is a number
            if (FormatChecker.canParseDouble(w)) {
                //setting value equal to user input to be returned to main and stored
                posNonOne = Double.parseDouble(w);
                //conditional to check if input is non one and positive
                //will keep looping for each number needed until valid
                if (posNonOne == 1.0 || posNonOne <= 0) {
                    out.print(
                            "Please enter a any positive real number besides one: ");
                    w = in.nextLine();
                    //once valid message to let user know, and is returned to main
                } else {
                    out.println("That number is valid.");
                    return posNonOne;
                }
            }
        }
        return posNonOne;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //calling method to get user inputted constant and storing as double
        double constant = getPositiveDouble(in, out);
        //first call to method for first personal number, and storing value as
        //double
        double personalNum = getPositiveDoubleNotOne(in, out);
        //initializing array to store personal numbers user enters
        double[] personalNumbers = new double[4];
        //setting first array value to first user entered number and so on
        personalNumbers[0] = personalNum;
        //next call to method for second personal number to store in array, etc
        double personalNum2 = getPositiveDoubleNotOne(in, out);
        personalNumbers[1] = personalNum2;
        double personalNum3 = getPositiveDoubleNotOne(in, out);
        personalNumbers[2] = personalNum3;
        double personalNum4 = getPositiveDoubleNotOne(in, out);
        personalNumbers[3] = personalNum4;
        //storing given possible exponent numbers to be used in jager formula
        double[] charmNums = { -5, -4, -3, -2, -1, (-1 / 2), (-1 / 3), (-1 / 4),
                0, (1 / 4), (1 / 3), (1 / 2), 1, 2, 3, 4, 5 };
        //initialzing variables to be used to increment and decrement through
        //the possible exponent numbers to find optimal ones for closest approx
        int minW = 0;
        int minX = 0;
        int minY = 0;
        int minZ = 0;
        //Initializing variables to be used in conversion to calculate formula
        //will set them to value of desired index in exponent array
        double exp1 = 0;
        double exp2 = 0;
        double exp3 = 0;
        double exp4 = 0;
        //initializing double to be used in loop to calculate relative error
        //of the optimal approximation for user inputs
        double relError = 0;
        //making these double values equal to values of user inputted numbers
        //to output the values properly
        double firstNum = personalNumbers[0];
        double secondNum = personalNumbers[1];
        double thirdNum = personalNumbers[2];
        double fourthNum = personalNumbers[3];
        //output messages to state user inputs
        out.println(
                "The mathematical constant you choose to approximate with the charming theory is: "
                        + constant);
        out.println(
                "The numbers you choose to be used in the jager formula calculations are "
                        + firstNum + ", " + secondNum + ", " + thirdNum + ", "
                        + fourthNum);
        //loop keeps looping through until an approximation is finally derived thats
        //within the desired relative error, program then outputs approximation value
        //as well as the exponents used to derive it, and the user inputs
        //loop increments and decrements values for each personal numbers exponents
        //to keep calculating approximations until the best combination is found
        //that is within the desired error
        while (minW < charmNums.length) {
            minX = 0;

            while (minX < charmNums.length) {
                minY = 0;

                while (minY < charmNums.length) {
                    minZ = 0;

                    while (minZ < charmNums.length) {
                        exp1 = charmNums[minW];
                        exp2 = charmNums[minX];
                        exp3 = charmNums[minY];
                        exp4 = charmNums[minZ];
                        //jager formula approx calculation
                        double approx = Math.pow(firstNum, exp1)
                                * Math.pow(secondNum, exp2)
                                * Math.pow(thirdNum, exp3)
                                * Math.pow(fourthNum, exp4);
                        //relative error formula
                        relError = ((constant - approx) / constant) * 100;
                        //conditional that checks if a certain combo of new exponents
                        //is within the relative error, if its not keeps looping
                        //if its within error, then outputs the exponents
                        //used to get there and the approx and error and ends
                        if (Math.abs(relError) < .1) {
                            out.println("The optimal value of exponent a is: "
                                    + exp1);
                            out.println("The optimal value of exponent b is: "
                                    + exp2);
                            out.println("The optimal value of exponent c is: "
                                    + exp3);
                            out.println("The optimal value of exponent d is: "
                                    + exp4);
                            String strDouble = String.format("%.11f", approx);
                            out.println(
                                    "The approximation for your inputted constant is: "
                                            + strDouble);
                            out.print(
                                    "The relative error of the approximation is: "
                                            + Math.abs(relError));
                            break;
                        }
                        minZ++;
                    }
                    minY++;
                }
                minX++;
            }
            minW++;
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
