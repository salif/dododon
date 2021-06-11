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
		if (args.length < 2) {
			die(new Error("need two arguments"));
		}
		Path srcDir = Path.of(args[0]);
		checkDir(srcDir);
		Path outDir = Path.of(args[1]);
		if (!Files.exists(outDir)) {
			try {
				Files.createDirectories(outDir);
			} catch (IOException e) {
				die(e);
			}
		}
		checkDir(outDir);
		try {
			compileDir(srcDir, outDir);
		} catch (Exception e) {
			die(e);
		}
	}

	public static void compileDir(Path srcDir, Path outDir) throws Exception {
		Path[] paths = Files.list(srcDir).toArray(Path[]::new);
		for (Path path : paths) {
			File file = path.toFile();
			if (file.isFile()) {
				if (file.getName().endsWith(".донд") || file.getName().endsWith(".dond")) {
					compileFile(file, outDir);
				}
			} else if (file.isDirectory()) {
				Path newOutDir = outDir.resolve(Util.translateName(file.getName()));
				Files.createDirectories(newOutDir);
				compileDir(path, newOutDir);
			}
		}
	}

	public static void compileFile(File file, Path outDir) throws IOException, Exception {
		String fileName = file.getName();
		Path outFileName = outDir
				.resolve(Util.translateName(fileName.substring(0, fileName.lastIndexOf('.'))).concat(".v"));
		try (Reader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			Files.writeString(outFileName, Compiler.compile(buffer), StandardCharsets.UTF_8);
		}
	}

	public static void checkDir(Path dir) {
		if (!Files.isDirectory(dir)) {
			die(new Exception(String.format("%s is not a directory", dir.toString())));
		}
	}

	static void die(Throwable t) {
		t.printStackTrace();
		System.exit(1);
	}

}
