package no.h598062.aoc2022.day9;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// inspirasjon til å oppdatere move kode
// https://github.com/sanishchirayath1/advent-of-code/blob/master/2022/day9/readable.js

public class RopeBridge {
	public static void main(String[] args) {
		// String filepath = "./resources/input-day9.txt";
		// String filepath = "./resources/input-day9-sample.txt";
		String filepath = "./resources/input-day9-sample2.txt";

		ArrayList<Move>   moves   = new ArrayList<>();
		HashSet<Position> visited = new HashSet<>();
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			while ((currentLine = file.readLine()) != null) {
				moves.add(new Move(currentLine.charAt(0), Integer.parseInt(currentLine.split(" ")[1])));
			}
		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
		// System.out.println(moves);
		Rope rope = new Rope(0, 0, 10);
		visited.add(rope.head);
		for (Move move : moves) {
			rope.move(move, visited);
		}
		rope.printBoard(visited);
		System.out.println(visited);
		System.out.println("Visited " + visited.size() + " nodes");
	}
}

enum direction {
	UP, DOWN, LEFT, RIGHT, NONE
}

class Move {
	private final int       steps;
	private final direction dir;

	public Move(char direction, int steps) {
		this.steps = steps;
		switch (direction) {
			case 'U' -> this.dir = no.h598062.aoc2022.day9.direction.UP;
			case 'D' -> this.dir = no.h598062.aoc2022.day9.direction.DOWN;
			case 'L' -> this.dir = no.h598062.aoc2022.day9.direction.LEFT;
			case 'R' -> this.dir = no.h598062.aoc2022.day9.direction.RIGHT;
			default -> this.dir = no.h598062.aoc2022.day9.direction.NONE;
		}

	}

	@Override
	public String toString() {
		return "Move{" + "steps=" + steps + ", dir=" + dir + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Move move = (Move) o;
		return steps == move.steps && dir == move.dir;
	}

	@Override
	public int hashCode() {
		return Objects.hash(steps, dir);
	}

	public int getSteps() {
		return steps;
	}

	public no.h598062.aoc2022.day9.direction getDir() {
		return dir;
	}
}

class Rope {
	Position             head;
	LinkedList<Position> tail;

	int maxX;
	int maxY;
	int minX;
	int minY;

	public Rope(int x, int y, int lenght) {
		this.head = new Position(x, y);
		this.tail = new LinkedList<>();
		if (lenght < 3) {
			this.tail.add(new Position(x, y));
		} else {
			for (int i = 0; i < lenght - 1; i++) {
				this.tail.add(new Position(x, y));
			}
		}
		this.maxX = 0;
		this.maxY = 0;
		this.minX = 0;
		this.minY = 0;
	}

	public void move(Move move, Set<Position> visited) {
		System.out.println(move);
		for (int i = 0; i < move.getSteps(); i++) {
			Position previousHead = this.head;
			switch (move.getDir()) {
				case UP -> this.head = new Position(this.head.x(), this.head.y() + 1);
				case DOWN -> this.head = new Position(this.head.x(), this.head.y() - 1);
				case LEFT -> this.head = new Position(this.head.x() - 1, this.head.y());
				case RIGHT -> this.head = new Position(this.head.x() + 1, this.head.y());
				case NONE -> System.out.println("Error, got posistion NONE");
			}
			Position prevTail = previousHead;
			for (int j = 0; j < tail.size(); j++) {
				Position t = tail.get(j);
				if (!isTailAdjacent(j)) {
					if (j == 0) {
						this.tail.set(j, new Position(t.x()+t.deltaX(previousHead), t.y()+t.deltaY(previousHead)));
					} else {
						if (j == tail.size() - 1) {
							visited.add(t);
							visited.add(prevTail);
						}
						this.tail.set(j, new Position(t.x()+t.deltaX(prevTail), t.y()+t.deltaY(prevTail)));
					}
					prevTail = t;
					// System.out.println(move + " step " + (i+ 1));
				} else { // if this tail segment did not need to move, then the rest does not need to move
					break;
				}
			}
			if (this.head.y() > this.maxY) {
				this.maxY = this.head.y();
			}
			if (this.head.x() > this.maxX) {
				this.maxX = this.head.x();
			}
			if (this.head.y() < this.minY) {
				this.minY = this.head.y();
			}
			if (this.head.x() < this.minX) {
				this.minX = this.head.x();
			}
			this.printBoard(visited);
		}
	}

