package dk.sdu.imada.rsa

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class CharacterToBigDecimalSpec extends Specification {

    @Unroll
    void "Check exception is thrown for invalid input: '#input'"() {
        when:
        CharacterConversion.charToNumber(input as char)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Invalid input: '${input}' - input must be a-z, 0-9 or space"

        where:
        input << ['A', '-', '#', 'æ']
    }

    @Unroll
    void "Check numbers is converted correct: #input -> #expected"() {
        expect:
        CharacterConversion.charToNumber(input as char) == expected

        where:
        input   | expected
        '0'     | 0
        '9'     | 9
    }

    @Unroll
    void "Check letter is converted correct: #input -> #expected"() {
        expect:
        CharacterConversion.charToNumber(input as char) == expected

        where:
        input   | expected
        'a'     | 10
        'z'     | 35
    }

    @Unroll
    void "Check space is converted correct"() {
        expect:
        CharacterConversion.charToNumber(' ' as char) == 36
    }

}
