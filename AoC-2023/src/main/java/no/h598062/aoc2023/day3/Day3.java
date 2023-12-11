package no.h598062.aoc2023.day3;

import java.util.ArrayList;
import java.util.List;

import static no.h598062.aoc2023.Utils.readLines;

public class Day3 {
	private static final String INPUT_PATH = "src/main/resources/day3/input.txt";
	private static final String SAMPLE_PATH = "src/main/resources/day3/sample.txt";

	public static void main(String[] args) {
		System.out.println("Hello AOC Day 3!");
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
		boolean[][] checkedMap = new boolean[lines.size()][lines.getFirst()
		                                                        .length()];
		List<Integer> engineParts = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == '.') {
					checkedMap[i][j] = true;
				} else if ("&+-#@$*/%=".contains("" + line.charAt(j))) {
					checkedMap[i][j] = true;
					boolean finished = false;
					// 0: min, 1: max, 2: current
					int[] xLims = {j - 1 < 0 ? j : j - 1, j + 1 >= line.length() ? j : j + 1};
					int[] yLims = {i - 1 < 0 ? i : i - 1, i + 1 >= lines.size() ? i : i + 1};
					int x = xLims[0];
					int y = yLims[0];
					while (!finished) {
						if (!checkedMap[y][x]) {
							checkedMap[y][x] = true;
							if ("&+-#@$*/%=.".contains("" + lines.get(y)
							                                     .charAt(x))) {
								// glemte at eg gjør adjacent sjekk i samme loop som sjekk etter . og symbol
								System.out.printf("oopsie: x %d y %d%n", x, y);
								continue;
							}
							String l2 = lines.get(y);
							int numstart = x;
							int numend = x;
							boolean fin = false;
							while (!fin && numstart - 1 >= 0) {
								char c = l2.charAt(numstart - 1);
								if (!checkedMap[y][numstart - 1] && c >= '0' && c <= '9') { // tall sjekk er redundant tror eg
									numstart--;
									checkedMap[y][numstart] = true;
								} else {
									fin = true;
								}
							}
							fin = false;
							while (!fin && numend + 1 < l2.length()) {
								char c = l2.charAt(numend + 1);
								if (!checkedMap[y][numend + 1] && c >= '0' && c <= '9') { // tall sjekk er redundant tror eg
									numend++;
									checkedMap[y][numend] = true;
								} else {
									fin = true;
								}
							}
							String partStr = l2.substring(numstart, numend + 1);
							engineParts.add(Integer.parseInt(partStr));
						}
						x++;
						if (x > xLims[1]) {
							x = xLims[0];
							y++;
						}
						if (y > yLims[1]) {
							finished = true;
						}
					}
				}
			}
		}
		System.out.println("Part number sum: " + engineParts.stream()
		                                                    .mapToInt(Integer::intValue)
		                                                    .sum());

		// if (line.charAt(j) != '.' && !"&+-#@$*/%=".contains("" + line.charAt(j))) {

	}

	private static void part2(String inputPath) {
		List<String> lines = readLines(inputPath);
		boolean[][] checkedMap = new boolean[lines.size()][lines.getFirst()
		                                                        .length()];
		List<Integer> engineParts = new ArrayList<>();
		List<Integer> gearRatios = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == '.') {
					checkedMap[i][j] = true;
				} else if ("&+-#@$*/%=".contains("" + line.charAt(j))) {
					checkedMap[i][j] = true;
					boolean finished = false;
					// 0: min, 1: max, 2: current
					int[] xLims = {j - 1 < 0 ? j : j - 1, j + 1 >= line.length() ? j : j + 1};
					int[] yLims = {i - 1 < 0 ? i : i - 1, i + 1 >= lines.size() ? i : i + 1};
					int x = xLims[0];
					int y = yLims[0];
					List<Integer> gears = new ArrayList<>();
					while (!finished) {
						if (!checkedMap[y][x]) {
							checkedMap[y][x] = true;
							if ("&+-#@$*/%=.".contains("" + lines.get(y)
							                                     .charAt(x))) {
								// glemte at eg gjør adjacent sjekk i samme loop som sjekk etter . og symbol
								System.out.printf("oopsie: x %d y %d%n", x, y);
								continue;
							}
							String l2 = lines.get(y);
							int numstart = x;
							int numend = x;
							boolean fin = false;
							while (!fin && numstart - 1 >= 0) {
								char c = l2.charAt(numstart - 1);
								if (!checkedMap[y][numstart - 1] && c >= '0' && c <= '9') { // tall sjekk er redundant tror eg
									numstart--;
									checkedMap[y][numstart] = true;
								} else {
									fin = true;
								}
							}
							fin = false;
							while (!fin && numend + 1 < l2.length()) {
								char c = l2.charAt(numend + 1);
								if (!checkedMap[y][numend + 1] && c >= '0' && c <= '9') { // tall sjekk er redundant tror eg
									numend++;
									checkedMap[y][numend] = true;
								} else {
									fin = true;
								}
							}
							String partStr = l2.substring(numstart, numend + 1);
							int part = Integer.parseInt(partStr);
							engineParts.add(part);
							gears.add(part);
						}
						x++;
						if (x > xLims[1]) {
							x = xLims[0];
							y++;
						}
						if (y > yLims[1]) {
							finished = true;
						}
					}
					if (line.charAt(j) == '*' && gears.size() == 2) {
						gearRatios.add(gears.get(0) * gears.get(1));
					}
				}
			}
		}
		System.out.println("Part number sum: " + engineParts.stream()
		                                                    .mapToInt(Integer::intValue)
		                                                    .sum());

		System.out.println("Gear ratio sum: " + gearRatios.stream()
		                                                  .mapToInt(Integer::intValue)
		                                                  .sum());

	}
}
