package no.h598062.aoc2022.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HillClimbingAlgorithm {
	public static void main(String[] args) {
		// String filepath = "./resources/input-day12.txt";
		String filepath = "./resources/input-day12-sample.txt";

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

