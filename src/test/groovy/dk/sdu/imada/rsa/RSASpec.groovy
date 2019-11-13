package dk.sdu.imada.rsa

import spock.lang.Unroll;

class RSASpec extends spock.lang.Specification {

	@Unroll
	void "Test d is correct set when values are given RSA(13,17,#chosenE) => d=#d"() {
		given:
		RSA rsa = new RSA(13,17,chosenE);

		expect:
		rsa.d == d

		where:
		chosenE || d
		5       || 77
		7       || 55
		11      || 35
	}

	@Unroll
	void "Test encryption only with small example '#text'=>'#result'"() {
		given:
		RSA rsa = new RSA(13,17,5);

		expect:
		rsa.encryptMessage(text) == result

		where:
		text         || result
		"00001"      || "1\n"
		"0000a"      || "108\n"
		"000050000g" || "31\n152\n"
	}

	@Unroll
	void "Test decryption only with small example '#text' => '#result'"() {
		given:
		RSA rsa = new RSA(13,17,5);

		expect:
		rsa.decrypt(text) == result

		where:
		text        || result
		"1\n"       || "00001"
		"31\n152\n" || "000050000g"
	}


	void "Test encryption and decryption"() {
		given:
		RSA rsa = new RSA(1000);
		String text = "this is a hello world encrypted message"

		when:
		String ciphertext = rsa.encryptMessage(text);

		then:
		rsa.decrypt(ciphertext).startsWith(text)
	}
}
