/**
 * Copyright 2020 sakaguchi<uqw@outlook.jp>, https://github.com/sakaguchi3/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.sakaguchi3.jbatch002.s;

import static com.github.sakaguchi3.jbatch002.api.CipherY.generateIVStr;
import static com.github.sakaguchi3.jbatch002.api.CipherY.generateKeyStr;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.jbatch002.api.CipherY;
import com.github.sakaguchi3.jbatch002.api.CipherY.KeyLen;
import com.github.sakaguchi3.jbatch002.io.resource.RepositoryCryptImplMemoryDB;
import com.github.sakaguchi3.jbatch002.io.resource.RepositoryKeyImplMemoryDB;
import com.github.sakaguchi3.jbatch002.io.resource.dao.CryptDao;
import com.github.sakaguchi3.jbatch002.io.resource.dao.KeyDao;
import com.github.sakaguchi3.jbatch002.io.resource.dto.CryptDto;
import com.github.sakaguchi3.jbatch002.io.resource.dto.KeyDto;

public class CryptRepositoryTest {

	KeyDao keyDao = null;
	final long dataSize = 3;

	@BeforeEach
	void init() {
		var keyData = LongStream.range(0, dataSize) //
				.mapToObj(num -> KeyDto.of(num, "corpNo" + num, generateKeyStr(KeyLen.Len256), generateIVStr())) //
				.collect(collectingAndThen(toList(), Collections::unmodifiableList));
		var keyRepo = new RepositoryKeyImplMemoryDB(keyData);

		this.keyDao = new KeyDao(keyRepo);
	}

	@Test
	void cryptTest() {
		try {
			var keyId = 1L;
			var key = keyDao.selectWhereId(keyId).get();
			var cipher = new CipherY(key.secretKey.getBytes(), key.iv.getBytes());

			var cryptRepo = new RepositoryCryptImplMemoryDB(new ArrayList<>());
			var cryptDao = new CryptDao(cryptRepo);
			IntStream.range(0, 4) //
					.mapToObj(num -> "test_" + num) //
					.map(str -> str.getBytes()) //
					.flatMap(plainDtByte -> cipher.encrypto(plainDtByte).stream()) //
					.map(encDtByte -> CryptDto.of(key.id, encDtByte)) //
					.forEach(dto -> cryptDao.insert(dto));

			// CryptDto{id=1, keyId=1, crypted(base64)=twrswRwwstPcTiAm31+uOw==},
			// CryptDto{id=2, keyId=1, crypted(base64)=Ms/EgQ2+6N6+LryETrXDRA==},
			// CryptDto{id=3, keyId=1, crypted(base64)=tthjNN5UDvznj4eJaJ0Oaw==},
			// CryptDto{id=4, keyId=1, crypted(base64)=RcXXuVAi0+VaqWT2hwEREA==}
			var cryptLst = cryptDao.selectWhereKeyId(key.id);
			assertTrue(cryptLst.size() == 4);

			// [test_0, test_1, test_2, test_3]
			var decodePlainDataLst = cryptLst.stream()//
					.flatMap(c -> cipher.decrypto(c.crypted).stream()) //
					.map(v -> new String(v)) //
					.collect(collectingAndThen(toList(), Collections::unmodifiableList));

			assertTrue(decodePlainDataLst.contains("test_0"));
			assertTrue(decodePlainDataLst.contains("test_3"));

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void keyLstTest() {
		var keyOp = keyDao.selectWhereId(1);
		assertTrue(keyOp.isPresent());

		// KeyDto{id=0, corpName=corpNo0, secretKey=HMBBi4vhmdmw, iv=4s8IwulUG7ms4IDG},
		// KeyDto{id=1, corpName=corpNo1, secretKey=8ovQzdNcPQzY, iv=WQ8bfzULp2swhYXb},
		// KeyDto{id=2, corpName=corpNo2, secretKey=zogt1FBmbNma, iv=5E5uLkB6ommA04S2}
		var keyLst = keyDao.selectAll();
		assertEquals(dataSize, keyLst.size());
	}

	void debug() {
	}

}
