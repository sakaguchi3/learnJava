package com.github.sakaguchi3.jdk15.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.EdECPoint;
import java.security.spec.EdECPublicKeySpec;
import java.security.spec.NamedParameterSpec;

import org.junit.jupiter.api.Test;

public class Java15FeatureTest {

	@Test
	void switchExpressionTest() {
		var numInt = 3;
		switch (numInt) {
		case 1 -> System.out.println("one");
		case 2 -> System.out.println("two");
		case 3 -> System.out.println("three");
		default -> System.out.println("many");
		}

		var str = switch (numInt) {
		case 1 -> "one";
		case 3 -> "three";
		default -> "default";
		};

		assertEquals("three", str);

		var str1 = "Bar";
		int num1 = switch (str1) {
		case "Foo":
			yield 1;
		case "Bar":
			yield 2;
		default:
			System.out.println("Neither Foo nor Bar, hmmm...");
			yield 0;
		};
		assertEquals(2, num1);
	}

	@Test
	void lowMemoryAccessTest() {
//		try {
//			VarHandle intHandle = MemoryHandles.varHandle(int.class, ByteOrder.nativeOrder());
//
//			try (MemorySegment segment = MemorySegment.allocateNative(100)) {
//				MemoryAddress base = segment.baseAddress();
//				for (int i = 0; i < 25; i++) {
//		        intHandle.set(base.addOffset(i * 4), i);
//				}
//			}
//			System.out.println(intHandle);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test
	void patternMatchingTest() {
		Object obj = "";
//		if (obj instanceof String s) {
//			// can use s here
//		} else {
//			// can't use s here
//		}
//		if (!(obj instanceof String s)) {
////		    .. s.contains(..) ..
//		} else {
////		    .. s.contains(..) ..
//		}
//		if (obj instanceof String s && s.length() > 5) {
//		}
	}

	@Test
	void eccGenKeyPairTest() {
		try {
			// example: generate a key pair and sign
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("Ed25519");
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// use Ed25519 algorithm
			var signature = Signature.getInstance("Ed25519");
			signature.initSign(keyPair.getPrivate());
			var msg = "test".getBytes();
			signature.update(msg);
			byte[] s = signature.sign();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void eccUseTest() {
		try {
			// example: use KeyFactory to contruct a public key
			KeyFactory kf = KeyFactory.getInstance("EdDSA");
			boolean xOdd = true;
			BigInteger y = BigInteger.valueOf(39982323355L);
			NamedParameterSpec paramSpec = new NamedParameterSpec("Ed25519");
			EdECPublicKeySpec pubSpec = new EdECPublicKeySpec(paramSpec, new EdECPoint(xOdd, y));
			PublicKey pubKey = kf.generatePublic(pubSpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void textBlockTest() {
		String html1 = "<html>\n" + //
				"    <body>\n" + //
				"        <p>Hello, world</p>\n" + //
				"    </body>\n" + //
				"</html>\n";
		var html2 = """
				          <html>
				              <body>
				                  <p>Hello, world</p>
				              </body>
				          </html>
				""";

		System.out.println(html1);
		System.out.println(html2);
	}

	void debug() {
	}

}
