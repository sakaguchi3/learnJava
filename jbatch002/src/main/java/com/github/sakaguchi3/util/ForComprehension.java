/**
 * Copyright 2020 sakaguchi<uqw@outlook.jp>, https://github.com/sakaguchi3/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.sakaguchi3.util;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vavr.Function3;
import io.vavr.Function4;

public class ForComprehension {

	static final Logger log = LogManager.getLogger();

	// ---------------------------------------------------------------------------------------------
	// - private method
	// ---------------------------------------------------------------------------------------------

	private static boolean anyMatch(Predicate<Optional<?>> p, Optional<?>... ops) {
		for (var op : ops) {
			if (p.test(op)) {
				return true;
			}
		}
		return false;
	}

	private static boolean anyMatch(Predicate<List<?>> p, List<?>... ops) {
		for (var op : ops) {
			if (p.test(op)) {
				return true;
			}
		}
		return false;
	}

	private static void requireNonNulls(Object... arrays) {
		var i = 1;
		for (var o : arrays) {
			requireNonNull(o, "ts" + i + " is null");
			i++;
		}
	}

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	// for optional -----------

	public static <T1> For1Optional<T1> for_(Optional<T1> ts1) {
		requireNonNull(ts1, "ts1 is null");
		return new For1Optional<>(ts1);
	}

	public static <T1, T2> For2Optional<T1, T2> for_( //
			Optional<T1> ts1, //
			Optional<T2> ts2 //
	) {
		requireNonNulls(ts1, ts2);
		return new For2Optional<>(ts1, ts2);
	}

	public static <T1, T2, T3> For3Optional<T1, T2, T3> for_(//
			Optional<T1> ts1, //
			Optional<T2> ts2, //
			Optional<T3> ts3 //
	) {
		requireNonNulls(ts1, ts2, ts3);
		return new For3Optional<>(ts1, ts2, ts3);
	}

	public static <T1, T2, T3, T4> For4Optional<T1, T2, T3, T4> for_(//
			Optional<T1> ts1, //
			Optional<T2> ts2, //
			Optional<T3> ts3, //
			Optional<T4> ts4 //
	) {
		requireNonNulls(ts1, ts2, ts3, ts4);
		return new For4Optional<>(ts1, ts2, ts3, ts4);
	}

	// for list -----------

	public static <T1> For1List<T1> for_(List<T1> ts1) {
		requireNonNull(ts1, "ts1 is null");
		return new For1List<>(ts1);
	}

	public static <T1, T2> For2List<T1, T2> for_( //
			List<T1> ts1, //
			List<T2> ts2 //
	) {
		requireNonNulls(ts1, ts2);
		return new For2List<>(ts1, ts2);
	}

	public static <T1, T2, T3> For3List<T1, T2, T3> for_( //
			List<T1> ts1, //
			List<T2> ts2, //
			List<T3> ts3 //
	) {
		requireNonNulls(ts1, ts2, ts3);
		return new For3List<>(ts1, ts2, ts3);
	}

	// ---------------------------------------------------------------------------------------------
	// - class
	// ---------------------------------------------------------------------------------------------

	// optional-for-comprehension

	public static class For1Optional<T1> {
		private final Optional<T1> ts1;

		private For1Optional(Optional<T1> ts1) {
			this.ts1 = ts1;
		}

		public <R> Optional<R> yield(Function<? super T1, ? extends R> f) {
			requireNonNull(f, "f is null");
			return ts1.map(f);
		}
	}

	public static class For2Optional<T1, T2> {
		private final Optional<T1> ts1;
		private final Optional<T2> ts2;

		private For2Optional(Optional<T1> ts1, Optional<T2> ts2) {
			this.ts1 = ts1;
			this.ts2 = ts2;
		}

		public <R> Optional<R> yield(BiFunction<? super T1, ? super T2, ? extends R> f) {
			requireNonNull(f, "f is null");
			return ts1.flatMap(t1 -> ts2.map(t2 -> f.apply(t1, t2)));
		}
	}

	public static class For3Optional<T1, T2, T3> {
		private final Optional<T1> ts1;
		private final Optional<T2> ts2;
		private final Optional<T3> ts3;

		private For3Optional(Optional<T1> ts1, Optional<T2> ts2, Optional<T3> ts3) {
			this.ts1 = ts1;
			this.ts2 = ts2;
			this.ts3 = ts3;
		}

		public <R> Optional<R> yield(Function3<? super T1, ? super T2, ? super T3, ? extends R> f) {
			requireNonNull(f, "f is null");
			if (anyMatch(Optional::isEmpty, ts1, ts2, ts3)) {
				return Optional.empty();
			}
			var r = f.apply(ts1.get(), ts2.get(), ts3.get());
			return Optional.of(r);
		}
	}

	public static class For4Optional<T1, T2, T3, T4> {
		private final Optional<T1> ts1;
		private final Optional<T2> ts2;
		private final Optional<T3> ts3;
		private final Optional<T4> ts4;

		private For4Optional(Optional<T1> ts1, Optional<T2> ts2, Optional<T3> ts3, Optional<T4> ts4) {
			this.ts1 = ts1;
			this.ts2 = ts2;
			this.ts3 = ts3;
			this.ts4 = ts4;
		}

		public <R> Optional<R> yield(Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
			requireNonNull(f, "f is null");
			if (anyMatch(Optional::isEmpty, ts1, ts2, ts3, ts4)) {
				return Optional.empty();
			}
			var r = f.apply(ts1.get(), ts2.get(), ts3.get(), ts4.get());
			return Optional.of(r);
		}
	}

	// list-for-comprehension

	public static class For1List<T1> {
		private final List<T1> ts1;

		private For1List(List<T1> ts1) {
			this.ts1 = ts1;
		}

		public <R> List<R> yield(Function<? super T1, ? extends R> f) {
			requireNonNull(f, "f is null");
			return ts1.stream().map(f).collect(toList());
		}
	}

	public static class For2List<T1, T2> {
		private final List<T1> ts1;
		private final List<T2> ts2;

		private For2List(List<T1> ts1, List<T2> ts2) {
			this.ts1 = ts1;
			this.ts2 = ts2;
		}

		public <R> List<R> yield(BiFunction<? super T1, ? super T2, ? extends R> f) {
			requireNonNull(f, "f is null");
			if (anyMatch(List::isEmpty, ts1, ts2)) {
				return Collections.emptyList();
			}
			var r = new ArrayList<R>(ts1.size() * ts2.size());
			for (var t1 : ts1)
				for (var t2 : ts2)
					r.add(f.apply(t1, t2));
			return r;
		}
	}

	public static class For3List<T1, T2, T3> {
		private final List<T1> ts1;
		private final List<T2> ts2;
		private final List<T3> ts3;

		private For3List(List<T1> ts1, List<T2> ts2, List<T3> ts3) {
			this.ts1 = ts1;
			this.ts2 = ts2;
			this.ts3 = ts3;
		}

		public <R> List<R> yield(Function3<? super T1, ? super T2, ? super T3, ? extends R> f) {
			requireNonNull(f, "f is null");
			if (anyMatch(List::isEmpty, ts1, ts2, ts3)) {
				return Collections.emptyList();
			}
			var r = new ArrayList<R>(ts1.size() * ts2.size() * ts3.size());
			for (var t1 : ts1)
				for (var t2 : ts2)
					for (var t3 : ts3)
						r.add(f.apply(t1, t2, t3));
			return r;
		}
	}

	public static class For4List<T1, T2, T3, T4> {
		private final List<T1> ts1;
		private final List<T2> ts2;
		private final List<T3> ts3;
		private final List<T4> ts4;

		private For4List(List<T1> ts1, List<T2> ts2, List<T3> ts3, List<T4> ts4) {
			this.ts1 = ts1;
			this.ts2 = ts2;
			this.ts3 = ts3;
			this.ts4 = ts4;
		}

		public <R> List<R> yield(Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
			requireNonNull(f, "f is null");
			if (anyMatch(List::isEmpty, ts1, ts2, ts3, ts4)) {
				return Collections.emptyList();
			}
			var r = new ArrayList<R>(ts1.size() * ts2.size() * ts3.size() * ts4.size());
			for (var t1 : ts1)
				for (var t2 : ts2)
					for (var t3 : ts3)
						for (var t4 : ts4)
							r.add(f.apply(t1, t2, t3, t4));
			return r;
		}
	}
}
