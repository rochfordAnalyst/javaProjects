import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Brian Rochford
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */
    //test of lower bound
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    //routine test
    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    //large value upper bound test
    @Test
    public void testReduceToGCD_1404_4878() {
        NaturalNumber n = new NaturalNumber2(1404);
        NaturalNumber nExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(4878);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);

    }

    /*
     * Tests of isEven
     */
    //test of lower bound
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    //routine
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    //large value upper bound test
    @Test
    public void testIsEven_123456788() {
        NaturalNumber n = new NaturalNumber2(123456788);
        NaturalNumber nExpected = new NaturalNumber2(123456788);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */
    //testing lower bound

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //routine test
    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //large value upper bound test
    @Test
    public void testPowerMod_1223_4321_4567() {
        NaturalNumber n = new NaturalNumber2(1223);
        NaturalNumber nExpected = new NaturalNumber2(1829276567);
        NaturalNumber p = new NaturalNumber2(4321);
        NaturalNumber pExpected = new NaturalNumber2(4320);
        NaturalNumber m = new NaturalNumber2(4567);
        NaturalNumber mExpected = new NaturalNumber2(4567);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);

    }

    /*
     * Tests of isWitnessToCompositeness method
     */
    //lower bound test
    @Test
    public void testIsWitnessToCompositeness_2_90() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(90);
        assertEquals("2", w.toString());
        assertEquals("90", n.toString());
        boolean isIt = true;
        assertEquals(isIt, CryptoUtilities.isWitnessToCompositeness(w, n));

    }

    //routine test
    @Test
    public void testIsWitnessToCompositeness_10_77() {
        NaturalNumber w = new NaturalNumber2(10);
        NaturalNumber n = new NaturalNumber2(77);
        assertEquals("10", w.toString());
        assertEquals("77", n.toString());
        boolean expected = false;
        assertEquals(expected, CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    //large value test (upper bound)
    @Test
    public void testIsWitnessToCompositeness_100_1020() {
        NaturalNumber w = new NaturalNumber2(100);
        NaturalNumber n = new NaturalNumber2(1020);
        assertEquals("100", w.toString());
        assertEquals("1020", n.toString());
        boolean isIt = true;
        assertEquals(isIt, CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /*
     * Tests of isPrime1 method
     */
    //lower bound test
    @Test
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean isIt = true;
        assertEquals(isIt, CryptoUtilities.isPrime1(n));
    }

    //routine test
    @Test
    public void testIsPrime_72() {
        NaturalNumber n = new NaturalNumber2(72);
        boolean isIt = false;
        assertEquals(isIt, CryptoUtilities.isPrime1(n));
    }

    //large upper limit test
    @Test
    public void testIsPrime1_1234567891() {
        NaturalNumber n = new NaturalNumber2(1234567891);
        boolean isIt = true;
        assertEquals(isIt, CryptoUtilities.isPrime1(n));

    }

    /*
     * Tests of isPrime2 method
     */
    //lower bound test
    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean isIt = true;
        assertEquals(isIt, CryptoUtilities.isPrime2(n));
    }

    //routine test
    @Test
    public void testIsPrime2_811() {
        NaturalNumber n = new NaturalNumber2(811);
        boolean isIt = true;
        assertEquals(isIt, CryptoUtilities.isPrime2(n));
    }

    //large value upper bound test
    @Test
    public void testIsPrime2_4000() {
        NaturalNumber n = new NaturalNumber2(4000);
        boolean isItExpected = false;
        assertEquals(isItExpected, CryptoUtilities.isPrime2(n));
    }

    /*
     * Tests of generateNextLikelyPrime method
     */
    //lower bound test
    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber nExpected = new NaturalNumber2(3);
        assertEquals(nExpected, n);

    }

    //routine test
    @Test
    public void testGenerateNextLikelyPrime_857() {
        NaturalNumber n = new NaturalNumber2(857);
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber nExpected = new NaturalNumber2(859);
        assertEquals(nExpected, n);

    }

    //large value upper bound test
    @Test
    public void testGenerateNextLikelyPrime_14325() {
        NaturalNumber n = new NaturalNumber2(14325);
        CryptoUtilities.generateNextLikelyPrime(n);
        NaturalNumber nExpected = new NaturalNumber2(14327);
        assertEquals(nExpected, n);

    }

}
