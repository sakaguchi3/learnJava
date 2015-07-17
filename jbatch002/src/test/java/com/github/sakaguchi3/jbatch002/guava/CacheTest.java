package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class CacheTest {

	private static Function<Integer, Function<String, String>> heavyTask = time -> s -> {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s.toUpperCase();
	};

	@Test
	public void u02Test() {

		final int cacheNum = 3;
		final int cacheTime = 100; // millisec

		LoadingCache<String, String> cache = CacheBuilder.newBuilder() //
				.maximumSize(cacheNum) // キャッシュするデータ数
				.expireAfterAccess(cacheTime, TimeUnit.MILLISECONDS) // キャッシュする期間
				.build(CacheLoader.from((heavyTask.apply(cacheTime))));

		Consumer<Stopwatch> swReset = _sw -> {
			_sw.reset();
			_sw.start();
		};
		Function<String, Consumer<Stopwatch>> swMeasure1 = _str -> _sw -> {
			System.out.println(_str + ": " + _sw.stop());
			swReset.accept(_sw);
		};
		BiConsumer<String, Stopwatch> swMeasure = (_str, _sw) -> {
			System.out.println(_str + ": " + _sw);
			swReset.accept(_sw);
		};

		try {

			Stopwatch sw = Stopwatch.createStarted();

			// キャッシュに存在しないから遅い
			swReset.accept(sw);
			cache.get("u");
			swMeasure.accept("slow", sw);

			// 高速
			swReset.accept(sw);
			cache.get("u");
			swMeasure.accept("fast", sw);

			// over cache size
			cache.get("a");
			cache.get("b");
			cache.get("c");

			swReset.accept(sw);
			cache.get("u");
			// キャッシュオーバサイズのため、キャッシュに存在しないから遅い
			swMeasure.accept("slow", sw);

			Thread.sleep(cacheTime + 50);

			// キャッシュの有効期限が切れ、キャッシュに存在しないので遅い
			swReset.accept(sw);
			cache.get("u");
			swMeasure.accept("slow", sw);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		debug();
	}

	private void debug() {

	}

}
