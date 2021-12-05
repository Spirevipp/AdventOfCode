"""
    AOC Day 2
    https://adventofcode.com/2021/day/2
"""
import os

print("Movement calculator is now running")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-2\\input.txt"
# inputFile = open(inputFilePath, "rt", encoding="utf-8")

# positions
horizontal = 0
depth = 0
aim = 0


def move(direction, distance):
    global horizontal
    global depth
    global aim
    if direction == "forward":
        horizontal += distance
        depth = depth + aim * distance
        print(f"New position: {horizontal}, {depth}")
    elif direction == "up":
        aim -= distance
        print(f"Decreasing aim to: {aim}")
    elif direction == "down":
        aim += distance
        print(f"Increasing aim to: {aim}")
    else:
        print(f"Invalid direction {direction}")


with open(inputFilePath, "rt", encoding="utf-8") as f:
    for line in f:
        line = line.split(" ")
        direction = line[0]
        distance = int(line[1])
        print(f"Input is command: {direction} value: {distance}")
        move(direction, distance)
    print(f"Final position is horizontal: {horizontal} depth: {depth}")
    # Challenge asks for horizontal and depth multiplied
    print(f"Output of position multiplied is {horizontal * depth}")
