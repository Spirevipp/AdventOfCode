package no.h598062.aoc2022.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SupplyStacks {
	static List<Stack<Character>> stacks = new ArrayList<>(9);
	public static void main(String[] args) {
		String       filepath       = "./resources/input-day5.txt";
		List<String[]> moveOperations = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			stacks.add(new Stack<>());
		}
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			String[] initialStackRows = new String[9]; // set up an array to hold initial stacks for setup
			int lineCounter = 0; // Line 1-8 contains initial stacks
			while ((currentLine = file.readLine()) != null) {
				if (lineCounter < 9) {
					initialStackRows[8 - lineCounter] = currentLine;
					lineCounter++;
				} else if (lineCounter == 9) { // line 9 and 10 has no usefull info
					lineCounter++;
				} else {
					moveOperations.add(currentLine.split("\n*\\s*\\D+\\s*\n*"));
				}
			}
			int charPos = 1;
			for (Stack<Character> stack : stacks) {
				for (String s : initialStackRows) {
					char c = s.charAt(charPos);
					if (c != ' ') {
						stack.push(c);
					}
				}
				charPos+=4;
			}
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}

		for (String[] moveOp : moveOperations) {
			System.out.println(Arrays.toString(moveOp));
			//move9000(moveOp);
			move9001(moveOp);
		}
		StringBuilder result = new StringBuilder();
		for (Stack<Character> stack : stacks) {
			result.append(stack.pop());
		}
		System.out.println("The resulting top level crates are: " + result);
	}

	private static void move9000(String[] moveOp) {
		int amount = Integer.parseInt(moveOp[1]);
		int source = Integer.parseInt(moveOp[2]) -1; // operations use numbers 1-9, we use 0-8
		int destination = Integer.parseInt(moveOp[3]) -1; // operations use numbers 1-9, we use 0-8

		for (int i = 0; i < amount; i++) {
			stacks.get(destination)
			      .push(stacks.get(source)
			                  .pop());
		}
	}
	private static void move9001(String[] moveOp) {
		int amount = Integer.parseInt(moveOp[1]);
		int source = Integer.parseInt(moveOp[2]) -1; // operations use numbers 1-9, we use 0-8
		int destination = Integer.parseInt(moveOp[3]) -1; // operations use numbers 1-9, we use 0-8

		Stack<Character> tmpStack = new Stack<>();
		for (int i = 0; i < amount; i++) {
			tmpStack.push(stacks.get(source).pop());
		}
		for (int i = 0; i < amount; i++) {
			stacks.get(destination)
			      .push(tmpStack.pop());
		}
	}
}
