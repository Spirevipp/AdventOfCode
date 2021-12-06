"""
    AOC Day 5
    https://adventofcode.com/2021/day/5
"""
import os

ventLines = []
horizontalLines = []
verticalLines = []
intersectingLines = []
maxX = 0
maxY = 0

print("--- Day 5: Hydrothermal Venture ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-5\\input.txt"
with open(inputFilePath) as f:
    lines = f.readlines()

    for line in lines:
        positions = line.strip().split("->")
        ventLines.append([])
        for position in positions:
            pos = position.strip().split(",")
            ventLines[len(ventLines) - 1].append(pos)
# print(f"all lines: {ventLines}")

for line in ventLines:
    if line[0][0] == line[1][0]:
        horizontalLines.append(line)
        if int(line[0][0]) > maxX:
            maxX = int(line[0][0])
        if int(line[0][1]) > maxY:
            maxY = int(line[0][1])
        if int(line[1][0]) > maxX:
            maxX = int(line[1][0])
        if int(line[1][1]) > maxY:
            maxY = int(line[1][1])
    elif line[0][1] == line[1][1]:
        verticalLines.append(line)
        if int(line[0][0]) > maxX:
            maxX = int(line[0][0])
        if int(line[0][1]) > maxY:
            maxY = int(line[0][1])
        if int(line[1][0]) > maxX:
            maxX = int(line[1][0])
        if int(line[1][1]) > maxY:
            maxY = int(line[1][1])
print(f"horizontal lines: \n{horizontalLines}")
print(f"vertical lines: \n{verticalLines}")
print(f"maxX: {maxX} - maxY: {maxY}")

areaDiagram = [[0 for x in range(maxX + 1)] for y in range(maxY + 1)]


def printAreaDiagram(areaDiagram):
    for row in areaDiagram:
        print(row)
    print("---" + "---" * maxX)


printAreaDiagram(areaDiagram)

for line in horizontalLines:
    x = int(line[0][0])
    yval = [int(line[0][1]), int(line[1][1])]
    yval.sort()
    print(f"x: {x} - yrange: {yval}")
    for y in range(yval[0], yval[1] + 1):
        areaDiagram[y][x] += 1
for line in verticalLines:
    y = int(line[0][1])
    xval = [int(line[0][0]), int(line[1][0])]
    xval.sort()
    print(f"y: {y} - xrange: {xval}")
    for x in range(xval[0], xval[1] + 1):
        areaDiagram[y][x] += 1

printAreaDiagram(areaDiagram)

morethan1 = 0
for row in areaDiagram:
    for val in row:
        if val > 1:
            morethan1 += 1
print(f"more than 1: {morethan1}")
