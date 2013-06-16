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
package com.github.sakaguchi3.jbatch002.io.csv.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.bean.CsvBindByPosition;

public class CsvItemEntity {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	protected static final Logger LOG = LogManager.getLogger();

	@CsvBindByPosition(position = 0, required = true)
	private String id;

	@CsvBindByPosition(position = 1, required = true)
	private String name;

	@CsvBindByPosition(position = 2, required = true)
	private int price;

	@CsvBindByPosition(position = 3, required = true)
	private int amount;

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

}
