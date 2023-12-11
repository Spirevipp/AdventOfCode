package no.h598062.aoc2023;

import java.util.List;

import static no.h598062.aoc2023.Utils.readLines;

public class Template {
	private static final String INPUT_PATH = "src/main/resources/day1/input.txt";
	private static final String SAMPLE_PATH = "src/main/resources/day1/sample.txt";

	public static void main(String[] args) {
		System.out.println("Hello AOC!");
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
	}

	private static void part2(String inputPath) {
		List<String> lines = readLines(inputPath);
	}

}
