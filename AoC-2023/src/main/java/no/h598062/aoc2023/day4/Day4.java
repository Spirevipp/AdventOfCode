package no.h598062.aoc2023.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static no.h598062.aoc2023.Utils.readLines;

public class Day4 {
	private static final String INPUT_PATH = "src/main/resources/day4/input.txt";
	private static final String SAMPLE_PATH = "src/main/resources/day4/sample.txt";

	public static void main(String[] args) {
		System.out.println("Hello AOC Day 4!");
		System.out.println("Part 1:");
		System.out.println("Sample:");
		part1(SAMPLE_PATH);
		System.out.println();
		System.out.println("Input:");
		part1(INPUT_PATH);
		System.out.println();
		System.out.println("Part 2:");
		System.out.println("Sample:");
		part2(SAMPLE_PATH);
		System.out.println();
		System.out.println("Input:");
		part2(INPUT_PATH);
	}

	private static void part1(String inputPath) {
		List<String> lines = readLines(inputPath);
		int scoreSum = 0;
		for (String line : lines) {
			// 0: winning numbers   1: your numbers
			String[] numbergroups = line.split(":\\s+")[1].split("\\s+\\|\\s+");
			List<Integer> winningNums = Arrays.stream(numbergroups[0].split("\\s+"))
			                                  .map(Integer::parseInt)
			                                  .toList();
			List<Integer> yourNums = Arrays.stream(numbergroups[1].split("\\s+"))
			                               .map(Integer::parseInt)
			                               .toList();
			int score = 0;
			for (Integer winningNum : winningNums) {
				if (yourNums.contains(winningNum)) {
					if (score == 0) {
						score++;
					} else {
						score *= 2;
					}
				}
			}
			scoreSum += score;
		}
		System.out.printf("Score is: %d%n", scoreSum);
	}

	private static void part2(String inputPath) {
		List<String> lines = readLines(inputPath);
		List<Integer> cardCount = Stream.generate(() -> 1)
		                                .limit(lines.size())
		                                .collect(Collectors.toList());
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			// 0: winning numbers   1: your numbers
			String[] numbergroups = line.split(":\\s+")[1].split("\\s+\\|\\s+");
			List<Integer> winningNums = Arrays.stream(numbergroups[0].split("\\s+"))
			                                  .map(Integer::parseInt)
			                                  .toList();
			List<Integer> yourNums = Arrays.stream(numbergroups[1].split("\\s+"))
			                               .map(Integer::parseInt)
			                               .toList();
			int wins = 0;
			for (Integer num : winningNums) {
				if (yourNums.contains(num)) {
					wins++;
				}
			}
			for (int j = 1; j <= wins && j + i < cardCount.size(); j++) {
				cardCount.set(i + j, cardCount.get(i + j) + cardCount.get(i));
			}
		}
		System.out.printf("Total cards: %d%n",
		                  cardCount.stream()
		                           .mapToInt(Integer::intValue)
		                           .sum());
	}

}
