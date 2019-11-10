package dk.sdu.imada.rsa;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    private Random random;
    private BigInteger primeOne;
    private BigInteger primeTwo;

    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger z;


    public RSA(Integer bits) {
        random = new Random();
        BigInteger primeOne = BigInteger.probablePrime(bits, random);
        BigInteger primeTwo = BigInteger.probablePrime(bits, random);
        setUpNumbers(primeOne,primeTwo, null);
    }

    public RSA(BigInteger primeOne, BigInteger primeTwo) {
        random = new Random();
        setUpNumbers(primeOne, primeTwo, null);
    }

    public RSA(BigInteger primeOne, BigInteger primeTwo, BigInteger e) {
        random = new Random();
        setUpNumbers(primeOne, primeTwo, e);
    }

    private void setUpNumbers(BigInteger primeOne, BigInteger primeTwo, BigInteger chosenE) {
        this.primeOne = primeOne;
        this.primeTwo = primeTwo;
        this.n = primeOne.multiply(primeTwo);

        z = (primeOne.subtract(BigInteger.ONE)).multiply(  (primeTwo.subtract(BigInteger.ONE)) );
        if( chosenE == null) {
            e = selectE(z);
        } else {
            if( !isValidE(chosenE, z) ) {
                throw new IllegalArgumentException("The chosen e is not relative prime to z");
            }
            e = chosenE;
        }
        d = selectD(e,z);
    }

    private boolean isValidE(BigInteger e, BigInteger z) {
        return NumberHelpers.extendedEuclideanAlgorithm(e, z)[0].equals(BigInteger.ONE);
    }

    private BigInteger selectE(BigInteger z) {
        BigInteger guess = BigInteger.probablePrime(primeOne.bitLength()/3, random);
        while( !isValidE(guess, z) ) {
            guess = guess.add(BigInteger.ONE);
        }
        return guess;
    }

    private BigInteger selectD(BigInteger e, BigInteger z) {
        BigInteger d = NumberHelpers.extendedEuclideanAlgorithm(e, z)[1];
        if( d.compareTo(BigInteger.ZERO) < 0) {
            return d.add(z);
        }
        return d;
    }

    /**
     * Encrypt using the public key. Each block of 5 characters are encrypted,
     * and placed in a separate line (i.e. followed by "\n"
     * @param plaintext The text to encrypt
     * @return The encrypted message
     */
    public String encryptMessage(String plaintext) {
        // TODO Implementation needed
        return null;
    }

    /**
     * Decrypt using the private key. The ciphertext has a block of encrypted text on each line.
     * @param cipherText Encrypted text to decrypt
     * @return Decrypted plaintext message
     */
    public String decrypt(String cipherText) {
        // TODO Implementation needed
        return null;
    }
}
