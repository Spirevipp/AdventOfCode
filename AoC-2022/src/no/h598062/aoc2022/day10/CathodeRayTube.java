package no.h598062.aoc2022.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CathodeRayTube {

	static int drawpos      = 1;
	static int cycleCounter = 0;
	static int sum          = 0;

	public static void main(String[] args) {
		String filepath = "./resources/input-day10.txt";
		// String filepath = "./resources/input-day10-sample.txt";

		ArrayList<ArrayList<String>> ops = new ArrayList<>();
		ArrayList<Character>         crt = new ArrayList<>();
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			while ((currentLine = file.readLine()) != null) {
				if (currentLine.equals("noop")) {
					ops.add(new ArrayList<>());
					ops.get(ops.size() - 1).add(currentLine);
				} else {
					ops.add(new ArrayList<>());
					String[] s = currentLine.split(" ");
					ops.get(ops.size() - 1).add(s[0]);
					ops.get(ops.size() - 1).add(s[1]);
				}
			}
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}

		for (ArrayList<String> op : ops) {
			if (op.get(0).equals("noop")) {
				if (drawpos == linepos() || drawpos == linepos() + 1 || drawpos == linepos() - 1) {
					crt.add('#');
				} else {
					crt.add('.');
				}
				cycle();
			} else {
				if (drawpos == linepos() || drawpos == linepos() + 1 || drawpos == linepos() - 1) {
					crt.add('#');
				} else {
					crt.add('.');
				}
				cycle();
				if (drawpos == linepos() || drawpos == linepos() + 1 || drawpos == linepos() - 1) {
					crt.add('#');
				} else {
					crt.add('.');
				}
				cycle();

				drawpos += Integer.parseInt(op.get(1));

			}
		}
		System.out.println("Sum is " + sum);

		int line = 1;
		for (Character c : crt) {
			System.out.print(c);
			if (line % 40 == 0 && line != 0) {
				System.out.println();
			}
			line++;
		}
	}

	public static void cycle() {
		cycleCounter++;
		if (cycleCounter == 20 || cycleCounter == 60 || cycleCounter == 100 || cycleCounter == 140 ||
		    cycleCounter == 180 || cycleCounter == 220) {
			int strenght = cycleCounter * drawpos;
			System.out.println("Signal strength for cycle " + cycleCounter + " is: " + strenght);
			sum += strenght;
		}
	}

	public static int linepos() {
		if (cycleCounter < 40) {
			return cycleCounter;
		} else if (cycleCounter < 80) {
			return cycleCounter - 40;
		} else if (cycleCounter < 120) {
			return cycleCounter - 80;
		} else if (cycleCounter < 160) {
			return cycleCounter - 120;
		} else if (cycleCounter < 200) {
			return cycleCounter - 160;
		} else {
			return cycleCounter - 200;
		}
	}
}
