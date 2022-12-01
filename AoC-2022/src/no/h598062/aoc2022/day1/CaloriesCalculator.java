package no.h598062.aoc2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// part 1 ans: 68802
public class CaloriesCalculator {
	public static void main(String[] args) {
		String         filepath = "./resources/input-day1.txt";
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine = file.readLine();
			int currentElf = 0;
			int currentElfCals = 0;
			int[] maxCals = {0, 0, 0};
			int[] maxCalElf = {0, 0, 0};
			while (currentLine != null){
				if (currentLine.equals("")) {
					if (currentElfCals > maxCals[0]) {
						maxCals[2] = maxCals[1];
						maxCals[1] = maxCals[0];
						maxCals[0] = currentElfCals;
						maxCalElf[2] = maxCalElf[1];
						maxCalElf[1] = maxCalElf[0];
						maxCalElf[0] = currentElf;
					} else if (currentElfCals > maxCals[1]) {
						maxCals[2] = maxCals[1];
						maxCals[1] = currentElfCals;
						maxCalElf[2] = maxCalElf[1];
						maxCalElf[1] = currentElf;
					} else if (currentElfCals > maxCals[2]) {
						maxCals[2] = currentElfCals;
						maxCalElf[2] = currentElf;
					}
					currentElf += 1;
					currentElfCals = 0;
				} else {
					currentElfCals += Integer.parseInt(currentLine);
				}
				currentLine = file.readLine();
			}
			System.out.println("Found " + (currentElf+1) + " elves.");
			System.out.println("Elf with most cals was elf " + maxCalElf[0] + " with " + maxCals[0] + " cals.");
			System.out.println("Elf with second most cals was elf " + maxCalElf[1] + " with " + maxCals[1] + " cals.");
			System.out.println("Elf with third most cals was elf " + maxCalElf[2] + " with " + maxCals[2] + " cals.");
			System.out.println("They had in total " + (maxCals[0] + maxCals[1] + maxCals[2]) + " cals.");
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
	}
}
