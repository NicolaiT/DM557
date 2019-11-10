package dk.sdu.imada.rsa;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        RSA rsa = new RSA(new BigInteger("1156561771779226504385211474703634660706188015894269578454843"),
                new BigInteger("1369432560389383877017373383790225389216006713827806996905907"),
                new BigInteger("47431565080882922701"));

        String encrypted = rsa.encryptMessage("what a lovely little coding exercise this is");
        System.out.println( "The encrypted message for 'what a lovely little coding exercise this is' is\n " + encrypted);
    }
}
