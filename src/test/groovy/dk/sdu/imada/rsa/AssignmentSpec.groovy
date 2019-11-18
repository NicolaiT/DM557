package dk.sdu.imada.rsa;

class AssignmentSpec extends spock.lang.Specification {

	void "test euclidean algorithm"() {
		setup:
		BigInteger a = 123456544
		BigInteger b = 9955487

		when:
		BigInteger[] result = NumberHelpers.extendedEuclideanAlgorithm(123456544,9955487)
		BigInteger r = result[0]
		BigInteger s = result[1]
		BigInteger t = result[2]

		println "gcd($a , $b) is [$r , $s , $t]"
		println "So $s * $a + $t * $b = $r"
		println "r = $r"
		println "s = $s"
		println "t = $t"

		then:
		s*a + t*b == r
	}
	void "Test encryption"() {
		given:
		RSA rsa = new RSA(1156561771779226504385211474703634660706188015894269578454843,
				1369432560389383877017373383790225389216006713827806996905907,
				47431565080882922701)

		when:
		String encrypted = rsa.encryptMessage("what a lovely little coding exercise this is")
		println "The encrypted message for 'what a lovely little coding exercise this is' is\n$encrypted"

		then:
		encrypted == """163539282851503430713649281058826017605780033897423242881455432662877632018769478554353123812963594791485722281476322756
202574766722208136313166663476564327834649003680637924023400022051716599540600055216461598632129559212761631470750669420
598960474564883367427080207709063755391591555682231814415513679734554747491160863687394585193290492889732805804107861555
220320145777862357441738877551067816692274577052115135074001448332611340178117207579718665466624807488912315490524200505
271582059888190233971804329802513179736903028870275062782161310802841999449479119326303821559863934078329429119891352357
1274506126335098257279145700985962069205914183404605046759957752207099277163051066194993909105653141473666302053261269506
1207112574213818870057677547911566678577137976266756261325173475348431775451185421263551412603804342392764225452068471571
287798322661892771800896866760889466548605993764324633734788160082572355642668947740262567924979636127922885225467414340
680340601307072977008828883968687722500620815383938859762893080409916789275240486679313430943956727992275053358071058825
"""

	}


	void "Test decryption"() {
		given:
		RSA rsa = new RSA(1156561771779226504385211474703634660706188015894269578454843,
				1369432560389383877017373383790225389216006713827806996905907,
				47431565080882922701)

		when:
		String ciphertext = """163539282851503430713649281058826017605780033897423242881455432662877632018769478554353123812963594791485722281476322756
1403088031255559788723512901657758136170056090141319566727530852705583581269998099387206638350077870095092242176348918069
160739623481607389014498189641816753489485837619050842354751944615037232215672505810744270521558551954216885911976767837
1362288427881790052640868072591543768924759105448001010174526644364994995822154399865380639790114884117983728189070514561
906627797097826904369253078816059979692649219583221706955726703240165882975063108357575970281958002921696877657475176625
1370521590730394245054191956703148163654652507411227750317194825895537391417969895809268529695964616324077003096585236684
772614893989252917154634048744147735301791670782651126965222754352798794618140026965158355895963968925424327853022469097
792002950344722191817913285636286157041467873447177930399198278775574356394295166300182427379794453772463012030520124511
57495846212629609070693802219532188659957277245478731680688159787712298979944396175336094357823836882892806028162228941
1040685262590840101049494642084067503438563912789414012558788670307896343097681035008484536543138834311568478973226575746
373027859796576198248309859640432154037525939411862492279129495640839583762381801992328202520688569550384199396455679537
1037588909346988937458915062798696942157432829863519571000308443146335211615382768295429821260735178460732788678585288665
1112392055920577475788615568592667698992173284962034547515487345407013744750589177619165040894940481041228218080553164019
689569537429781615303046024463216063837759551563546328579723949756532970641465795305095185890426458231159030495376357412
984456805688371298158902771621473150863330165777863304891484348897932827709173775234151036713636728544566396278536323964
"""


		then:
		println "The ciphertext decrypted is:"
		println rsa.decrypt(ciphertext)
		rsa.decrypt(ciphertext) == "what is the best thing about switzerland i dont know but the flag is a plus"
	}
}
