package no.h598062.aoc2022.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TuningTrouble {
	public static void main(String[] args) {
		String         filepath       = "./resources/input-day6.txt";
		String input = "";
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			input = file.readLine();
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
		char[] inputArr = input.toCharArray();

		int markerLength = 14;
		for (int i = 0; i < inputArr.length; i++) {
			HashSet<Character> match = new HashSet<>();
			if (i+markerLength > inputArr.length) break;
			for (int j = i; j < i + markerLength; j++) {
				match.add(inputArr[j]);
			}
			if (match.size() == markerLength) {
				System.out.println("Found marker at position " + i + " sequence starts at " + (i+markerLength));
				System.out.println("The marker was " + match);
			}
		}
	}
}
