package no.h598062.aoc2022.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DistressSignal {
	public static void main(String[] args) {
		// String filepath = "./resources/input-day13.txt";
		String filepath = "./resources/input-day13-sample.txt";

		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			while ((currentLine = file.readLine()) != null) {
			}
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}

	}
}
