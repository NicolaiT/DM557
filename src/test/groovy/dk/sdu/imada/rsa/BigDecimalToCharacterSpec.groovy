package dk.sdu.imada.rsa

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class BigDecimalToCharacterSpec extends Specification {

    @Unroll
    void "Check exception is thrown for invalid input: #input"() {
        when:
        CharacterConversion.numberToChar(input)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Input must be in a BigInteger in the interval [0:36]"

        where:
        input << [null,-1,37,100]
    }

    @Unroll
    void "Check input that map to numbers can be converted correct: #input = #expected"() {
        expect:
        CharacterConversion.numberToChar(input) == expected

        where:
        input   | expected
        0       | '0'
        5       | '5'
        9       | '9'
    }

    @Unroll
    void "Check input that map to letters can be converted correct: #input = #expected"() {
        expect:
        CharacterConversion.numberToChar(input) == expected

        where:
        input   | expected
        10      | 'a'
        11      | 'b'
        35      | 'z'
    }

    void "Check input that map to space is converted correct"() {
        expect:
        CharacterConversion.numberToChar(36) == ' '
    }

}
