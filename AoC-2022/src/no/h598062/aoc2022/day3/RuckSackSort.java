package no.h598062.aoc2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class RuckSackSort {
	public static void main(String[] args) {
		String filepath = "./resources/input-day3.txt";
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine = file.readLine();
			int priorities = 0;
			int badgePriorities = 0;
			String[] group = new String[3];
			int groupCounter = 0;
			while (currentLine != null) {
				if (groupCounter < 2) {
					group[groupCounter] = currentLine;
				} else {
					group[groupCounter] = currentLine;
					badgePriorities += findBadgePriority(group);
					groupCounter = -1;
				}
				priorities += findRucksackPriority(currentLine);
				currentLine = file.readLine();
				groupCounter++;
			}
			System.out.println("Total priorities is: " + priorities);
			System.out.println("Total badge priority is: " + badgePriorities);
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
	}

	public static int findRucksackPriority(String rucksack) {
		int      lineLen      = rucksack.length();
		String[] compartments = {rucksack.substring(0, lineLen / 2),
		                         rucksack.substring(lineLen / 2)};
		HashMap<Character, Integer> charmap = new HashMap<>();
		for (char c : compartments[0].toCharArray()) {
			if (charmap.containsKey(c)) {
				charmap.put(c, charmap.get(c) + 1);
			} else {
				charmap.put(c, 1);
			}
		}
		for (char c : compartments[1].toCharArray()) {
			if (charmap.containsKey(c)) {
				return calculatePriority(c);
			}
		}
		return 0;
	}

	public static int calculatePriority(char c) {
		String charstring = "abcdefghijklmnopqrstuvwxyz";
		charstring += charstring.toUpperCase();

		return charstring.indexOf(c) + 1;
	}

	public static int findBadgePriority(String[] group) {
		char commonChar = 0;
		for(int i=0; i < group[0].length(); i++){
			if(group[1].indexOf(group[0].charAt(i)) != -1 && group[2].indexOf(group[0].charAt(i)) != -1){
				commonChar = group[0].charAt(i);
				break;
			}
		}
		return calculatePriority(commonChar);
	}
}
