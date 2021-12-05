"""
    AOC Day 3
    https://adventofcode.com/2021/day/3
"""
import os

bits = 12
# powerConsumption = gammaRate * epsilonRate
powerConsumption = 0
# gammaRate = most common bit of each 12 bit posistions concatanated
gammaRateBase2 = ""
# epsilonRate = least common bit of each 12 bit posistions concatanated
epsilonRateBase2 = ""


diagnosticsList = []

print("--- Day 3: Binary Diagnostic ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-3\\input.txt"
with open(inputFilePath) as f:
    lines = f.readlines()
    for line in lines:
        diagnosticsList.append(line.strip())


def findCommonBit(bitIndex, list):
    bitList = []
    for line in list:
        bitList.append(line[bitIndex])
    count0 = bitList.count("0")
    count1 = bitList.count("1")
    if count0 > count1:
        return "0"
    elif count1 > count0:
        return "1"
    else:
        print("Error: No common bit found, there are equal amounts of 0 and 1")


for i in range(bits):
    commonBit = findCommonBit(i, diagnosticsList)
    print(f"Common bit at position {i}: {commonBit}")
    if commonBit == "0":
        gammaRateBase2 += "0"
        epsilonRateBase2 += "1"
    elif commonBit == "1":
        gammaRateBase2 += "1"
        epsilonRateBase2 += "0"
print(f"Gamma Rate base2: {gammaRateBase2}, Epsilon Rate base2: {epsilonRateBase2}")
epsilonRate = int(epsilonRateBase2, 2)
gammaRate = int(gammaRateBase2, 2)
print(f"Gamma Rate: {gammaRate}, Epsilon Rate: {epsilonRate}")
powerConsumption = epsilonRate * gammaRate
print(f"Power consumption: {powerConsumption}")
