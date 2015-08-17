package testcase.generics;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Animal {

	private static final Logger LOG = LogManager.getLogger();

	String name = null;

	public Animal(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Animal)) {
			return false;
		}
		var that = (Animal) o;

		var isSame = Objects.equals(name, that.name);
		return isSame;
	}
}
