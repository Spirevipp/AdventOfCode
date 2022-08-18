"""
    AOC Day 7
    https://adventofcode.com/2021/day/7
"""
import os

list = []

print("--- Day 7: The Treachery of Whales ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-7\\input.txt"
with open(inputFilePath) as f:
    lines = f.readlines()
    for line in lines:
        list.append(line.strip())
