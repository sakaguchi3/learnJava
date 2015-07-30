package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Lazy;
import io.vavr.control.Option;
import io.vavr.control.Try;

public class FunctionTest {

	@Test
	void curry2Test() {
		// int -> float -> double
		Function1<Integer, Function1<Float, Double>> f1 = _int -> _float -> 0d + _int + _float;
		// (int, float) -> double
		Function2<Integer, Float, Double> f2 = (_int, _float) -> 0d + _int + _float;

		Function1<Float, Double> f22 = f2.apply(1);
	}

	@Test
	void curryVavrTest() {
		Function3<Integer, Integer, Integer, Integer> func3Add = (a, b, c) -> (a + b + c);

		// partical apply: (int, int) -> int
		Function2<Integer, Integer, Integer> func2Add = func3Add.apply(1);
		assertEquals(3, func2Add.apply(1, 1));

		// curried: (int) -> (int -> int) <=> (int) -> (int) -> (int)
		Function1<Integer, Function1<Integer, Integer>> funcCurryAdd = func3Add.curried().apply(1);
		assertEquals(3, funcCurryAdd.apply(1).apply(1));
	}

	@Test
	public void liftTest() {

		Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

		Function2<Integer, Integer, Option<Integer>> safeDivide10 = Function2.lift(divide);
		Function2<Integer, Integer, Try<Integer>> safeDivide21 = Function2.liftTry((a, b) -> a / b);

		// success action
		assertEquals(safeDivide21.apply(1, 3), (Try.success(1 / 3)));

		// fail acction

		assertEquals(safeDivide10.apply(30, 0), (Option.none()));

		var sutFail = safeDivide21.apply(1, 0);
		assertTrue(sutFail.isFailure());
		assertTrue((sutFail.getCause() instanceof ArithmeticException));

		assertThrows(ArithmeticException.class, () -> {
			divide.apply(1, 0);
		});
	}

	@Test
	public void funcTest() {
		Function2<Integer, Integer, Integer> add2 = (a, b) -> a + b;
		Function1<Integer, Integer> add1 = add2.curried().apply(3);

		assertEquals(add1.apply(33), (add2.apply(3, 33)));
	}

	@Test
	public void curryJavaTest() {
		// (int, int) -> int
		BiFunction<Integer, Integer, Integer> add10 = (a, b) -> a + b;
		assertEquals(add10.apply(5, 6), (11));

		// (int) -> (int -> int)
		Function<Integer, Function<Integer, Integer>> add20 = a -> b -> a + b;
		Function<Integer, Integer> add21 = add20.apply(3);

		assertEquals(add20.apply(3).apply(8), (add21.apply(8)));

	}

	@Test
	public void lazyTest() {
		Supplier<Double> sup = () -> 0.123;

		Lazy<Double> lazy = Lazy.of(sup);

		Double ans = 0.123;

		assertFalse(lazy.isEvaluated());
		double d1 = lazy.get(); // 0.123 (evaluted)
		assertEquals(d1, (ans));

		assertTrue(lazy.isEvaluated());
		double d2 = lazy.get(); // 0.123 (memoized)
		assertEquals(d2, (ans));

		double d3 = lazy.get(); // 0.123 (memoized)
		assertEquals(d3, (ans));
	}

	@Test
	public void lazy2Test() {
		Supplier<Double> sup = () -> 0.123;

		Lazy<Double> lazy = Lazy.of(sup);

		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertFalse(lazy.isEvaluated());
		var sut = lazy.get();
		assertEquals(0.123d, sut);
	}

	void debug() {
	}

}
