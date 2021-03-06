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

import java.util.ArrayList;
import java.util.List;

public class ListMutableImpl<T> implements ListMutable<T> {

	final List<T> lst;

	public ListMutableImpl(int size) {
		lst = new ArrayList<>(size);
	}

	public ListMutableImpl() {
		lst = new ArrayList<>();
	}

	@Override
	public boolean add(T e) {
		return lst.add(e);
	}

	@Override
	public T get(int index) {
		return lst.get(index);
	}

	@Override
	public int size() {
		return lst.size();
	}

}
