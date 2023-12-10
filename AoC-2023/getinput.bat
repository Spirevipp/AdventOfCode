@echo off
set day=%1
set /p token=<sessiontoken.txt
mkdir "src/main/java/no/h598062/aoc2023/day%day%"
mkdir "src/main/resources/day%day%"
echo "" > "src/main/resources/day%day%/sample.txt"
curl --cookie "session=%token%" "https://adventofcode.com/2023/day/%day%/input" -o "./src/main/resources/day%day%/input.txt"
