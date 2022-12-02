@echo off
set day=%1
set /p token=<sessiontoken.txt
curl --cookie "session=%token%" "https://adventofcode.com/2022/day/%day%/input" -o "./resources/input-day%day%.txt"
mkdir "src/no/h598062/aoc2022/day%day%"