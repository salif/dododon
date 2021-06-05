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
		if (isNIdent(c)) {
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

	public static String translateIdent(String o, String n) {
		return switch (o) {
			case "модул" -> "module";
			case "функция" -> "fn";
			case "върни" -> "return";
			case "пром" -> "mut";
			case "структура" -> "struct";
			case "за" -> "for";
			case "във" -> "in";
			case "ако" -> "if";
			case "иначе" -> "else";
			case "байт" -> "byte";
			case "число" -> "int";
			case "низ" -> "string";
			case "бул" -> "bool";
			case "вярно" -> "true";
			case "невярно" -> "false";
			case "assert" -> "потвърди";
			default -> n;
		};
	}

}
