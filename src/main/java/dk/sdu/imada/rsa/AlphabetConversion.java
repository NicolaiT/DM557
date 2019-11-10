package dk.sdu.imada.rsa;

import java.math.BigInteger;
import java.util.ArrayList;

public class AlphabetConversion {

	/**
	 * Converts a number to a string, according to the alphabet conversion rules of the assignment
	 * @param number
	 * @return
	 */
	public static String numberToString(BigInteger number) {
		String result = "";
		int power = 4;
		int split = 0; // To ensure to print all the characters in a group
		BigInteger remainingNumbers = number;

		while (remainingNumbers.longValue() != (long) 0 || split != 5) {
			if(split == 5) {
				split = 0;
			}
			remainingNumbers = BigInteger.valueOf(remainingNumbers.longValue() % (long) Math.pow(37, power));
			System.out.println("Remaining numbers: " + remainingNumbers);

			BigInteger character =  number.subtract(remainingNumbers); // Character before dividing
			System.out.println("Character before dividing: " + character);

			character = BigInteger.valueOf(character.longValue()/(long) Math.pow(37, power)); // Use this character to convert to an actual char.
			System.out.println("Character in number: " + character);

			result += CharacterConversion.numberToChar(character);
			System.out.println("Result: " + result + "\n");
			split++;
			power--;
			number = remainingNumbers;

		}
		return result;
	}


	/**
	 * Convert a string of length 5 to a BigInteger number
	 * @param string The string to convert
	 * @return The converted number
	 */
	public static BigInteger stringToNumber(String string) {
		BigInteger total = BigInteger.valueOf(0);
		ArrayList<BigInteger> group = new ArrayList<>();
		int power = 4;
		int split = 0; // make a new group at 5.
		for (int i = 0; i < string.length(); i++) {
			group.add(BigInteger.valueOf(CharacterConversion.charToNumber(string.charAt(i)).intValue()));

			if(split == 5) {
				for (BigInteger big : group) {
					BigInteger powerof = BigInteger.valueOf((long)Math.pow(37,power));
					long temporaryLong = big.multiply(powerof).longValue();
					total = total.add(BigInteger.valueOf(temporaryLong));
					power--;
				}
				split = 0;
				power = 4;
				group = new ArrayList<>();
			}
				split++;
		}
		// If the group is not complete yet, then insert the free spaces with spaces.
		while(split != 0 && split != 5) {
			group.add(BigInteger.valueOf(CharacterConversion.charToNumber(' ').intValue()));
			split++;
		}
		for (BigInteger big : group) {
			BigInteger powerof = BigInteger.valueOf((long)Math.pow(37,power));
			long temporaryLong = big.multiply(powerof).longValue();
			total = total.add(BigInteger.valueOf(temporaryLong));
			power--;
		}

		return total;
	}



}
