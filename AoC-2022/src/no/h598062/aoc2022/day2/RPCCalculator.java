package no.h598062.aoc2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RPCCalculator {
	public static void main(String[] args) {
		String  filepath = "./resources/input-day2.txt";
		boolean part1    = false;
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine = file.readLine();
			int    score       = 0;
			int    rounds      = 0;
			while (currentLine != null) {
				String[] round = currentLine.split(" ");
				if (part1) {
					// Rock     - A X   - 1
					// Paper    - B Y   - 2
					// Scissor  - C Z   - 3
					// Win 6    Draw 3  Loss 0
					switch (round[0]) {
						case "A" -> {
							switch (round[1]) {
								case "X" -> score += 1 + 3;
								case "Y" -> score += 2 + 6;
								case "Z" -> score += 3;
								default -> throw new IllegalStateException("Unexpected value: " + round[1]);
							}
						}
						case "B" -> {
							switch (round[1]) {
								case "X" -> score += 1;
								case "Y" -> score += 2 + 3;
								case "Z" -> score += 3 + 6;
								default -> throw new IllegalStateException("Unexpected value: " + round[1]);
							}
						}
						case "C" -> {
							switch (round[1]) {
								case "X" -> score += 1 + 6;
								case "Y" -> score += 2;
								case "Z" -> score += 3 + 3;
								default -> throw new IllegalStateException("Unexpected value: " + round[1]);
							}
						}
						default -> throw new IllegalStateException("Unexpected value: " + round[0]);
					}
				} else {
					// Rock     - A     - 1
					// Paper    - B     - 2
					// Scissor  - C     - 3
					// Win      - Z     - 6
					// Draw     - Y     - 3
					// Loss     - X     - 0
					switch (round[0]) {
						case "A" -> {
							switch (round[1]) {
								case "X" -> score += 3;
								case "Y" -> score += 1 + 3;
								case "Z" -> score += 2 + 6;
								default -> throw new IllegalStateException("Unexpected value: " + round[1]);
							}
						}
						case "B" -> {
							switch (round[1]) {
								case "X" -> score += 1;
								case "Y" -> score += 2 + 3;
								case "Z" -> score += 3 + 6;
								default -> throw new IllegalStateException("Unexpected value: " + round[1]);
							}
						}
						case "C" -> {
							switch (round[1]) {
								case "X" -> score += 2;
								case "Y" -> score += 3 + 3;
								case "Z" -> score += 1 + 6;
								default -> throw new IllegalStateException("Unexpected value: " + round[1]);
							}
						}
						default -> throw new IllegalStateException("Unexpected value: " + round[0]);
					}
				}
				currentLine = file.readLine();
				rounds += 1;

			}
			System.out.println("Played " + rounds + " rounds");
			System.out.println("Score is: " + score);
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
	}
}
