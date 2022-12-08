package no.h598062.aoc2022.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreetopTreeHouse {

	public static void main(String[] args) {
		// String filepath = "./resources/input-day8-sample.txt";
		String filepath = "./resources/input-day8.txt";

		List<List<Tree>> forrest = new ArrayList<>();
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			int    y = 0;
			while ((currentLine = file.readLine()) != null) {
				forrest.add(new ArrayList<>());
				char[] c = currentLine.toCharArray();
				for (int x = 0; x < c.length; x++) {
					forrest.get(y)
					       .add(new Tree(x, y, Character.getNumericValue(c[x])));
				}
				y++;
			}
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
		int visibleTrees = 0;
		int maxScore     = 0;
		for (List<Tree> trees : forrest) {
			for (Tree tree : trees) {
				tree.checkVisible(forrest);
				tree.calculateScore(forrest);
				if (tree.isVisible()) {
					visibleTrees++;
				}
				if (tree.getScore() > maxScore) {
					maxScore = tree.getScore();
				}
			}
			//System.out.println(trees);
		}
		System.out.println("Visible trees: " + visibleTrees);
		System.out.println("Max scenic score: " + maxScore);
	}
}

class Tree {
	private final int x;
	private final int y;

	private final int height;

	private boolean visible;
	private int     score;

	public Tree(int x, int y, int height) {
		this.x       = x;
		this.y       = y;
		this.height  = height;
		this.visible = false;
		this.score   = 0;
	}

	public void calculateScore(List<List<Tree>> forrest) {
		this.score = countUp(forrest) * countDown(forrest) * countLeft(forrest) * countRight(forrest);
	}

	private int countRight(List<List<Tree>> forrest) {
		if (this.x == forrest.get(y)
		                     .size() - 1) {
			return 0;
		}
		for (int i = this.x + 1; i < forrest.size(); i++) {
			Tree t = forrest.get(this.y)
			                .get(i);
			if (t.getHeight() >= this.height) {
				return t.getX() - this.x;
			}
		}
		return forrest.get(y)
		              .size() - 1 - this.x;
	}

	private int countLeft(List<List<Tree>> forrest) {
		if (this.x == 0) {
			return 0;
		}
		for (int i = this.x - 1; i >= 0; i--) {
			Tree t = forrest.get(this.y)
			                .get(i);
			if (t.getHeight() >= this.height) {
				return this.x - t.getX();
			}
		}
		return this.x;
	}

	private int countDown(List<List<Tree>> forrest) {
		if (this.y == forrest.size() - 1) {
			return 0;
		}
		for (int i = this.y + 1; i < forrest.size(); i++) {
			Tree t = forrest.get(i)
			                .get(this.x);
			if (t.getHeight() >= this.height) {
				return t.getY() - this.y;
			}
		}
		return forrest.size() - 1 - this.y;
	}

	private int countUp(List<List<Tree>> forrest) {
		if (this.y == 0) {
			return 0;
		}
		for (int i = this.y - 1; i >= 0; i--) {
			Tree t = forrest.get(i)
			                .get(this.x);
			if (t.getHeight() >= this.height) {
				return this.y - t.getY();
			}
		}
		return this.y;
	}

	public void checkVisible(List<List<Tree>> forrest) {
		this.visible = checkVisibleUp(forrest) || checkVisibleDown(forrest) || checkVisibleLeft(forrest) ||
		               checkVisibleRight(forrest);
	}

	private boolean checkVisibleRight(List<List<Tree>> forrest) {
		if (this.x == forrest.get(y)
		                     .size() - 1) {
			return true;
		}
		for (int i = this.x + 1; i < forrest.size(); i++) {
			Tree t = forrest.get(this.y)
			                .get(i);
			if (t.getHeight() >= this.height) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVisibleLeft(List<List<Tree>> forrest) {
		if (this.x == 0) {
			return true;
		}
		for (int i = this.x - 1; i >= 0; i--) {
			Tree t = forrest.get(this.y)
			                .get(i);
			if (t.getHeight() >= this.height) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVisibleDown(List<List<Tree>> forrest) {
		if (this.y == forrest.size() - 1) {
			return true;
		}
		for (int i = this.y + 1; i < forrest.size(); i++) {
			Tree t = forrest.get(i)
			                .get(this.x);
			if (t.getHeight() >= this.height) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVisibleUp(List<List<Tree>> forrest) {
		if (this.y == 0) {
			return true;
		}
		for (int i = this.y - 1; i >= 0; i--) {
			Tree t = forrest.get(i)
			                .get(this.x);
			if (t.getHeight() >= this.height) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tree tree = (Tree) o;
		return x == tree.x && y == tree.y && height == tree.height;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, height);
	}

	@Override
	public String toString() {
		// return "Tree{" + "x=" + x + ", y=" + y + ", height=" + height + '}';
		return "{(" + x + "," + y + ") " + height + "  " + (visible ? "x" : "o") + " " + score + "}";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getScore() {
		return score;
	}
}
