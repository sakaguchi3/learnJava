package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class FunctionTest {

	@Test
	public void identity2Test() {
		var l = Optional.of(Optional.of(3));
		var l2 = l.flatMap(Function.identity());
		System.out.println(l2);
		debug();
	}

	/**
	 * カリー化
	 */
	@Test
	public void curryTest() {

		// 2引数関数
		BiConsumer<String, Integer> notCurry = (str, cnt) -> {
			String s2 = IntStream.range(0, cnt) //
					.mapToObj(__ -> str) //
					.collect(Collectors.joining());
			System.out.println("new_string:" + s2);
		};

		// カリー化させる関数
		Function<String, Consumer<Integer>> curry = str -> cnt -> {
			String s2 = IntStream.range(0, cnt) //
					.mapToObj(__ -> str) //
					.collect(Collectors.joining());
			System.out.println("new_string:" + s2);
		};

		// notCurry
		notCurry.accept("[x]", 3);
		// カリー化
		curry.apply("[x]").accept(3);

		debug();
	}

	/**
	 * 関数合成
	 */
	@Test
	public void funcCompositionTest() {
		Function<String, Integer> f10 = str -> str.length();
		Function<Integer, Integer> f20 = i -> i + 20;

		// function composition ---

		// f10 -> f20 で適用する
		Function<String, Integer> cf01 = f10.andThen(f20);
		// f10 -> f20 で適用する
		Function<String, Integer> cf02 = f20.compose(f10);

		final String s01 = "jjAbbkLL lllkkk";

		assertEquals(cf01.apply(s01), (cf02.apply(s01)));
	}

	private void debug() {

	}

}
