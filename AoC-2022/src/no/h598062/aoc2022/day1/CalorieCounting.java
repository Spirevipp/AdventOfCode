package no.h598062.aoc2022.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// part 1 ans: 68802
public class CalorieCounting {
	public static void main(String[] args) {
		String filepath = "./resources/input-day1.txt";
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine    = file.readLine();
			int    currentElf     = 0;
			int    currentElfCals = 0;
			int[]  maxCals        = {0, 0, 0};
			int[]  maxCalElf      = {0, 0, 0};
			while (currentLine != null) {
				if (currentLine.equals("")) {
					if (currentElfCals > maxCals[0]) {
						maxCals[2]   = maxCals[1];
						maxCals[1]   = maxCals[0];
						maxCals[0]   = currentElfCals;
						maxCalElf[2] = maxCalElf[1];
						maxCalElf[1] = maxCalElf[0];
						maxCalElf[0] = currentElf;
					} else if (currentElfCals > maxCals[1]) {
						maxCals[2]   = maxCals[1];
						maxCals[1]   = currentElfCals;
						maxCalElf[2] = maxCalElf[1];
						maxCalElf[1] = currentElf;
					} else if (currentElfCals > maxCals[2]) {
						maxCals[2]   = currentElfCals;
						maxCalElf[2] = currentElf;
					}
					currentElf += 1;
					currentElfCals = 0;
				} else {
					currentElfCals += Integer.parseInt(currentLine);
				}
				currentLine = file.readLine();
			}
			System.out.println("Found " + (currentElf + 1) + " elves.");
			System.out.println("Elf with most cals was elf " + maxCalElf[0] + " with " + maxCals[0] + " cals.");
			System.out.println("Elf with second most cals was elf " + maxCalElf[1] + " with " + maxCals[1] + " cals.");
			System.out.println("Elf with third most cals was elf " + maxCalElf[2] + " with " + maxCals[2] + " cals.");
			System.out.println("They had in total " + (maxCals[0] + maxCals[1] + maxCals[2]) + " cals.");
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}

			gpt(filepath);

	}

	public static void gpt(String filepath) {
		// Open the input file
		File inputFile = new File(filepath);
		try (Scanner in = new Scanner(inputFile)) {
			// Create a list to store the total Calories carried by each Elf
			List<Integer> elfCalories = new ArrayList<>();

			// Create a variable to store the total Calories carried by the current Elf
			int currentElfCalories = 0;

			// Read the file line by line
			while (in.hasNextLine()) {
				String line = in.nextLine();

				// If the line is empty, add the current Elf's Calories to the list and reset the total
				if (line.isEmpty()) {
					elfCalories.add(currentElfCalories);
					currentElfCalories = 0;
					continue;
				}

				// If the line is not empty, add its value to the current Elf's total
				int calories = Integer.parseInt(line);
				currentElfCalories += calories;
			}

			// Add the last Elf's total Calories to the list
			elfCalories.add(currentElfCalories);

			// Sort the list of Calories in descending order
			elfCalories.sort(Collections.reverseOrder());

			// Take the top three Elves carrying the most Calories
			int topThreeCalories = 0;
			for (int i = 0; i < 3; i++) {
				topThreeCalories += elfCalories.get(i);
			}

			// Print the total number of Calories carried by the top three Elves
			System.out.println("The top three Elves are carrying " + topThreeCalories + " Calories in total.");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
