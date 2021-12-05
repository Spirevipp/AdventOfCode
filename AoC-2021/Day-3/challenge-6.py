"""
    AOC Day 3
    https://adventofcode.com/2021/day/3
"""
import os

bits = 12

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
        # return 1 if there are equal amounts of 0 and 1
        return "1"


# returns life support rating, oxygen generator rating * CO2 scrubber rating
# recursive function
def calculateOxygenGeneratorRating(bitPosition, oxyList):
    filteredList = []
    commonBit = findCommonBit(bitPosition, oxyList)
    # print(f"Common bit at position {bitPosition}: {commonBit}")
    for line in oxyList:
        if line[bitPosition] == commonBit:
            filteredList.append(line)
        else:
            pass

    # if we found the last position, return
    if len(filteredList) == 1:
        var = filteredList.pop()
        print(f"Oxygen rating: {var}")
        return var
    return calculateOxygenGeneratorRating(bitPosition + 1, filteredList)


def calculateCO2ScrubberRating(bitPosition, CO2List):
    filteredList = []
    commonBit = findCommonBit(bitPosition, CO2List)
    # print(f"Common bit at position {bitPosition}: {commonBit}")
    for line in CO2List:
        if line[bitPosition] != commonBit:
            filteredList.append(line)
        else:
            pass

    # if we found the last position, return
    if len(filteredList) == 1:
        var = filteredList.pop()
        print(f"CO2 rating: {var}")
        return var
    return calculateCO2ScrubberRating(bitPosition + 1, filteredList)


oxygenGeneratorRatingBase2 = str(calculateOxygenGeneratorRating(0, diagnosticsList))
print(f"Oxygen generator rating base 2: {oxygenGeneratorRatingBase2}")
CO2ScrubberRatingBase2 = str(calculateCO2ScrubberRating(0, diagnosticsList))
print(f"CO2 scrubber rating base 2: {CO2ScrubberRatingBase2}")
oxygenGeneratorRating = int(oxygenGeneratorRatingBase2, 2)
print(f"Oxygen generator rating: {oxygenGeneratorRating}")
CO2ScrubberRating = int(CO2ScrubberRatingBase2, 2)
print(f"CO2 scrubber rating: {CO2ScrubberRating}")
print(f"Total life support rating: {oxygenGeneratorRating * CO2ScrubberRating}")
