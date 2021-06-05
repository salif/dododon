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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			die(new Error("need more args"));
		}
		Path dirPath = Path.of(args[0]);
		try {
			compileDir(dirPath, ".dond");
			compileDir(dirPath, ".донд");
		} catch (Exception e) {
			die(e);
		}
	}

	public static void compileDir(Path dir, String ext) throws Exception {
		Path[] paths = Files.list(dir).filter(e -> e.toFile().isFile() && e.toFile().getName().endsWith(ext))
				.toArray(Path[]::new);
		for (Path path : paths) {
			compileFile(dir, path);
		}
	}

	public static void compileFile(Path dir, Path path) throws IOException, Exception {
		File file = path.toFile();
		String fileName = file.getName();
		Path outFileName = dir
				.resolve(Util.translateName(fileName.substring(0, fileName.lastIndexOf('.'))).concat(".v"));
		try (Reader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			Files.writeString(outFileName, Compiler.compile(buffer), StandardCharsets.UTF_8);
		}
	}

	private static void die(Throwable t) {
		t.printStackTrace();
		System.exit(1);
	}

}
