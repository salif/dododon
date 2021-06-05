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

	private boolean notEOF() {
		return this.peek != -1;
	}

	private void next() throws IOException {
		this.peek = buffer.read();
	}

	private Token parseNumber() throws IOException {
		Token token = new Token(Type.INT);
		while (notEOF()) {
			char c = (char) this.peek;
			if (!Util.isDigit(c)) {
				if (c == '.') {
					token.setType(Type.FLOAT);
					token.append(c);
					this.next();
					while (notEOF()) {
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

	private Token parseComment() throws IOException {
		Token token = new Token(Type.COMMENT);
		token.append('/');
		token.append('/');
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

	private Token parseQuotes(char e) throws IOException {
		Token token = new Token(Type.STRING);
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

	private String parseIdent() throws IOException {
		StringBuilder builder = new StringBuilder();
		StringBuilder orig = new StringBuilder();
		while (notEOF()) {
			char c = (char) this.peek;
			String i = Util.translate(c);
			if (i == null) {
				break;
			}
			builder.append(i);
			orig.append(c);
			this.next();
		}
		String origIdent = orig.toString();
		String newIdent = builder.toString();
		return switch (origIdent) {
			case "модул" -> "module";
			case "функция" -> "fn";
			case "върни" -> "return";
			case "за" -> "for";
			case "във" -> "in";
			case "низ" -> "string";
			default -> newIdent;
		};
	}

	private List<Token> parseFile() throws Exception {
		List<Token> tokens = new LinkedList<>();
		this.next();
		while (notEOF()) {
			char c = (char) this.peek;
			if (c == '\t' || c == '\r' || c == ' ') {
				Token token = new Token(Type.WS);
				token.append(c);
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
				Token token = new Token(Type.STRING);
				token.append('"');
				token.appendString(value.substring(1, value.length() - 1));
				token.append('"');
				tokens.add(token);
			} else if (Util.translate(c) != null) {
				Token token = new Token(Type.IDENT);
				token.appendString(parseIdent());
				tokens.add(token);
			} else {
				Token token = new Token(Type.UNKNOWN);
				token.append(c);
				tokens.add(token);
				this.next();
			}
		}
		return tokens;
	}

}
