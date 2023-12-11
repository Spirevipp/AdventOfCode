package no.h598062.aoc2023.day2;

import java.util.ArrayList;
import java.util.List;

import static no.h598062.aoc2023.Utils.readLines;

public class Day2 {
	private static final String INPUT_PATH = "src/main/resources/day2/input.txt";
	private static final String SAMPLE_PATH = "src/main/resources/day2/sample.txt";

	public static void main(String[] args) {
		System.out.println("Hello AOC Day 2!");
		System.out.println("Part 1:");
		System.out.println("Sample:");
		part1(SAMPLE_PATH);
		System.out.println();
		System.out.println("Input:");
		part1(INPUT_PATH);
		System.out.println("Part 2:");
		System.out.println("Sample:");
		part2(SAMPLE_PATH);
		System.out.println();
		System.out.println("Input:");
		part2(INPUT_PATH);
	}

	private static void part1(String inputPath) {
		List<String> lines = readLines(inputPath);
		List<Integer> gameIDs = new ArrayList<>();
		for (String line : lines) {
			boolean invalid = false;
			String[] split = line.split(": ");
			int gameID = Integer.parseInt(split[0].split(" ")[1]);
			String[] gameSets = split[1].split("; ");
			for (String set : gameSets) {
				if (invalid) {
					break;
				}
				String[] setAmounts = set.split(", ");
				for (String setAmount : setAmounts) {
					if (invalid) {
						break;
					}
					String[] splitAmount = setAmount.split(" ");
					int amount = Integer.parseInt(splitAmount[0]);
					switch (splitAmount[1]) {
						case "red" -> {
							if (amount > 12) {
								invalid = true;
							}
						}
						case "green" -> {
							if (amount > 13) {
								invalid = true;
							}
						}
						case "blue" -> {
							if (amount > 14) {
								invalid = true;
							}
						}
						default -> {
							System.out.println("Invalid color: " + splitAmount[1]);
							invalid = true;
						}
					}
				}
			}
			if (!invalid) {
				gameIDs.add(gameID);
				System.out.println("Valid game ID: " + gameID);
			}
		}
		System.out.println("Valid game IDs sum: " + gameIDs.stream()
		                                                   .mapToInt(Integer::intValue)
		                                                   .sum());
	}

	private static void part2(String inputPath) {
		List<String> lines = readLines(inputPath);
		List<Integer> cubePowers = new ArrayList<>();
		for (String line : lines) {
			String[] split = line.split(": ");
			int gameID = Integer.parseInt(split[0].split(" ")[1]);
			String[] gameSets = split[1].split("; ");
			int maxRed = 0;
			int maxGreen = 0;
			int maxBlue = 0;
			for (String set : gameSets) {
				String[] setAmounts = set.split(", ");
				for (String setAmount : setAmounts) {
					String[] splitAmount = setAmount.split(" ");
					int amount = Integer.parseInt(splitAmount[0]);
					switch (splitAmount[1]) {
						case "red" -> maxRed = Math.max(maxRed, amount);
						case "green" -> maxGreen = Math.max(maxGreen, amount);
						case "blue" -> maxBlue = Math.max(maxBlue, amount);
						default -> System.out.println("Invalid color: " + splitAmount[1]);
					}
				}
			}
			cubePowers.add(maxRed * maxGreen * maxBlue);
			System.out.println("Valid game ID: " + gameID + " with cube power: " + maxRed * maxGreen * maxBlue);

		}
		System.out.println("Valid game cube power sum: " + cubePowers.stream()
		                                                             .mapToInt(Integer::intValue)
		                                                             .sum());
	}

}
