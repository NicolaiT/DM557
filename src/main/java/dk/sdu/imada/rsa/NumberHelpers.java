package dk.sdu.imada.rsa;

import java.math.BigInteger;

public class NumberHelpers {

	private static final BigInteger TWO = new BigInteger("2");

	/**
	 * Extended euclidean algorithm
	 * @param a
	 * @param b
	 * @return [r,s,t] where r = gcd(a,b) and sa + tb = r
	 */
	public static BigInteger[] extendedEuclideanAlgorithm(BigInteger a, BigInteger b) {
		BigInteger a0 = a;
		BigInteger b0 = b;
		BigInteger t0 = BigInteger.valueOf(0);
		BigInteger t = BigInteger.valueOf(1);
		BigInteger s0 = BigInteger.valueOf(1);
		BigInteger s = BigInteger.valueOf(0);
		BigInteger q = a0.divide(b0);
		BigInteger r = a0.subtract(q.multiply(b0));

		int compareValue = r.compareTo(BigInteger.ZERO);

		while(compareValue == 1) {
			BigInteger temp = t0.subtract(q.multiply(t));
			t0 = t;
			t = temp;
			temp = s0.subtract(q.multiply(s));
			s0 = s;
			s = temp;
			a0 = b0;
			b0 = r;
			q = (a0.divide(b0));
			r = a0.subtract(q.multiply(b0));
			compareValue = r.compareTo(BigInteger.ZERO);
		}
		r = b0;

		BigInteger[] results = new BigInteger[3];

		results[0] = r;
		results[1] = s;
		results[2] = t;

		return results;
	}

	/**
	 * Method handling modular exponentiation efficiently
	 * @param base
	 * @param pow
	 * @param modulus
	 * @return BigDecimal with the value of base^pow mod modulus
	 */
	public static BigInteger recursiveModularExponentiation(BigInteger base, BigInteger pow, BigInteger modulus) {
		if (pow == BigInteger.ZERO)
			return BigInteger.ONE;
		if (pow == BigInteger.ONE)
			return base;
		if (pow.mod(BigInteger.valueOf(2)) == BigInteger.ZERO) {
			BigInteger temp = recursiveModularExponentiation(base, pow.divide(BigInteger.valueOf(2)), modulus);
			return temp.multiply(temp).mod(modulus);
		} else {
			float newPow = pow.floatValue()/2;
			long newPowInteger = (long) Math.floor(newPow);
			BigInteger temp = recursiveModularExponentiation(base, BigInteger.valueOf(newPowInteger), modulus);
			return base.multiply(temp).multiply(temp).mod(modulus);
		}
	}
}
