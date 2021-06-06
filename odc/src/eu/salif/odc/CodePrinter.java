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

public class CodePrinter {
	public static void main(String[] args) {
		for (String arg : args) {
			File file = Path.of(arg).toFile();
			if (file.isDirectory()) {
				continue;
			}
			try {
				System.out.println(colorize(file).toString());
			} catch (Exception e) {
				Main.die(e);
			}
		}
	}

	public static StringBuilder colorize(File file) throws Exception {
		try (Reader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			List<Token> tokens = Parser.parse(buffer);
			StringBuilder builder = new StringBuilder();
			for (Token token : tokens) {
				String value = token.getValue();
				switch (token.getType()) {
					case WHITESPACE:
						builder.append(value);
						break;
					case COMMENT:
						builder.append(Color.ANSI_BRIGHT_BLACK);
						builder.append(value);
						builder.append(Color.ANSI_RESET);
						break;
					case NUMBER:
						builder.append(Color.ANSI_BRIGHT_BLUE);
						builder.append(value);
						builder.append(Color.ANSI_RESET);
						break;
					case STRING:
						builder.append(Color.ANSI_YELLOW);
						builder.append(value);
						builder.append(Color.ANSI_RESET);
						break;
					case IDENT:
						Token newToken = Util.translateIdent(token);
						switch (newToken.getType()) {
							case IDENT:
								builder.append(Color.ANSI_WHITE);
								builder.append(value);
								builder.append(Color.ANSI_RESET);
								break;
							case KEYWORD:
								builder.append(Color.ANSI_RED);
								builder.append(value);
								builder.append(Color.ANSI_RESET);
								break;
							case TYPE:
								builder.append(Color.ANSI_CYAN);
								builder.append(value);
								builder.append(Color.ANSI_RESET);
								break;
							case CONSTANT:
								builder.append(Color.ANSI_BLUE);
								builder.append(value);
								builder.append(Color.ANSI_RESET);
								break;
							default:
								break;
						}
						break;
					case UNKNOWN:
						builder.append(Color.ANSI_PURPLE);
						builder.append(value);
						builder.append(Color.ANSI_RESET);
						break;
					default:
						builder.append(value);
						break;
				}
			}
			return builder;
		}
	}
}
