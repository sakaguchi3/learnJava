package com.github.sakaguchi3.fplambda.functor;

import com.jnape.palatable.lambda.functions.Fn1;
import com.jnape.palatable.lambda.functor.Applicative;

public class ApplicativeConcrete<A> implements Applicative<A, ApplicativeConcrete<?>> {

	private static final String C = ApplicativeConcrete.class.getSimpleName();
	private final A a;

	public ApplicativeConcrete(A x) {
		this.a = x;
	}

	// ---------------------------------------------------------------------
	// public
	// ---------------------------------------------------------------------

	// required -------------------------

	@Override
	public <B> ApplicativeConcrete<B> pure(B b) {
		var y = new ApplicativeConcrete<>(b);
		return y;
	}

	/**
	 * ap:: fa -> f(a->b) -> fb
	 */
	@Override
	public <B> ApplicativeConcrete<B> zip(Applicative<Fn1<? super A, ? extends B>, ApplicativeConcrete<?>> appFn) {
		// app(a->b) to concreateApp(a->b)
		ApplicativeConcrete<Fn1<? super A, ? extends B>> appFn2 = appFn.coerce();
		// app(a->b) to (a->b)
		Fn1<? super A, ? extends B> fn = appFn2.getA();
		// a to b
		B b = fn.apply(getA());
		// b to app(b)
		ApplicativeConcrete<B> appB = pure(b);

		return appB;
	}

	// optional ---------------------------

	/**
	 * fmap:: fa -> (a->b) -> fb
	 */
	@Override
	public <B> ApplicativeConcrete<B> fmap(Fn1<? super A, ? extends B> fn) {
		// a to b
		var b = fn.apply(a);
		// b to app(b)
		var appB = pure(b);
		return appB;
	}

	public A getA() {
		return a;
	}

	@Override
	public String toString() {
		String s = C + "[" + "a:" + a + "]";
		return s;
	}
}
