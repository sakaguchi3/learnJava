package testcase;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public class ValidationFuga {

	private static final Logger LOG = LogManager.getLogger();

	public Validation<Seq<String>, Tuple3<String, Integer, Double>> validate(String s, int i, double d) {
		var a = Validation.combine(valid(s), valid(i), valid(d)) //
				.ap(Tuple::of);
		return a;
	}
	
	public void ap() {
//		Vlidation<Seq<Seq<String>>, ? extends Function<? super Person, ? extends Integer>> 
	}

	protected Validation<String, String> valid(String d) {
		if (StringUtils.isEmpty(d))
			return Validation.invalid("NgName.");
		return Validation.valid(d);
	}

	protected Validation<String, Integer> valid(int d) {
		if (d < 0)
			return Validation.invalid("NgInt.");
		return Validation.valid(d);
	}

	protected Validation<String, Double> valid(double d) {
		if (d < 0)
			return Validation.invalid("NgDouble.");
		return Validation.valid(d);
	}

	void debug() {
	}
}
