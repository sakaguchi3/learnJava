package com.github.sakaguchi3.jbatch002.gs;

import org.junit.jupiter.api.Test;

import com.gs.collections.impl.factory.Lists;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.DisplayName;
//import static org.junit.jupiter.api.Assertions.*;
//import static java.time.Duration.*; 
//import static java.util.Optional.ofNullable;
//import static java.util.Collections.*;
//import static java.util.Comparator.*;
//import static java.util.Objects.*;
//import static java.util.Optional.*;
//import static java.util.stream.Collectors.*;
//import static java.time.Duration.*; 
//import static java.util.Collections.*;
//import static java.util.Comparator.*;
//import static java.util.Objects.*;
//import static java.util.Optional.*;
//import static java.util.stream.Collectors.*;
//import static java.util.stream.Stream.*; 
//import static io.vavr.collection.HashMap.*;
//import static io.vavr.collection.HashMultimap.*;
//import static io.vavr.collection.HashSet.*;
//import static io.vavr.control.Try.*; 
//import static io.vavr.API.*;
//import static io.vavr.API.Match.*;
//import static io.vavr.Predicates.*;
//import io.vavr.API.*;
//import io.vavr.control.*;
//import org.apache.logging.log4j.*; 
//import java.util.Collections.*;
//import java.util.*;

public class GSCollection {
	@Test
	public void bTest() {
		var il = Lists.immutable.of(1, 2, 3);
		il.select(v -> v % 2 == 0) //
				.collect(s -> s + 9)
		//
		;
		debug();
	}

	void debug() {
	}

}
