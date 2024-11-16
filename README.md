# Project: TicTacToe

* Author: Brian Wu
* Class: CS121 Section #01
* Semester: Fall 2021

## Overview

This program is a game of TicTacToe that can be played in the GUI and has several methods to accompany it.

## Reflection

To sum it up as simple as possible, this project was a breeze. To go more in depth on my reflection, I found this project in comparison to the previous one significantly easy in terms of logic and workload. I'm not sure why. I found that arrays just clicked for me and making solutions to each method just popped up in my head. Well, not entirely. When I was developing the getPoints() method I kept running into an ArrayOutOfBoundsIndexError that would always plague my tester, bombarding it with failures. Turns out, all I needed to add was an extra conditional attached the while loop to make sure it doesn't go past the length of the points array.

Another issue that I ran into was how could I create an idea to solve the gameOver() method. I felt that it was the most difficult hill of this entire project and for some time, I had no idea what to do. Until I had an idea that exploded in my head. Why not just translate all the values from the gameBoard into string literals and put them into one. That way, If I get 3 in a row, I can 
use the contains method and BAM! it will see that 3 letters are next to each letter of all the same kind and quickly find a winner.

## Compiling and Using

You can make a driver to use the code, or you can use the GUI provided. Either works as you don't need anything else to make the code run.

To compile:
`javac *.java`

To run:
`java TicTacToeGUI.java`

## Sources used

None

----------

