package com.github.sakaguchi3.fplambda.functor;

import com.jnape.palatable.lambda.functions.Fn1;
import com.jnape.palatable.lambda.functor.Functor;

public class FunctorConcrete<A> implements Functor<A, FunctorConcrete<?>> {

	private static final String C = ApplicativeConcrete.class.getSimpleName();

	private final A a;

	public FunctorConcrete(A x) {
		this.a = x;
	}

	// ---------------------------------------------------------------------
	// public
	// ---------------------------------------------------------------------

	// required -------------------------

	@Override
	public <B> FunctorConcrete<B> fmap(Fn1<? super A, ? extends B> fn) {
		FunctorConcrete<B> b = pure(fn.apply(a));
		return b;
	}

	// optional ---------------------------

	public A getA() {
		return a;
	}

	// ---------------------------------------------------------------------
	// private
	// ---------------------------------------------------------------------

	protected <B> FunctorConcrete<B> pure(B b) {
		var y = new FunctorConcrete<>(b);
		return y;
	}

	@Override
	public String toString() {
		String s = C + "[" + "a:" + a + "]";
		return s;
	}
}
