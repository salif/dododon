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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class AstPrinter {
	public static void main(String[] args) {
		for (String arg : args) {
			File file = Path.of(arg).toFile();
			if (file.isDirectory()) {
				continue;
			}
			try {
				System.out.println(print(file).toString());
			} catch (Exception e) {
				Main.die(e);
			}
		}
	}

	public static StringBuilder print(File file) throws Exception {
		try (Reader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			List<Token> tokens = Parser.parse(buffer);
			StringBuilder builder = new StringBuilder();
			for (Token token : tokens) {
				if (token.getType().equals(Type.WHITESPACE)) {
					builder.append(
							String.format("type: %s, code: %d%n", token.getType(), token.getValue().codePointAt(0)));
				} else if (token.getType().equals(Type.UNKNOWN)) {
					builder.append(String.format("type: %s, code: %d, value: %s%n", token.getType(),
							token.getValue().codePointAt(0), token.getValue()));
				} else {
					if (token.getType().equals(Type.IDENT)) {
						token = Util.translateIdent(token);
					}
					builder.append(String.format("type: %s, value: %s%n", token.getType(), token.getValue()));
				}
			}
			return builder;
		}
	}
}
