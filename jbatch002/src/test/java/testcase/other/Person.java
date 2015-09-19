package testcase.other;

import static java.util.Objects.hash;

import java.util.Objects;
import java.util.Optional;

import com.google.common.collect.ComparisonChain;

public class Person implements Comparable<Person> {

	// --------------------------------------------------------------------------------
	// field
	// --------------------------------------------------------------------------------

	public final String name;
	public final int age;

	private final static String CLASS = Person.class.getSimpleName();

	// --------------------------------------------------------------------------------
	// constructor
	// --------------------------------------------------------------------------------

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// --------------------------------------------------------------------------------
	// public
	// --------------------------------------------------------------------------------

	@Override
	public String toString() {
		String s = CLASS + "(" + name + ", " + age + ")";
		return s;
	}

	@Override
	public boolean equals(Object o) {
		var isSame = Optional.ofNullable(o) //
				.filter(v -> (v instanceof Person)) //
				.map(v -> (Person) v) //
				.filter(v -> (age == v.age)) //
				.filter(v -> Objects.equals(name, v.name)) //
				.isPresent();
		return isSame;
	}

	@Override
	public int hashCode() {
		return hash(age, name);
	}

	/**
	 * Comparable<Person>
	 */
	@Override
	public int compareTo(Person that) {
		var intComp = ComparisonChain.start() //
				.compare(this.age, that.age) //
				.compare(this.name, that.name) //
				.result();
		return intComp;
	}
}
