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

import java.io.Reader;
import java.util.List;

public class Compiler {
	public static StringBuilder compile(Reader buffer) throws Exception {
		List<Token> tokens = Parser.parse(buffer);
		StringBuilder builder = new StringBuilder();
		boolean isWFO = false;
		boolean isNFD = false;
		for (Token token : tokens) {
			if (isWFO) {
				if (isNFD) {
					isNFD = false;
					continue;
				}
				if (token.getValue().equals("=")) {
					token = new Token(Type.UNKNOWN, ":=");
					isWFO = false;
				}
			}
			if (token.getType().equals(Type.IDENT)) {
				if (token.getValue().equals("нека")) {
					isWFO = true;
					isNFD = true;
					continue;
				}
				if (token.getValue().equals("пром")) {
					isWFO = true;
				}
				token = Util.translateIdent(token);
			}
			builder.append(token.toString());
		}
		return builder;
	}
}
