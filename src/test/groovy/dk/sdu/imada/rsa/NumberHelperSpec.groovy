package dk.sdu.imada.rsa

import spock.lang.Unroll

class NumberHelperSpec  extends spock.lang.Specification{

	@Unroll
	void "Test extendedEuclideanAlgorithm eua(#a,#b) => [#gcd, #s, #t]"() {
		when:
		BigInteger[] result = NumberHelpers.extendedEuclideanAlgorithm(a,b)

		then:
		result[0] == gcd
		result[1] == s
		result[2] == t

		where:
		a   | b     || gcd  | s     | t
		75  | 28    || 1    | 3     | -8
		13  | 2436  || 1    | 937   | -5
	}

	@Unroll
	void "Test recursiveModularExponentiation modexp(#base, #pow, #modulus) = #expected"() {
		expect:
		NumberHelpers.recursiveModularExponentiation(base, pow, modulus) == expected

		where:
		base    | pow   | modulus   || expected
		2       | 644   | 645       || 1
		123     | 1001  | 101       || 22
	}

}
