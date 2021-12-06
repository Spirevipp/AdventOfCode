"""
    AOC Day 4
    https://adventofcode.com/2021/day/4
"""
import os
import re

boardList = []
correctList = []
numbers = []
boards = 0

print("--- Day 4: Giant Squid ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-4\\input.txt"
with open(inputFilePath) as f:
    lines = f.readlines()
    i = 0
    for line in lines:
        if len(line.strip()) > 14:
            numbers = line.strip().split(",")
        elif len(line.strip()) == 0:
            continue
        else:
            if i == 5:
                boards += 1
                i = 0
            if i == 0:
                boardList.append([])
                correctList.append([])
            print(f"{boards} - {i}")
            boardList[boards].append(re.split(r"\D{1,2}", line.strip()))
            correctList[boards].append(["o", "o", "o", "o", "o"])
            # print(boardList[boards])
            i += 1
for i in boardList:
    print("-----------------")
    for j in i:
        print(j)


def checkRow(correctBoard, rowNum):
    correctNums = correctBoard[rowNum].count("x")
    if correctNums == 5:
        return True
    else:
        return False


def checkColumn(correctBoard, columnNum):
    row = 0
    correctNums = 0
    while row < 5:
        if correctBoard[row][columnNum] == "x":
            correctNums += 1
        row += 1
    if correctNums == 5:
        return True
    else:
        return False


def checkWinConditions(correctBoard):
    # print("Checking win conditions")
    row = 0
    while row < 5:
        isWin = checkRow(correctBoard, row)
        if isWin:
            return isWin
        row += 1
    col = 0
    while col < 5:
        isWin = checkColumn(correctBoard, col)
        if isWin:
            return isWin
        col += 1
    return False


def getIncorrectNumbers(board, correctBoard):
    incorrectList = []
    for i, row in enumerate(board):
        for j, num in enumerate(row):
            if correctBoard[i][j] == "o":
                incorrectList.append(num)
    return incorrectList


def checkBingoBoards(boardList, correctList, currentNum):
    boardNum = 0
    print(f"Checking {currentNum}")
    while boardNum < len(boardList):
        row = 0
        while row < 5:
            if currentNum in boardList[boardNum][row]:
                j = boardList[boardNum][row].index(currentNum)
                correctList[boardNum][row][j] = "x"
            row += 1
        isWin = checkWinConditions(correctList[boardNum])
        if isWin:
            print(f"Bingo for board {boardNum}")
            incorrectNumbers = getIncorrectNumbers(boardList[boardNum], correctList[boardNum])
            print(f"Incorrect numbers: {incorrectNumbers}")
            print(f"Current number: {currentNum}")
            score = sum(int(num) for num in incorrectNumbers) * int(currentNum)
            return score
        boardNum += 1

    if len(numbers) == 0:
        return "Reached end of bingo and noone won :("
    return checkBingoBoards(boardList, correctList, numbers.pop(0))


print(checkBingoBoards(boardList, correctList, numbers.pop(0)))
