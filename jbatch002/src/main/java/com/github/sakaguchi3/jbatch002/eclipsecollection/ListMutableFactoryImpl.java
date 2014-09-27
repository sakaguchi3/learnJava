/**
 * Copyright 2021. sakaguchi3, https://github.com/sakaguchi3/learnJava
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
package com.github.sakaguchi3.jbatch002.eclipsecollection;

public class ListMutableFactoryImpl implements ListMutableFactory {

	@Override
	public <T> ListMutable<T> of(T one) {
		var lst = new ListMutableImpl<T>(1);
		lst.add(one);
		return lst;
	}

	@Override
	public <T> ListMutable<T> of(@SuppressWarnings("unchecked") T... some) {
		var lst = new ListMutableImpl<T>(some.length);
		for (var t : some) {
			lst.add(t);
		}
		return lst;
	}

	@Override
	public <T> ListMutable<T> of() {
		return new ListMutableImpl<>();
	}

}
