package dk.sdu.imada.rsa;

import java.math.BigInteger;
import java.lang.Exception;

public class CharacterConversion {

    /**
     * Converts a char to a BigInteger according to this alphabet conversion:
     *  0-9 should return their value  0->0, 5->5, 9->9
     *  a should map to 10, b to 11, c to 12 etc. up to z->35
     *  Space should map to 36
     * If a char not in the alphabet [ a-z0-0] is input, an IllegalArgumentException must be thrown
     * @param c Input char
     * @return BigInteger value for the character conversion
     */
    public static BigInteger charToNumber(char c) {
        Integer numberChar = (int) c;
        if(47 < numberChar && numberChar < 58) {
            return new BigInteger(Integer.toString(numberChar - 48));
        } else if(96 < numberChar && numberChar < 123) {
            return new BigInteger(Integer.toString(numberChar - 87));
        } else if(numberChar == 32) {
            return new BigInteger(Integer.toString(numberChar + 4));
        } else {
            throw new IllegalArgumentException("Invalid input: " + "'" + c + "'" + " - input must be a-z, 0-9 or space");
        }
    }

    /**
     * Converts a number in the interval [0:36] to the corresponding char
     * The conversion should be the opposite as in the method above.
     * If a number not in the interval specified above is input, an IllegalArgumentException must be thrown
     * @param number The number to convert
     * @return The converted char
     */
    public static char numberToChar(BigInteger number) {
        if(number == null) {
            throw new IllegalArgumentException("Input must be in a BigInteger in the interval [0:36]");
        } else if(-1 < number.intValue() && number.intValue() < 10) {
            return (char) (number.intValue() + 48);
        } else if(9 < number.intValue() && number.intValue()  < 36) {
            return (char) (number.intValue() + 87);
        } else if(number.intValue() == 36) {
            return (char) (number.intValue() - 4);
        } else {
            throw new IllegalArgumentException("Input must be in a BigInteger in the interval [0:36]");
        }
    }

}
