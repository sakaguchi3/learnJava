package com.github.sakaguchi3.fplambda.functor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jnape.palatable.lambda.functions.Fn1;
import com.jnape.palatable.lambda.functions.Fn2;

public class ApplicativeConcreteTest {

	/**
	 * ap:: fa -> f(a->b) -> fb
	 */
	@Test
	void apTest() {
		// fa
		var ap2 = new ApplicativeConcrete<>(2);
		// f(a->b)
		var ap4 = new ApplicativeConcrete<Fn1<? super Integer, ? extends Integer>>((Integer x) -> x + 2);
		// fa -> f(a->b) -> fb
		var sut2 = ap2.zip(ap4);

		assertEquals(4, sut2.getA());
	}

	/**
	 * ap:: fa -> f(a->b) -> fb
	 */
	@Test
	void ap2Test() {
		// fa
		var ap1 = new ApplicativeConcrete<>(1);

		// fmapすると、関数のapplicative(f(a->b))が返ってくる。Fn2自体もapplicative
		Fn2<Integer, Integer, Integer> fn2 = (x, y) -> (x + y);
		var ap2 = new ApplicativeConcrete<>(2);
		ApplicativeConcrete<Fn1<? super Integer, ? extends Integer>> apFn1 = ap2.fmap(fn2);

		// fa -> f(a->b) -> fb
		ApplicativeConcrete<Integer> sut = ap1.zip(apFn1);

		assertEquals(3, sut.getA());
	}

	void debug() {
	}

}
