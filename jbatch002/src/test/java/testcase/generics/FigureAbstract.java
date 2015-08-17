package testcase.generics;

public abstract class FigureAbstract<T extends FigureAbstract<T>> {

	@SuppressWarnings("unchecked")
	T setPos(int x, int y) {
		return (T) this;
	}

}
