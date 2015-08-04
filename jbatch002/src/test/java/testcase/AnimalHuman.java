package testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimalHuman extends Animal {

	private static final Logger LOG = LogManager.getLogger();

	public AnimalHuman(String name) {
		super(name);
	}

}
