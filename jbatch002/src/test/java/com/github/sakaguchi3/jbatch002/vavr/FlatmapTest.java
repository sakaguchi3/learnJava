package com.github.sakaguchi3.jbatch002.vavr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static java.time.Duration.*;
import static java.util.Optional.ofNullable;
import static java.util.Collections.*;
import static java.util.Comparator.*;
import static java.util.Objects.*;
import static java.util.Optional.*;
import static java.util.stream.Collectors.*;
import static java.time.Duration.*;
import static java.util.Collections.*;
import static java.util.Comparator.*;
import static java.util.Objects.*;
import static java.util.Optional.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.*;
import static io.vavr.collection.HashMap.*;
import static io.vavr.collection.HashMultimap.*;
import static io.vavr.collection.HashSet.*;
import static io.vavr.control.Try.*;
import static io.vavr.API.*;
import static io.vavr.API.Match.*;
import static io.vavr.Predicates.*;
import io.vavr.API.*;
import io.vavr.control.*;
import org.apache.logging.log4j.*;
import java.util.Collections.*;
import java.util.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.vavr.Function1;
import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Traversable;
import io.vavr.collection.Vector;

public class FlatmapTest {
	@Test
	public void bTest() {
		debug();
	}

	@Test
	void vavrLstflatMapTest() {
		Array<Traversable<Integer>> nestLst = Array.of(List.of(3, 4, 5), Vector.of(10, 11, 12));

		Array<Integer> flattenLst1 = nestLst.flatMap(Function.identity());
		Seq<Integer> flattenLst2 = nestLst.flatMap(Function1.identity());

		var ans1 = Array.of(3, 4, 5, 10, 11, 12);
		assertIterableEquals(ans1, flattenLst1);
		assertIterableEquals(ans1, flattenLst2); 
		debug();
	}

	void debug() {
	}

}
