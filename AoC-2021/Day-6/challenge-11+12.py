"""
    AOC Day 6
    https://adventofcode.com/2021/day/6
"""
import os

# every index represents age from 0 to 8
# value at each index represents the amount of fish with that age
population = [0] * 9

print("--- Day 6: Lanternfish ---")
inputFilePath = f"{os.getcwd()}\\Aoc-2021\\Day-6\\input.txt"
with open(inputFilePath) as f:
    initialAges = f.read()
    for age in range(len(population)):
        population[age] = initialAges.count(str(age))
print(f"Initial population: {population}")


def newDay(population, days):
    days -= 1
    newPopulation = [0] * 9
    for age in range(len(population)):
        # print(age)
        if age != 0:
            newPopulation[age - 1] += population[age]
        elif age == 0:
            newPopulation[6] += population[age]
            newPopulation[8] += population[age]
        else:
            print(f"Error, age {age} is not valid")
        # print(f"Population: {population}, newPopulation: {newPopulation}")
    if days == 0:
        return newPopulation
    return newDay(newPopulation, days)


population80days = newDay(population, 80)
print(f"After 80 days: {population80days}")
print(f"Sum of population: {sum(population80days)}")
population256days = newDay(population, 256)
print(f"After 256 days: {population256days}")
print(f"Sum of population: {sum(population256days)}")
