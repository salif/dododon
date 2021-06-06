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

public class Util {

	public static boolean isDigit(char c) {
		return c > 47 && c < 58;
	}

	public static boolean isNIdent(char c) {
		return (c > 64 && c < 91) || (c > 96 && c < 123) || c == 95;
	}

	public static String translate(char c) {
		if (isNIdent(c) || isDigit(c)) {
			return String.valueOf(c);
		}
		return switch (c) {
			case 'А' -> "A";
			case 'Б' -> "B";
			case 'В' -> "V";
			case 'Г' -> "G";
			case 'Д' -> "D";
			case 'Е' -> "E";
			case 'Ж' -> "J";
			case 'З' -> "Z";
			case 'И' -> "I";
			case 'Й' -> "WY";
			case 'К' -> "K";
			case 'Л' -> "L";
			case 'М' -> "M";
			case 'Н' -> "N";
			case 'О' -> "O";
			case 'П' -> "P";
			case 'Р' -> "R";
			case 'С' -> "S";
			case 'Т' -> "T";
			case 'У' -> "U";
			case 'Ф' -> "F";
			case 'Х' -> "H";
			case 'Ц' -> "CTS";
			case 'Ч' -> "CH";
			case 'Ш' -> "C6";
			case 'Щ' -> "C6T";
			case 'Ъ' -> "WU";
			case 'Ю' -> "YU";
			case 'Я' -> "YA";
			case 'а' -> "a";
			case 'б' -> "b";
			case 'в' -> "v";
			case 'г' -> "g";
			case 'д' -> "d";
			case 'е' -> "e";
			case 'ж' -> "j";
			case 'з' -> "z";
			case 'и' -> "i";
			case 'й' -> "wy";
			case 'к' -> "k";
			case 'л' -> "l";
			case 'м' -> "m";
			case 'н' -> "n";
			case 'о' -> "o";
			case 'п' -> "p";
			case 'р' -> "r";
			case 'с' -> "s";
			case 'т' -> "t";
			case 'у' -> "u";
			case 'ф' -> "f";
			case 'х' -> "h";
			case 'ц' -> "cts";
			case 'ч' -> "ch";
			case 'ш' -> "c6";
			case 'щ' -> "c6t";
			case 'ъ' -> "wu";
			case 'ь' -> "wi";
			case 'ю' -> "yu";
			case 'я' -> "ya";
			case 'ѝ' -> "yi";
			default -> null;
		};
	}

	public static String translateName(String name) {
		StringBuilder builder = new StringBuilder();
		for (char c : name.toCharArray()) {
			builder.append(translate(c));
		}
		return builder.toString();
	}

	private static final Token TOKEN_PUB = new Token(Type.KEYWORD, "pub");
	private static final Token TOKEN_MODULE = new Token(Type.KEYWORD, "module");
	private static final Token TOKEN_IMPORT = new Token(Type.KEYWORD, "import");
	private static final Token TOKEN_FN = new Token(Type.KEYWORD, "fn");
	private static final Token TOKEN_RETURN = new Token(Type.KEYWORD, "return");
	private static final Token TOKEN_NEKA = new Token(Type.KEYWORD, "нека");
	private static final Token TOKEN_MUT = new Token(Type.KEYWORD, "mut");
	private static final Token TOKEN_STRUCT = new Token(Type.KEYWORD, "struct");
	private static final Token TOKEN_FOR = new Token(Type.KEYWORD, "for");
	private static final Token TOKEN_IN = new Token(Type.KEYWORD, "in");
	private static final Token TOKEN_IF = new Token(Type.KEYWORD, "if");
	private static final Token TOKEN_ELSE = new Token(Type.KEYWORD, "else");
	private static final Token TOKEN_MAP = new Token(Type.TYPE, "map");
	private static final Token TOKEN_BYTE = new Token(Type.TYPE, "byte");
	private static final Token TOKEN_INT = new Token(Type.TYPE, "int");
	private static final Token TOKEN_STRING = new Token(Type.TYPE, "string");
	private static final Token TOKEN_BOOL = new Token(Type.TYPE, "bool");
	private static final Token TOKEN_TRUE = new Token(Type.CONSTANT, "true");
	private static final Token TOKEN_FALSE = new Token(Type.CONSTANT, "false");
	private static final Token TOKEN_ASSERT = new Token(Type.KEYWORD, "assert");

	public static Token translateIdent(Token token) {
		return switch (token.getValue()) {
			case "pub" -> TOKEN_PUB;
			case "модул" -> TOKEN_MODULE;
			case "module" -> TOKEN_MODULE;
			case "импорт" -> TOKEN_IMPORT;
			case "import" -> TOKEN_IMPORT;
			case "функция" -> TOKEN_FN;
			case "fn" -> TOKEN_FN;
			case "върни" -> TOKEN_RETURN;
			case "return" -> TOKEN_RETURN;
			case "нека" -> TOKEN_NEKA;
			case "пром" -> TOKEN_MUT;
			case "mut" -> TOKEN_MUT;
			case "структура" -> TOKEN_STRUCT;
			case "struct" -> TOKEN_STRUCT;
			case "за" -> TOKEN_FOR;
			case "for" -> TOKEN_FOR;
			case "във" -> TOKEN_IN;
			case "in" -> TOKEN_IN;
			case "ако" -> TOKEN_IF;
			case "if" -> TOKEN_IF;
			case "иначе" -> TOKEN_ELSE;
			case "else" -> TOKEN_ELSE;
			case "речник" -> TOKEN_MAP;
			case "map" -> TOKEN_MAP;
			case "байт" -> TOKEN_BYTE;
			case "byte" -> TOKEN_BYTE;
			case "число" -> TOKEN_INT;
			case "int" -> TOKEN_INT;
			case "низ" -> TOKEN_STRING;
			case "string" -> TOKEN_STRING;
			case "бул" -> TOKEN_BOOL;
			case "bool" -> TOKEN_BOOL;
			case "вярно" -> TOKEN_TRUE;
			case "true" -> TOKEN_TRUE;
			case "невярно" -> TOKEN_FALSE;
			case "false" -> TOKEN_FALSE;
			case "потвърди" -> TOKEN_ASSERT;
			case "assert" -> TOKEN_ASSERT;
			default -> {
				StringBuilder builder = new StringBuilder();
				for (char c : token.getValue().toCharArray()) {
					builder.append(translate(c));
				}
				yield new Token(Type.IDENT, builder.toString());
			}
		};
	}
}
