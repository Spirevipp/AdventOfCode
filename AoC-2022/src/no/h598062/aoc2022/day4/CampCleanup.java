package no.h598062.aoc2022.day4;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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
		gptcode(filepath);
	}

	// Function to visualize ranges
	public static void visualize(List<int[]> ranges) {
		// Create window for graph
		JFrame frame = new JFrame("Ranges");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);

		// Create panel for drawing graph
		JPanel panel = new JPanel() {
			// List to store ranges
			private final List<int[]> rangeList = ranges;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				// Set font for labels
				g.setFont(new Font("SansSerif", Font.BOLD, 16));

				// Draw ranges
				int y = 100;
				for (int[] range : rangeList) {
					int start = range[0];
					int end = range[1];

					// Draw range
					int x = 50;
					int width = end - start + 1;
					int height = 50;
					g.setColor(y == 100 ? Color.RED : Color.BLUE);
					g.fillRect(x, y, width, height);
					g.setColor(Color.BLACK);
					g.drawString(start + "-" + end, x, y - 10);

					// Draw axis
					g.setColor(Color.GRAY);
					g.drawLine(x, y - 25, x + width, y - 25);
					g.drawString("0", x, y - 40);
					g.drawString(Integer.toString(width), x + width - 10, y - 40);

					y += 100;
				}
			}
		};
		frame.add(panel);

		// Add ranges to list
		panel.repaint();

		// Show window
		frame.setVisible(true);
	}

	// Function to split a range string into start and end values
	public static int[] splitRange(String range) {
		String[] values = range.split("-");
		int[] result = new int[2];
		result[0] = Integer.parseInt(values[0]);
		result[1] = Integer.parseInt(values[1]);
		return result;
	}

	public static void gptcode(String filepath) {
		// Counter for number of pairs that overlap
		int counter = 0;

		// List to store ranges
		List<int[]> ranges = new ArrayList<>();

		// Read input file
		try (Scanner scanner = new Scanner(new File(filepath))) {
			// Iterate over lines in input file
			while (scanner.hasNextLine()) {
				// Split line into two ranges
				String   line   = scanner.nextLine();
				String[] lines  = line.split(",");

				// Split each range into start and end values
				int[] range1 = splitRange(lines[0]);
				int[] range2 = splitRange(lines[1]);

				// Check if ranges overlap
				boolean overlap = IntStream.rangeClosed(Math.max(range1[0], range2[0]), Math.min(range1[1], range2[1]))
				                           .anyMatch(i -> true);
				if (overlap) {
					counter++;
				}

				// Add ranges to list
				ranges.add(range1);
				ranges.add(range2);
			}

			// Visualize ranges
			visualize(ranges);
		} catch (FileNotFoundException e) {
			System.out.println("Error: input file not found");
		}
	}
}
