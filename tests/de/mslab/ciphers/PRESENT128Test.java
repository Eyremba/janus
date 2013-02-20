package de.mslab.ciphers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.mslab.core.ByteArray;

public class PRESENT128Test extends AbstractCipherTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cipher = new PRESENT128();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		cipher = null;
	}
	
	@Test
	public void testDecrypt() {
		int[] key = 		{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] plaintext = 	{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] ciphertext = 	{ 0x62, 0x8d, 0x9f, 0xbd, 0x42, 0x18, 0xe5, 0xb4 };
		testDecryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testDecryptEmptyValues() {
		int[] key = 		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] plaintext = 	{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] ciphertext = 	{ 0x96, 0xdb, 0x70, 0x2a, 0x2e, 0x69, 0x00, 0xaf };
		testDecryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testDecryptEmptyPlaintext() {
		int[] key = 		{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] plaintext = 	{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] ciphertext = 	{ 0x13, 0x23, 0x8c, 0x71, 0x02, 0x72, 0xa5, 0xd8 };
		testDecryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testDecryptEmptyKey() {
		int[] key = 		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] plaintext = 	{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] ciphertext = 	{ 0x3c, 0x60, 0x19, 0xe5, 0xe5, 0xed, 0xd5, 0x63 };
		testDecryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testEncrypt() {
		int[] key = 		{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] plaintext = 	{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] ciphertext = 	{ 0x62, 0x8d, 0x9f, 0xbd, 0x42, 0x18, 0xe5, 0xb4 };
		testEncryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testEncryptEmptyValues() {
		int[] key = 		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] plaintext = 	{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] ciphertext = 	{ 0x96, 0xdb, 0x70, 0x2a, 0x2e, 0x69, 0x00, 0xaf };
		testEncryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testEncryptEmptyPlaintext() {
		int[] key = 		{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] plaintext = 	{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] ciphertext = 	{ 0x13, 0x23, 0x8c, 0x71, 0x02, 0x72, 0xa5, 0xd8 };
		testEncryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testEncryptEmptyKey() {
		int[] key = 		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		int[] plaintext = 	{ 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
		int[] ciphertext = 	{ 0x3c, 0x60, 0x19, 0xe5, 0xe5, 0xed, 0xd5, 0x63 };
		testEncryption(key, plaintext, ciphertext);
	}
	
	@Test
	public void testShift() {
		long input = 0xcc00000000000000L;
		long actual = input >>> 3;
		long expected = 0x1980000000000000L;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExpandKey() {
		/*int[] expandedKeyShouldBe = { // Each line contains two 64 bit round keys
			0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0xcc,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0xc3,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0xc8,0x30,0x00,0x00,0x00,0x00,0x00,0x00,0x5b,0x0c,0x00,0x00,0x00,0x00,0x00,0x00,
			0x58,0x20,0xc0,0x00,0x00,0x00,0x00,0x01,0x65,0x6c,0x30,0x00,0x00,0x00,0x00,0x01,
			0x6e,0x60,0x83,0x00,0x00,0x00,0x00,0x01,0xb5,0x95,0xb0,0xc0,0x00,0x00,0x00,0x01,
			0xbe,0xb9,0x82,0x0c,0x00,0x00,0x00,0x02,0x96,0xd6,0x56,0xc3,0x00,0x00,0x00,0x02,
			0x9f,0xfa,0xe6,0x08,0x30,0x00,0x00,0x02,0x06,0x5b,0x59,0x5b,0x0c,0x00,0x00,0x02,
			0x0f,0x7f,0xeb,0x98,0x20,0xc0,0x00,0x03,0xac,0x19,0x6d,0x65,0x6c,0x30,0x00,0x03,
			0xa3,0x3d,0xff,0xae,0x60,0x83,0x00,0x03,0xd6,0xb0,0x65,0xb5,0x95,0xb0,0xc0,0x03,
			0xdf,0x8c,0xf7,0xfe,0xb9,0x82,0x0c,0x04,0x3b,0x5a,0xc1,0x96,0xd6,0x56,0xc3,0x04,
			0x38,0x7e,0x33,0xdf,0xfa,0xe6,0x08,0x34,0xec,0xed,0x6b,0x06,0x5b,0x59,0x5b,0x08,
			0x53,0xe1,0xf8,0xcf,0x7f,0xeb,0x98,0x25,0x88,0xb3,0xb5,0xac,0x19,0x6d,0x65,0x69,
			0x67,0x4f,0x87,0xe3,0x3d,0xff,0xae,0x65,0xf6,0x22,0xce,0xd6,0xb0,0x65,0xb5,0x90,
			0xc7,0x9d,0x3e,0x1f,0x8c,0xf7,0xfe,0xbf,0x2b,0xd8,0x8b,0x3b,0x5a,0xc1,0x96,0xd0,
			0xcb,0x1e,0x74,0xf8,0x7e,0x33,0xdf,0xfc,0x34,0xaf,0x62,0x2c,0xed,0x6b,0x06,0x5d,
			0x8b,0x2c,0x79,0xd3,0xe1,0xf8,0xcf,0x78,0x54,0xd2,0xbd,0x88,0xb3,0xb5,0xac,0x1e,
			0x4a,0x2c,0xb1,0xe7,0x4f,0x87,0xe3,0x3a,0x97,0x53,0x4a,0xf6,0x22,0xce,0xd6,0xb7
		};*/
		// Each line contains two 80 bit register states.
		// The leftmost 64 bits are used as the round key. 
		long[] fullKeyRegisterStates = {
			0x0L, 0x0L, 
			0xcc00000000000000L,0x4000000000000000L,
			0xc300000000000000L,0x9980000000000000L,
			0x5b30000000000000L,0xd860000000000000L,
			0x580c000000000001L,0x0b66000000000000L,
			0x656cc00000000001L,0x4b01800000000000L,
			0x6e60300000000001L,0x8cad980000000000L,
			0xb595b30000000001L,0xcdcc060000000000L,
			0xbeb980c000000002L,0x16b2b66000000000L,
			0x96d656cc00000002L,0x57d7301800000000L,
			0x9ffae60300000002L,0x92dacad980000000L,
			0x065b595b30000002L,0xd3ff5cc060000000L,
			0x0f7feb980c000003L,0x00cb6b2b66000000L,
			0xac196d656cc00003L,0x41effd7301800000L,
			0xa33dffae60300003L,0x95832dacad980000L,
			0xd6b065b595b30003L,0xd467bff5cc060000L,
			0xdf8cf7feb980c004L,0x1ad60cb6b2b66000L,
			0x3b5ac196d656cc04L,0x5bf19effd7301800L,
			0x387e33dffae60304L,0x876b5832dacad980L,
			0xeced6b065b595b34L,0xc70fc67bff5cc060L,
			0xe3e1f8cf7feb9809L,0x1d9dad60cb6b2b66L,
			0x6bb3b5ac196d6569L,0x9c7c3f19effd7301L,
			0xbb8f87e33dffae65L,0xad7676b5832dacadL,
			0x80aeced6b065b590L,0x7771f0fc67bff5ccL,
			0xc1ee3e1f8cf7febfL,0x9015d9dad60cb6b2L,
			0x2602bb3b5ac196d0L,0x183dc7c3f19effd7L,
			0xcb07b8f87e33dffcL,0x64c057676b5832daL,
			0x34980aeced6b065dL,0x9960f71f0fc67bffL,
			0x8b2c1ee3e1f8cf78L,0xe693015d9dad60cbL,
			0x54d2602bb3b5ac1eL,0x316583dc7c3f19efL,
			0x4a2cb07b8f87e33aL,0x6a9a4c057676b583L,
			0x97534980aeced6b7L,0xa945960f71f0fc67L
		};
		
		ByteArray secretKey = new ByteArray(fullKeyRegisterStates);
		ByteArray keyPart;
		ByteArray foo;
		ByteArray computedExpandedKey;
		int from, to;
		long[] registerState;
		
		for (int roundIndex = 1; roundIndex <= cipher.getNumRounds(); roundIndex++) {
			from = 2 * (roundIndex - 1);
			to = from + 2;
			registerState = Arrays.copyOfRange(fullKeyRegisterStates, from, to);
			// logger.info("round {0} register {1}", roundIndex, Formatter.longArrayToHexStrings(registerState));
			keyPart = new ByteArray(registerState);
			computedExpandedKey = cipher.computeExpandedKey(keyPart, roundIndex);
			foo = computedExpandedKey.clone();
			foo.xor(secretKey);
			logger.info("round {0} \n{1}", roundIndex, computedExpandedKey);
			assertTrue(secretKey.equals(computedExpandedKey));
		}
	}
	
}