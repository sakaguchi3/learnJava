package testcase.generics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FigureSquare extends FigureAbstract<FigureSquare> {

	private static final Logger LOG = LogManager.getLogger();

	public FigureSquare setEdge(int w) {
		return this;
	}

}
