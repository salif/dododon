/*
 * Copyright (C) 2021  Salif Mehmed
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package eu.salif.odc;

import java.io.CharArrayWriter;

public class Token {
	private CharArrayWriter value;
	private Type type;

	public Token(Type t) {
		this.value = new CharArrayWriter();
		this.type = t;
	}

	public Token(Type t, String s) {
		this(t);
		this.value.append(s);
	}

	void append(char c) {
		this.value.append(c);
	}

	public String getValue() {
		return this.value.toString();
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type t) {
		this.type = t;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
