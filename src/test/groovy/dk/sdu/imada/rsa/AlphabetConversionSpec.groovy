package dk.sdu.imada.rsa

import spock.lang.Unroll

class AlphabetConversionSpec extends spock.lang.Specification{

	@Unroll
	void "Check that numberToString converts correctly '#input' => '#output'"() {
		expect:
		AlphabetConversion.numberToString(new BigInteger(input) ) == output

		where:
		input       || output
		'0'  		|| '00000'
		'32822818'  || 'hi s7'
		'69343956'  || '     '
		'43855754'  || 'netwo'

	}

	@Unroll
	void "Check that stringToNumber converts correctly '#input' => #output"() {
		expect:
		AlphabetConversion.stringToNumber( input ) == output

		where:
		input    || output
		'00000'  || 0
		'     '  || 69343956
		'hi s7'  || 32822818
		'netwo'  || 43855754

	}

}
