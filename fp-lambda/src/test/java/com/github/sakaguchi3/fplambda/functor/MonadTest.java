package com.github.sakaguchi3.fplambda.functor;

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
//import org.apache.logging.log4j.*; 
//import java.util.Collections.*;
//import java.util.*;

public class MonadTest {
	@Test
	public void bTest() {
		debug();
	}

	@Test
	public void throwTest() {
		assertThrows(Exception.class, () -> {
			throw new RuntimeException();
		});
		debug();
	}

	@Test
	public void asserEqTest() {
		assertEquals(true, true);
		if (false) {
			fail();
		}
		debug();
	}

	void debug() {
	}

}
