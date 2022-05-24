import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems. Checking these Utilities
 * from all the methods in this program with Junit testing to make sure they run
 * the way the requires and ensures clauses states they should run. The
 * CryptoGraphic methods include a random number generator method, a greatest
 * common divisor method, checking if a number is even method, a power modulo
 * method, a composite number checking method, 2 similar prime number method
 * checkers, and a method that generates the next likely prime number from the
 * number inputed
 *
 * @author Brian Rochford
 *
 */
public final class CryptoUtilities {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilities() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     *
     * @param n
     *            top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     * </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result;
        int d = n.divideBy10();
        if (n.isZero()) {
            /*
             * Incoming n has only one digit and it is d, so generate a random
             * number uniformly distributed in [0, d]
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);
        } else {
            /*
             * Incoming n has more than one digit, so generate a random number
             * (NaturalNumber) uniformly distributed in [0, n], and another
             * (int) uniformly distributed in [0, 9] (i.e., a random digit)
             */
            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);
            if (result.compareTo(n) > 0) {
                /*
                 * In this case, we need to try again because generated number
                 * is greater than n; the recursive call's argument is not
                 * "smaller" than the incoming value of n, but this recursive
                 * call has no more than a 90% chance of being made (and for
                 * large n, far less than that), so the probability of
                 * termination is 1
                 */
                result = randomNumber(n);
            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     *
     * @param n
     *            one number
     * @param m
     *            the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Using Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */
        NaturalNumber n1 = new NaturalNumber2(n);
        NaturalNumber m1 = new NaturalNumber2(m);
        //initializing a natural number of 1 to compare and check with n and m below
        //and to use for copying values, and conditional to hold loops
        NaturalNumber rep = new NaturalNumber2(1);
        NaturalNumber N2 = new NaturalNumber2(n);
        NaturalNumber M2 = new NaturalNumber2(m);
        //conditional to see if n or m is 0, if one is clearing n
        if (n.isZero() | m.isZero()) {
            n.clear();
        } else {
            //conditional to see if n is greater than m, if compareTo returns 1
            //than n is greater than m
            if (n1.compareTo(m1) > 0) {
                //loops until transfer of remainder becomes zero for rep
                while (!rep.isZero()) {
                    NaturalNumber remain = new NaturalNumber2(N2.divide(M2));
                    NaturalNumber rep2 = new NaturalNumber2(remain);
                    N2.transferFrom(M2);
                    M2.transferFrom(remain);
                    rep.transferFrom(rep2);
                }
                n.transferFrom(N2);
            } else {
                //loops until transfer of remainder becomes zero for rep
                while (!rep.isZero()) {
                    NaturalNumber remain2 = new NaturalNumber2(M2.divide(N2));
                    NaturalNumber rep3 = new NaturalNumber2(remain2);
                    M2.transferFrom(N2);
                    N2.transferFrom(remain2);
                    rep.transferFrom(rep3);
                }
                n.transferFrom(M2);
            }

        }
        m.clear();

    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {

        NaturalNumber n2 = new NaturalNumber2(n);
        NaturalNumber two = new NaturalNumber2(2);
        boolean checkEven = false;
        n2.transferFrom(n2.divide(two));
        //this checks the remainder of whats left after dividing n by 2 with
        //transfer from above, if whats left is 0 then its even
        if (n2.isZero()) {
            checkEven = true;
        }
        //if conditional holds then this will return true, otherwise its set to
        //false so no other conditional needed for odd number check
        return checkEven;
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        NaturalNumber n2 = new NaturalNumber2(n);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        /*
         * Using conditional if else statements, calling static methods isEven
         * and powerMod to successfully apply the fast-powering algorithm with
         * the additional feature of reducing the result modulo "m" after every
         * multiplication used in the algo
         *
         */
        //conditional if p is 0, clear n and increment it by 1
        if (p.isZero()) {
            n.clear();
            n.increment();
            //if p equals one, dividing n by m and setting it to be new value of n
        } else if (p.equals(one)) {
            n.transferFrom(n.divide(m));

        } else if (isEven(p)) {
            powerMod(n, p.divide(two), m);
            p.multiply(two);
            NaturalNumber n3 = new NaturalNumber2(n);
            n.multiply(n3);

        } else {
            powerMod(n, p.divide(two), m);
            p.multiply(two);
            NaturalNumber n4 = new NaturalNumber2(n);
            n.multiply(n4);
            n.multiply(n2);
        }

    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     *
     * @param w
     *            witness candidate
     * @param n
     *            number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2 and 1 < w < n - 1
     * @ensures <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     * </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w,
            NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();
        //initializing object that will be the return value
        boolean isWit = false;
        NaturalNumber n1 = new NaturalNumber2(n);
        NaturalNumber n2 = new NaturalNumber2(n);
        NaturalNumber w1 = new NaturalNumber2(w);
        NaturalNumber w2 = new NaturalNumber2(w);
        NaturalNumber two = new NaturalNumber2(2);
        //calling power mod method for use in Fermats theoreom application
        powerMod(w1, two, n1);
        //if w is not 0 then decrement it by 1
        if (!w1.isZero()) {
            w1.decrement();
            //if w returns as 0 from mod method call then it is compositeness
            if (w1.isZero()) {
                isWit = true;
            }
        }
        // or is witness to compositeness can also be derived as such
        n2.decrement();
        powerMod(w2, n2, n1);
        if (!w2.isZero()) {
            w2.decrement();
            if (w2.isZero()) {
                isWit = false;
            }
        }

        return isWit;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        boolean isPrime;
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5: simply check whether 2 is a witness that n is
             * composite (which works surprisingly well :-)
             */
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        /*
         * returns if n is a prime with near certainty, going to be using
         * conditional if else statements, nested while loops within the if
         * statements, calling of the randomNumber method and calling of the
         * isWitnessToCompositeness method as well NaturalNumber methods such as
         * divideBy10 to determine if n is a prime number with small margin of
         * error or if its composite with no chance of error
         */
        boolean isIt = true;
        NaturalNumber one = new NaturalNumber2(1);
        //used to generate the witness candidates, for guessing that n is prime
        //only if none of the candidates used is a witness to n being composite
        final int gen50 = 50;
        //if n is less than or equal to three, 2 and 3 are primes so.
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            isIt = true;
            //all even numbers are composite besides 2 so
        } else if (isEven(n)) {
            isIt = false;

        } else {
            NaturalNumber n2 = n.newInstance();
            int i = 1;
            while (i < gen50) {
                n2.copyFrom(n);
                n2.decrement();
                //if n is prime at this point
                if (isIt) {
                    NaturalNumber w = randomNumber(n);
                    //applying isWitnessToCompositeness equality check here
                    if (w.compareTo(one) > 0 && w.compareTo(n2) < 0) {
                        //if its not composite then it has to be prime so
                        isIt = !isWitnessToCompositeness(w, n);
                    }
                    //if composite, loop terminates
                } else {
                    break;
                }
                i++;
            }
        }
        return isIt;
    }

    /**
     * Generates a likely prime number at least as large as some given number.
     *
     * @param n
     *            minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n and [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        //NaturalNumber two = new NaturalNumber2(2);
        boolean isIt = false;
        /*
         * Using the calling of isPrime2 method and isEven method to check
         * numbers, starting at n and incrementing by 1 or 2 (to increase
         * through odd numbers only, because all prime numbers are odd besides
         * 2. because an even number is divisible by 2 which makes it composite.
         *
         *
         */
        //looping until the isPrime2 method returns true that n is a prime number
        while (!isIt) {
            //increments n every time its a even number
            if (isEven(n)) {
                n.increment();
                //if n is odd adding 2 each time
            } else {
                n.increment();
                n.increment();

            }
            isIt = isPrime2(n);
        }

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

        /*
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println("  expected value = "
                + (double) testSamples / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */
        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}