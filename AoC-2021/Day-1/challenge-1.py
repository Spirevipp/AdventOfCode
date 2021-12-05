"""
    AOC Day 1
    https://adventofcode.com/2021/day/1
"""
import os

print("--- Day 1: Sonar Sweep ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-1\\Challenge-1\\input.txt"
inputFile = open(inputFilePath, "rt", encoding="utf-8")

# parse inputFile as a list of ints
inputList = [int(x) for x in inputFile.read().splitlines()]

compareVal = inputList[0]
index = 1
increased = 0
while index < len(inputList):
    currentNum = int(inputList[index])
    # print(f"comparing {compareVal} to {currentNum}")

    if currentNum == compareVal:
        print(f"{currentNum} is equal to {compareVal}")
        pass
    elif currentNum > compareVal:
        print(f"{currentNum} is bigger than {compareVal}")
        increased += 1
        pass
    elif currentNum < compareVal:
        print(f"{currentNum} is smaller than {compareVal}")
        pass
    compareVal = inputList[index]
    index += 1
print(f"Measurement increased {increased} times")
