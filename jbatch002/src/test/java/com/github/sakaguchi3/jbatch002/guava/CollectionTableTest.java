package com.github.sakaguchi3.jbatch002.guava;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.google.common.collect.HashBasedTable;

public class CollectionTableTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void tbl3Test() {
		// (key1, key2, val1)
		HashBasedTable<String, String, Integer> table = HashBasedTable.create();

		table.put("k1", "k1", 3);
		table.put("k1", "k2", 4);
		table.put("k2", "k1", 4);
		table.put("k3", "k1", 4);
		table.put("k3", "k2", 4);

		// [3, 4, 4, 4, 4]
		var d3 = table.values().stream().collect(collectingAndThen(toList(), Collections::unmodifiableList));


		debug();
	}

	@Test
	public void tbl2Test() {
		// (key1, key2, val1)
		HashBasedTable<String, String, Integer> table = HashBasedTable.create();

		// k1.size3
		table.put("k1_1", "k2_1", 3);
		table.put("k1_1", "k2_1", 4);
		table.put("k1_1", "k2_2", 4);

		var sut01 = table.get("k1_1", "k2_1");
		var sut02 = table.cellSet();
		var sut03 = table.row("k1_1");

		debug();
	}

	/**
	 * @see <a href=
	 *      "https://github.com/google/guava/wiki/NewCollectionTypesExplained#table">guava
	 *      official</a>
	 * @see <a href="https://www.baeldung.com/guava-table">guide to guava</a>
	 * @see <a href="https://qiita.com/disc99/items/c4b98045fc4682cbb620">qiita</a>
	 */
	@Test
	public void tableTest() {
		// (key1, key2, val1)
		HashBasedTable<String, String, String> table = HashBasedTable.create();
		table.put("k1_aaa", "k2_aaa", "data1");

		var v1 = table.get("k1_aaa", "k2_aaa"); // data1
		var v2 = table.get("nodata", "k2_aaa"); // null

		var b1 = table.contains("k1_aaa", "k2_aaa"); // true
		var b2 = table.contains("nodata", "k2_aaa"); // false

		assertEquals(("data1"), v1);
		assertNull(v2);

		assertEquals(true, b1);
		assertEquals(false, b2);

		debug();
	}

	void debug() {
	}

}
