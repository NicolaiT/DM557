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
        BigInteger result;
        String resultString = "";
        String substring = "";
        for(int i = 0; i < plaintext.length(); i++) {
            while(plaintext.length() % 5 != 0) {
                plaintext = plaintext.concat(" ");
            }            if(substring.length() % 5 != 0 || substring.length() == 0) {
                Character character = plaintext.charAt(i);
                substring = substring.concat(character.toString());
            } else {
                result = AlphabetConversion.stringToNumber(substring);
                result = result.modPow(e, n);
                resultString = resultString.concat(result.toString() + "\n");
                substring = "";
                i--;
            }
        }
        result = AlphabetConversion.stringToNumber(substring);
        result = result.modPow(e, n);
        resultString = resultString.concat(result.toString() + '\n');

        return resultString;
    }

    /**
     * Decrypt using the private key. The ciphertext has a block of encrypted text on each line.
     * @param cipherText Encrypted text to decrypt
     * @return Decrypted plaintext message
     */
    public String decrypt(String cipherText) {
        BigInteger result;
        String finalResult = "";
        String[] numbers = cipherText.split("\n");
        for (String number : numbers) {
            result = new BigInteger(number);
            result = result.modPow(d, n);
            String stringResult = AlphabetConversion.numberToString(result);
            finalResult = finalResult.concat(stringResult.toString());
        }
        return finalResult;
    }
}
