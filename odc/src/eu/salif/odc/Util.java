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

	private static final Token TOKEN_IMPORT = new Token(Type.KEYWORD, "import");
	private static final Token TOKEN_AS = new Token(Type.KEYWORD, "as");
	private static final Token TOKEN_IS = new Token(Type.KEYWORD, "is");
	private static final Token TOKEN_RETURN = new Token(Type.KEYWORD, "return");
	private static final Token TOKEN_NEKA = new Token(Type.KEYWORD, "нека");
	private static final Token TOKEN_MUT = new Token(Type.KEYWORD, "mut");
	private static final Token TOKEN_FOR = new Token(Type.KEYWORD, "for");
	private static final Token TOKEN_IN = new Token(Type.KEYWORD, "in");
	private static final Token TOKEN_BREAK = new Token(Type.KEYWORD, "break");
	private static final Token TOKEN_CONTINUE = new Token(Type.KEYWORD, "continue");
	private static final Token TOKEN_IF = new Token(Type.KEYWORD, "if");
	private static final Token TOKEN_ELSE = new Token(Type.KEYWORD, "else");
	private static final Token TOKEN_MODULE = new Token(Type.TYPE, "module");
	private static final Token TOKEN_FN = new Token(Type.TYPE, "fn");
	private static final Token TOKEN_STRUCT = new Token(Type.TYPE, "struct");
	private static final Token TOKEN_INTERFACE = new Token(Type.TYPE, "interface");
	private static final Token TOKEN_ENUM = new Token(Type.TYPE, "enum");
	private static final Token TOKEN_MAP = new Token(Type.TYPE, "map");
	private static final Token TOKEN_BYTE = new Token(Type.TYPE, "byte");
	private static final Token TOKEN_I8 = new Token(Type.TYPE, "i8");
	private static final Token TOKEN_I16 = new Token(Type.TYPE, "i16");
	private static final Token TOKEN_INT = new Token(Type.TYPE, "int");
	private static final Token TOKEN_I64 = new Token(Type.TYPE, "i64");
	private static final Token TOKEN_F32 = new Token(Type.TYPE, "f32");
	private static final Token TOKEN_F64 = new Token(Type.TYPE, "f64");
	private static final Token TOKEN_STRING = new Token(Type.TYPE, "string");
	private static final Token TOKEN_BOOL = new Token(Type.TYPE, "bool");
	private static final Token TOKEN_TRUE = new Token(Type.CONSTANT, "true");
	private static final Token TOKEN_FALSE = new Token(Type.CONSTANT, "false");
	private static final Token TOKEN_ASSERT = new Token(Type.KEYWORD, "assert");
	private static final Token TOKEN_PRINTLN = new Token(Type.BUILTIN, "println");
	private static final Token TOKEN_PRINT = new Token(Type.BUILTIN, "print");
	private static final Token TOKEN_EPRINTLN = new Token(Type.BUILTIN, "eprintln");
	private static final Token TOKEN_EPRINT = new Token(Type.BUILTIN, "eprint");
	private static final Token TOKEN_EXIT = new Token(Type.BUILTIN, "exit");
	private static final Token TOKEN_PANIC = new Token(Type.BUILTIN, "panic");

	public static Token translateIdent(Token token) {
		return switch (token.getValue()) {
			case "импортни" -> TOKEN_IMPORT;
			case "като" -> TOKEN_AS;
			case "е" -> TOKEN_IS;
			case "върни" -> TOKEN_RETURN;
			case "нека" -> TOKEN_NEKA;
			case "пром" -> TOKEN_MUT;
			case "за" -> TOKEN_FOR;
			case "във" -> TOKEN_IN;
			case "прекъсни" -> TOKEN_BREAK;
			case "продължи" -> TOKEN_CONTINUE;
			case "ако" -> TOKEN_IF;
			case "иначе" -> TOKEN_ELSE;
			case "модул" -> TOKEN_MODULE;
			case "функция" -> TOKEN_FN;
			case "структура" -> TOKEN_STRUCT;
			case "интерфейс" -> TOKEN_INTERFACE;
			case "енум" -> TOKEN_ENUM;
			case "речник" -> TOKEN_MAP;
			case "байт" -> TOKEN_BYTE;
			case "ч8" -> TOKEN_I8;
			case "ч16" -> TOKEN_I16;
			case "ч32" -> TOKEN_INT;
			case "ч64" -> TOKEN_I64;
			case "ф32" -> TOKEN_F32;
			case "ф64" -> TOKEN_F64;
			case "низ" -> TOKEN_STRING;
			case "бул" -> TOKEN_BOOL;
			case "вярно" -> TOKEN_TRUE;
			case "невярно" -> TOKEN_FALSE;
			case "потвърди" -> TOKEN_ASSERT;
			case "принтнилн" -> TOKEN_PRINTLN;
			case "принтни" -> TOKEN_PRINT;
			case "епринтнилн" -> TOKEN_EPRINTLN;
			case "епринтни" -> TOKEN_EPRINT;
			case "излез" -> TOKEN_EXIT;
			case "паник" -> TOKEN_PANIC;
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
