package testcase.generics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FigureCircle extends FigureAbstract<FigureCircle> {

	private static final Logger LOG = LogManager.getLogger();

	public FigureCircle setRadius(int r) {
		return this;
	}

}