	public boolean isTailAdjacent(int pos) {
		boolean isAdjacent;
		if (pos == 0) {
			isAdjacent = (this.tail.get(pos).equals(this.head)) ||
			             (this.tail.get(pos).x() + 1 == this.head.x() && this.tail.get(pos).y() == this.head.y()) ||
			             (this.tail.get(pos).x() - 1 == this.head.x() && this.tail.get(pos).y() == this.head.y()) ||
			             (this.tail.get(pos).x() == this.head.x() && this.tail.get(pos).y() + 1 == this.head.y()) ||
			             (this.tail.get(pos).x() == this.head.x() && this.tail.get(pos).y() - 1 == this.head.y()) ||
			             (this.tail.get(pos).x() + 1 == this.head.x() && this.tail.get(pos).y() + 1 == this.head.y()) ||
			             (this.tail.get(pos).x() + 1 == this.head.x() && this.tail.get(pos).y() - 1 == this.head.y()) ||
			             (this.tail.get(pos).x() - 1 == this.head.x() && this.tail.get(pos).y() + 1 == this.head.y()) ||
			             (this.tail.get(pos).x() - 1 == this.head.x() && this.tail.get(pos).y() - 1 == this.head.y());
		} else {
			isAdjacent = (this.tail.get(pos).equals(this.tail.get(pos - 1))) ||
			             (this.tail.get(pos).x() + 1 == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() - 1 == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() + 1 == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() - 1 == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() + 1 == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() + 1 == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() + 1 == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() - 1 == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() - 1 == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() + 1 == this.tail.get(pos - 1).y()) ||
			             (this.tail.get(pos).x() - 1 == this.tail.get(pos - 1).x() &&
			              this.tail.get(pos).y() - 1 == this.tail.get(pos - 1).y());
		}
		return isAdjacent;
	}

	public void printBoard(Set<Position> visited) {
		System.out.println("======================================");
		List<String> board = new ArrayList<>();

		int x  = this.maxX + 1;
		int y  = this.maxY + 1;
		int mx = Math.abs(this.minX);
		int my = Math.abs(this.minY);
		for (int i = 0; i < y + my; i++) {
			String s = ".".repeat(x + mx);
			board.add(s);
		}
		for (Position p : visited) {
			StringBuilder sb1 = new StringBuilder(board.get(p.y() + my));
			if (p.equals(new Position(0, 0))) {
				sb1.setCharAt(p.x() + mx, 'S');
			} else {
				sb1.setCharAt(p.x() + mx, '#');
			}
			board.set(p.y() + my, sb1.toString());
		}

		StringBuilder sh = new StringBuilder(board.get(this.head.y() + my));
		sh.setCharAt(this.head.x() + mx, 'H');
		board.set(this.head.y() + my, sh.toString());

		for (int i = 0; i < tail.size(); i++) {
			Position      t  = tail.get(i);
			StringBuilder st = new StringBuilder(board.get(t.y() + my));
			st.setCharAt(t.x() + mx, ((char) (i + '0')));
			board.set(t.y() + my, st.toString());
		}

		for (int i = board.size() - 1; i >= 0; i--) {
			System.out.println(board.get(i));
		}
		System.out.println("======================================");
	}
}

record Position(int x, int y) {
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Position position = (Position) o;
		return x == position.x && y == position.y;
	}

	@Override
	public String toString() {
		return "{" + "x=" + x + ", y=" + y + '}';
	}

	public int deltaX(Position p) {
		return p.x - x;
	}

	public int deltaY(Position p) {
		return p.y - y;
	}
}
