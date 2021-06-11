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

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class Parser {

	private Reader buffer;
	private int peek;

	public static List<Token> parse(Reader buffer) throws Exception {
		return new Parser(buffer).parseFile();
	}

	public Parser(Reader buffer) {
		this.buffer = buffer;
	}

	public boolean notEOF() {
		return this.peek != -1;
	}

	public void next() throws IOException {
		this.peek = buffer.read();
	}

	public Token parseNumber() throws IOException {
		Token token = new Token(Type.NUMBER);
		while (notEOF()) {
			char c = (char) this.peek;
			if (!Util.isDigit(c)) {
				if (c == '.') {
					token.append(c);
					this.next();
					while (notEOF()) {
						c = (char) this.peek;
						if (!Util.isDigit(c)) {
							break;
						}
						token.append(c);
						this.next();
					}
				}
				break;
			}
			token.append(c);
			this.next();
		}
		return token;
	}

	public Token parseComment() throws IOException {
		Token token = new Token(Type.COMMENT, "//");
		this.next();
		while (notEOF()) {
			char c = (char) this.peek;
			if (c == '\r' || c == '\n') {
				break;
			}
			token.append(c);
			this.next();
		}
		return token;
	}

	public Token parseQuotes(char e) throws IOException {
		Token token = new Token(Type.STRING);
		char cf = (char) this.peek;
		token.append(cf);
		this.next();
		while (notEOF()) {
			char c = (char) this.peek;
			token.append(c);
			this.next();
			if (c == e) {
				break;
			}
		}
		return token;
	}

	public Token parseIdent() throws IOException {
		StringBuilder builder = new StringBuilder();
		while (notEOF()) {
			char c = (char) this.peek;
			String i = Util.translate(c);
			if (i == null) {
				break;
			}
			builder.append(c);
			this.next();
		}
		return new Token(Type.IDENT, builder.toString());
	}

	public List<Token> parseFile() throws Exception {
		List<Token> tokens = new LinkedList<>();
		this.next();
		while (notEOF()) {
			char c = (char) this.peek;
			if (c == '\t' || c == '\r' || c == '\n' || c == ' ') {
				Token token = new Token(Type.WHITESPACE, String.valueOf(c));
				tokens.add(token);
				this.next();
			} else if (Util.isDigit(c)) {
				tokens.add(parseNumber());
			} else if (c == '#') {
				tokens.add(parseComment());
			} else if (c == '`') {
				tokens.add(parseQuotes('`'));
			} else if (c == '\'') {
				tokens.add(parseQuotes('\''));
			} else if (c == '"') {
				tokens.add(parseQuotes('"'));
			} else if (c == '„') {
				String value = parseQuotes('“').getValue();
				tokens.add(new Token(Type.STRING, '"' + value.substring(1, value.length() - 1) + '"'));
			} else if (Util.translate(c) != null) {
				tokens.add(parseIdent());
			} else if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') {
				Token token = new Token(Type.BRACKET, String.valueOf(c));
				tokens.add(token);
				this.next();
			} else {
				Token token = new Token(Type.UNKNOWN, String.valueOf(c));
				tokens.add(token);
				this.next();
			}
		}
		return tokens;
	}

}
