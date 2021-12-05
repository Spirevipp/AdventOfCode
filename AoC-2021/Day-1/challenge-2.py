"""
    AOC Day 1
    https://adventofcode.com/2021/day/1
"""
import os

print("--- Day 1: Sonar Sweep ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-1\\input.txt"
inputFile = open(inputFilePath, "rt", encoding="utf-8")

# parse inputFile as a list of ints
inputListTmp = [int(x) for x in inputFile.read().splitlines()]

# combine 3 subsequent ints to form a new int and add to inputList
inputList = []
for i in range(0, len(inputListTmp)):
    if i == len(inputListTmp) - 2:
        break
    inputList.append(inputListTmp[i] + inputListTmp[i + 1] + inputListTmp[i + 2])
    i += 1

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
