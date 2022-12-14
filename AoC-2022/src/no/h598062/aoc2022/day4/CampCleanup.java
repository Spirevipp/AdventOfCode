package no.h598062.aoc2022.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CampCleanup {
	public static void main(String[] args) {
		String filepath = "./resources/input-day4.txt";
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			int    containedAssignments = 0;
			int    overlaps             = 0;
			while ((currentLine = file.readLine()) != null) {
				String[][] assignmentStrings = {currentLine.split(",")[0].split("-"),
				                                currentLine.split(",")[1].split("-")};
				int[][] assignments = new int[2][2];
				for (int i = 0; i < assignmentStrings.length; i++) {
					for (int j = 0; j < assignmentStrings[i].length; j++) {
						assignments[i][j] = Integer.parseInt(assignmentStrings[i][j]);
					}
				}
				if ((assignments[0][0] <= assignments[1][0] && assignments[0][1] >= assignments[1][1]) ||
				    (assignments[1][0] <= assignments[0][0] && assignments[1][1] >= assignments[0][1])) {
					containedAssignments++;
					overlaps++;
				} else if ((assignments[0][0] <= assignments[1][0] && assignments[0][1] >= assignments[1][0]) ||
				           (assignments[1][0] <= assignments[0][0] && assignments[1][1] >= assignments[0][0])) {
					overlaps++;
				}
			}
			System.out.println("Completely contained assignments is: " + containedAssignments);
			System.out.println("Overlapping assignments is: " + overlaps);
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
	}
}
