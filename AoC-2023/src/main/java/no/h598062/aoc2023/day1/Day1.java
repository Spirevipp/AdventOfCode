package no.h598062.aoc2023.day1;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static no.h598062.aoc2023.Utils.readLines;

public class Day1 {

	private static final String INPUT_PATH = "src/main/resources/day1/input.txt";
	private static final String SAMPLE_PATH = "src/main/resources/day1/sample.txt"; // 142
	private static final String SAMPLE2_PATH = "src/main/resources/day1/sample2.txt"; // 281
	private static final String NUMBER_REGEX = "one|two|three|four|five|six|seven|eight|nine|\\d";

	public static void main(String[] args) {
		System.out.println("Hello AOC Day 1!");

		System.out.println("part1:");
		System.out.print("sample: ");
		part1(SAMPLE_PATH);
		System.out.print("input: ");
		part1(INPUT_PATH);
		System.out.println("part2:");
		System.out.print("sample: ");
		part2(SAMPLE2_PATH);
		System.out.print("input: ");
		part2(INPUT_PATH);
	}

	public static void part1(String inputPath) {
		List<String> lines = readLines(inputPath);
		lines.stream()
		     .map(l -> {
			     String d1 = "";
			     String d2 = "";
			     for (int i = 0; i < l.length(); i++) {
				     char c = l.charAt(i);
				     if (c >= '0' && c <= '9') {
					     if (d1.isEmpty()) {
						     d1 = String.valueOf(c);
					     }
					     d2 = String.valueOf(c);
				     }
			     }
			     return Integer.parseInt(d1 + d2);
		     })
		     .reduce(Integer::sum)
		     .ifPresent(System.out::println);
	}

	public static void part2(String inputPath) {
		List<String> edgeCases = List.of("oneight", "twone", "threeight", "fiveight", "sevenine", "eightwo", "eighthree", "nineight");
		List<String> edgeCasesReplacements = List.of("oneeight",
		                                             "twoone",
		                                             "threeeight",
		                                             "fiveeight",
		                                             "sevennine",
		                                             "eighttwo",
		                                             "eightthree",
		                                             "nineeight");
		List<String> lines = readLines(inputPath);
		List<String> linesFixed = lines.stream()
		                               .map(l -> {
			                               for (int i = 0; i < edgeCases.size(); i++) {
				                               l = l.replace(edgeCases.get(i), edgeCasesReplacements.get(i));
			                               }
			                               return l;
		                               })
		                               .toList();
		Pattern p = Pattern.compile(NUMBER_REGEX);
		linesFixed.stream()
		          .map(l -> {
			          String d1 = "";
			          String d2 = "";
			          Matcher m = p.matcher(l);
			          List<String> matches = m.results()
			                                  .map(MatchResult::group)
			                                  .toList();
			          for (String s : matches) {
				          String digit = switch (s) {
					          case "zero" -> "0";
					          case "one" -> "1";
					          case "two" -> "2";
					          case "three" -> "3";
					          case "four" -> "4";
					          case "five" -> "5";
					          case "six" -> "6";
					          case "seven" -> "7";
					          case "eight" -> "8";
					          case "nine" -> "9";
					          default -> s;
				          };
				          if (d1.isEmpty()) {
					          d1 = digit;
				          }
				          d2 = digit;
			          }
			          return Integer.parseInt(d1 + d2);
		          })
		          .reduce(Integer::sum)
		          .ifPresent(System.out::println);
	}
}
