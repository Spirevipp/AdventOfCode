package no.h598062.aoc2022.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MonkeyintheMiddle {
	public static void main(String[] args) {

		List<Monkey> monkeys = new ArrayList<>();
		monkeys.add(new Monkey(new int[]{73, 77}, 11, 6, 5));
		monkeys.add(new Monkey(new int[]{57, 88, 80}, 19, 6, 0));
		monkeys.add(new Monkey(new int[]{61, 81, 84, 69, 77, 88}, 5, 3, 1));
		monkeys.add(new Monkey(new int[]{78, 89, 71, 60, 81, 81, 87, 75}, 13, 2, 7));
		monkeys.add(new Monkey(new int[]{60, 76, 90, 63, 86, 87, 89}, 17, 4, 7));
		monkeys.add(new Monkey(new int[]{88}, 3, 1, 0));
		monkeys.add(new Monkey(new int[]{84, 98, 78, 85}, 7, 5, 4));
		monkeys.add(new Monkey(new int[]{98, 89, 78, 73, 71}, 2, 3, 2));
	}
}

class Monkey {
	List<Integer> items = new ArrayList<>();

	int   testDivisible;
	int[] testTarget = new int[2];

	public Monkey(int[] items, int testDivisible, int testTrue, int testFalse) {
		IntStream.range(0, items.length).forEach(i -> this.items.add(i));
		this.testDivisible = testDivisible;
		this.testTarget[0] = testTrue;
		this.testTarget[1] = testFalse;
	}
}
