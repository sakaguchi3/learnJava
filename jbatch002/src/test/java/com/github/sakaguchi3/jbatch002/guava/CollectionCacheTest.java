package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;

public class CollectionCacheTest {

	ImmutableMap<String, String> data = ImmutableMap.<String, String>builder() //
			.put("k1", "v1") //
			.put("k2", "v2") //
			.put("k3", "v3") //
			.build();

	@Test
	public void loadCacheTest() throws ExecutionException {

		var cache = CacheBuilder.newBuilder() //
				.maximumSize(3) // cache size
				.expireAfterWrite(10, TimeUnit.MINUTES) // interval
				.build(new CacheLoader<String, String>() {
					public String load(String key) throws Exception {
						var v = data.get(key);
						return v;
					}
				});

		// nonull
		var v1 = cache.get("k1");
		var v2 = cache.get("k2");
		var v3 = cache.get("k3");

		// nullable
		var jv1 = cache.getIfPresent("kk1");
		assertThrows(InvalidCacheLoadException.class, () -> {
			// throw exception
			var jv2 = cache.get("empty_key");
		});

		debug();
	}

	@Test
	public void nullExceptionTest() throws Exception {
		var cache = CacheBuilder.newBuilder() //
				.maximumSize(3) // cache size
				.expireAfterWrite(10, TimeUnit.MINUTES) // interval
				.build(new CacheLoader<String, String>() {
					public String load(String key) throws Exception {
						var v = data.get(key);
						return v;
					}
				});

		assertThrows(InvalidCacheLoadException.class, () -> {
			// throw Exception
			cache.get("invalid key");
		});
		debug();
	}

	@Test
	public void cacheExpireTest() throws InterruptedException {
		var cache = CacheBuilder.newBuilder() //
				.expireAfterWrite(2, TimeUnit.SECONDS) // reflesh inverval
				.build();

		cache.put("k1", "v1");
		var v2 = cache.getIfPresent("k1");
		assertEquals(v2, ("v1"));

		TimeUnit.SECONDS.sleep(3);

		// expire
		var v3 = cache.getIfPresent("k1");
		assertNull(v3);

		debug();

	}

	@Test
	public void cacheMaxSizeTest() {

		var cache = CacheBuilder.newBuilder() //
				.maximumSize(3) // max cache size
				.<String, String>build();

		cache.put("k1", "v1");
		cache.put("k2", "v2");
		cache.put("k3", "v3");
		cache.put("k4", "v4");

		var vv1 = cache.getIfPresent("k1");
		var vv2 = cache.getIfPresent("k2");
		var vv3 = cache.getIfPresent("k3");
		var vv4 = cache.getIfPresent("k4");

		// varだと型定義が確定しないから（？）エラーが起きる
		var size = cache.size();
		debug();

		assertEquals(size, (3L));
		assertNull(vv1);
		assertEquals(vv2, ("v2"));
		assertEquals(vv3, ("v3"));
		assertEquals(vv4, ("v4"));

		debug();

	}

	void debug() {

	}

	@Test
	public void cacheInvalidate1Test() throws ExecutionException {
		HeavyTask h = new HeavyTask();

		LoadingCache<String, String> cache = CacheBuilder.newBuilder() //
				.maximumSize(10) // cache size
				.expireAfterWrite(10, TimeUnit.MINUTES) // interval
				.build(new CacheLoader<>() {
					public String load(String key) throws Exception {
						var v = h.getKey(key);
						return v;
					}
				});

		var keya = "keya1";

		var vala = cache.get(keya);
		var numa = h.getCnt(); // 1

		var vala2 = cache.get(keya);
		var numa2 = h.getCnt(); // 1

		cache.invalidate(keya);
		var vala3 = cache.get(keya);
		var numa3 = h.getCnt(); // 2

		assertEquals(1, numa);
		assertEquals(1, numa2);
		assertEquals(2, numa3);

		assertEquals("val1", vala);
		assertEquals("val1", vala2);
		assertEquals("val1", vala3);

		debug();
	}

	@Test
	public void cacheInvalidate2Test() throws ExecutionException, InterruptedException {
		HeavyTask h = new HeavyTask();

		LoadingCache<String, String> cache = CacheBuilder.newBuilder() //
				.maximumSize(10) // cache size
				.expireAfterWrite(10, TimeUnit.MINUTES) // interval
				.build(new CacheLoader<>() {
					public String load(String key) throws Exception {
						var v = h.getKey(key);
						return v;
					}
				});

		var keya = "keya1";

		var vala = cache.get(keya);
		var numa = h.getCnt(); // 1

		var vala2 = cache.get(keya);
		var numa2 = h.getCnt(); // 1

		cache.refresh(keya);
		var vala3 = cache.get(keya);
		var numa3 = h.getCnt(); // 1 or 2

		TimeUnit.MILLISECONDS.sleep(100);

		var numa4 = h.getCnt(); // 2

		assertEquals(1, numa);
		assertEquals(1, numa2);
		assertTrue((1 == numa3) || (2 == numa3));
		assertEquals(2, numa4);

		assertEquals("val1", vala);
		assertEquals("val1", vala2);
		assertEquals("val1", vala3);

		debug();
	}

	@Test
	public void cacheInvalidate3Test() {
		HeavyTask h = new HeavyTask();

		LoadingCache<String, String> cache = CacheBuilder.newBuilder() //
				.maximumSize(10) // cache size
				.expireAfterWrite(10, TimeUnit.MINUTES) // interval
				.build(new CacheLoader<>() {
					public String load(String key) throws Exception {
						var v = h.getKey(key);
						return v;
					}
				});

		var keya = "keya1";

		var vala = cache.getIfPresent(keya);
		var numa = h.getCnt(); // 1

		var vala2 = cache.getIfPresent(keya);
		var numa2 = h.getCnt(); // 1

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		var vala4 = cache.getIfPresent(keya);
		var numa4 = h.getCnt(); // 2

		assertEquals(0, numa);
		assertEquals(0, numa2);
		assertEquals(0, numa4);
		assertNull(vala);
		assertNull(vala2);
		assertNull(vala4);

		debug();
	}

	static class HeavyTask {
		final Map<String, String> m = new HashMap<>(30);
		private int num = 1;
		private int cnt = 0;

		public String getKey(String key) {
			var a = m.get(key);

			cnt++;
			if (Objects.isNull(a)) {
				a = "val" + num;
				m.put(key, a);
				num++;
			}
			return a;
		}

		public int getCnt() {
			return cnt;
		}
	}

}
