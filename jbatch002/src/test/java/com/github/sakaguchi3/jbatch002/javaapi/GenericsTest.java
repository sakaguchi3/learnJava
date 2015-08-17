package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import testcase.generics.Animal;
import testcase.generics.AnimalDog;
import testcase.generics.AnimalHuman;
import testcase.generics.AnimalWomen;

public class GenericsTest {
	@Test
	void bTest() {
		debug();
	}

	@Test
	void usecaseTest() {
		List<AnimalDog> dogLst = List.of(//
				new AnimalDog("dog01"), //
				new AnimalDog("dog02"), //
				new AnimalDog("dog03"));
		List<Animal> animalLst = new ArrayList<>();

		// OK
		copy(dogLst, animalLst);

		List<AnimalHuman> humanLst = new ArrayList<>();
		List<AnimalWomen> womenLst = List.of(new AnimalWomen("human01"));
		// NG
		// copy(dogLst, humanLst); // humanLstが<? super Animal>を満たさない
		// copy(womenLst, humanLst); // humanLstが<? super Animal>を満たさない
		// OK
		womenLst.stream().forEach(humanLst::add);

		List<Object> objLst = new ArrayList<>();
		// OK
		copy(dogLst, objLst);
	}

	/**
	 * arrayは共変(convariant)になってしまっている<br>
	 */
	@Test
	void convariantArrayTest() {
		AnimalDog[] dogArry = new AnimalDog[1];
		Animal[] animalArry = dogArry;

		// ok
		animalArry[0] = new AnimalDog("dog01");

		// compileが通ってしまうが、 throw exception
		assertThrows(ArrayStoreException.class, () -> {
			animalArry[0] = new AnimalHuman("human01");
		});

		Object[] objArray = dogArry;
		assertThrows(ArrayStoreException.class, () -> {
			objArray[0] = 1;
		});

	}

	/**
	 * 反変(contravariant)<br>
	 * List<? supserA> <br>
	 * 
	 * 変数への代入: List<? super A>だったらok<br>
	 * 
	 * get: ok, Object <br>
	 * add: ok, <? extends A>だったらok <br>
	 */
	@Test
	void contravariantTest() {
		List<AnimalDog> dogLst = new ArrayList<>();

		List<Animal> animalLst = new ArrayList<>();
		List<Object> objLst = List.of();

		// NG
		// List<? super Animal> animalLst = dogLst;

		// OK
		List<? super Animal> animal01Lst = animalLst;
		List<? super Animal> animal02Lst = objLst;

		// NG : <? extends A>じゃないのでNG
		// dogLst.add(new Object());

		// OK: <? extends A>だったらOK
		animal01Lst.add(new AnimalDog("dog01"));
		animal01Lst.add(new AnimalHuman("taro"));

		// OK:
		Object obj01 = animal01Lst.get(0);
		// NG:
		// Animal obj01 = animal01Lst.get(0);

		debug();
	}

	/**
	 * 共変(convariant)<br>
	 * List<? extens A> <br>
	 * 
	 * 変数への代入: List<? extends A>だったらok <br>
	 * 
	 * get: ok, 取り出す型はAになる <br>
	 * add: ng<br>
	 */
	@Test
	void covariantTest() {
		List<AnimalDog> dogLst = new ArrayList<>();
		dogLst.add(new AnimalDog("dog1"));
		dogLst.add(new AnimalDog("dog2"));

		// NG: compile err
		// List<Animal> animal01Lst = dogLst;

		// OK: (? extends Animal)のListを代入できる。
		List<? extends Animal> animal01Lst = dogLst;

		// NG:
		// animal01Lst.add(new Animal());

		// OK
		Animal animal = animal01Lst.get(0);
		assertEquals(new Animal("dog1"), animal);
	}

	/**
	 * @param src: get
	 * @param tar: add
	 */
	void copy(List<? extends Animal> src, List<? super Animal> tar) {
		src.stream().forEach(tar::add);
	}

	void debug() {
	}

}
