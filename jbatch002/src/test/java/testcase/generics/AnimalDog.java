package testcase;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimalDog extends Animal {

	private static final Logger LOG = LogManager.getLogger();

	public AnimalDog(String name) {
		super(name);
	}


	@Override
	public boolean equals(Object o) {
		if (!(o instanceof AnimalDog)) {
			return false;
		}
		var that = (AnimalDog) o;
		var isSame = Objects.equals(name, that.name);
		return isSame;
	}
}
