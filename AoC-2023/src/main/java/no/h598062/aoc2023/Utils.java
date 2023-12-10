package no.h598062.aoc2023;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Utils {

	public static List<String> readLines(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			return br.lines()
			         .toList();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filePath);
			throw new RuntimeException(e);
		} catch (IOException e) {
			System.err.println("Something went wrong when reading file: " + filePath);
			throw new RuntimeException(e);
		}
	}
}
