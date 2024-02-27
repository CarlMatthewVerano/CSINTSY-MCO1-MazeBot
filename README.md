# CSINTSY-MCO1-MazeBot


## Maze
The setup is an 𝑛 × 𝑛 maze, where 𝑛 is any number from 3 up to a maximum of 64. A maze
configuration can be defined as a text file called maze.txt in the following format:

n\
XXX\
XXX\
XXX

The first line of the text file contains a single integer 𝑛, the size of the maze. This is a followed by 𝑛
lines containing 𝑛 characters each (represented as Xs in the definition above). Each X may be one of
the following:

| Symbol   | Description               |
|----------|---------------------------|
| `#`      | wall                      |
| `.`      | an empty space            |
| `S`      | starting location of the bot |
| `G`      | target location of the bot   |\

You may assume that the maze always contains exactly one S and exactly one G, otherwise it is an
invalid maze.

Here is an example of an actual maze definition:

5\
....G\
.####\
...#S\
.#.#.\
.#...

## The Bot
Given a maze configuration, you are to implement a bot that must find its way to the goal. The bot can only step on a space, and its movements are restricted to go one step up, down, left, or right.

The simulation of the search must be illustrated to show which paths were explored before getting to the goal. The chosen path must be highlighted differently.

## Expected Program Behavior
You are to implement a program with the following behavior:

- Once executed, the program loads and displays the maze defined in `maze.txt`. You’re free to choose the way the look of the maze as long as it is easily interpretable. However, don’t spend too much time and effort making the maze look good at the expense of the more important parts of the project.
- The user can initiate the search. After which, the bot will find a path from the starting point (S) to the target location (G). The program should display (a) the final path taken by the bot, (b) all the locations that were explored by the implemented algorithm before arriving at the solution, and (c) the total number of states explored before the solution is found (or not found).
- The program should also display the order in which the locations were explored by the algorithm. It is up to you how to want to display this. Some options are:
  - Put a number to show the ordering
  - Use an animation showing the step by step exploration of locations by the algorithm (either timed or through a button to advance to the next step).
- If there is no path to the target location, the program should state that a solution cannot be found, and must not crash.

Note the following things:
- You can use any programming language.
- Resulting program can be text-based, web-based or could be a desktop application. But it **must run on a Windows-based platform**. Regardless of what you choose, make sure you comply with all features listed above.
- You may assume that `maze.txt` will always be valid according to the definition above. No need to write checks for this.
- To get full points for this project, your bot should at least be complete (i.e., finds a solution if it exists).

No need to create interfaces for:
- Creating / editing a maze (maze configurations can just be specified through the text file, no need to have an editor)
- Restarting the search after it is completed (we can just restart the program if we want to restart the search).
