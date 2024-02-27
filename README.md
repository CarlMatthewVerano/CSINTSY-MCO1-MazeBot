# CSINTSY-MCO1-MazeBot


## Maze

The project involves an \(n \times n\) maze, where \(n\) is any number from 3 up to a maximum of 64. A maze configuration is defined in a text file `maze.txt` with a specific format that includes a wall symbol `#`, an empty space symbol `.`, a starting location `S`, and a target location `G`. The maze will always contain exactly one `S` and one `G`.

Example maze definition:

Maze
The setup is an 𝑛 × 𝑛 maze, where 𝑛 is any number from 3 up to a maximum of 64. A maze
configuration can be defined as a text file called maze.txt in the following format:

n\
XXX\
XXX\
XXX

The first line of the text file contains a single integer 𝑛, the size of the maze. This is a followed by 𝑛
lines containing 𝑛 characters each (represented as Xs in the definition above). Each X may be one of
the following:

Symbol Description
'#' - wall
. (period) - an empty space
S - starting location of the bot
G - target location of the bot

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

Students are tasked with implementing a bot that can navigate the maze from the starting point `S` to the target location `G`. The bot can move one step up, down, left, or right but can only step on empty spaces. The program should illustrate the search process, highlight the chosen path, show all explored locations, and display the total number of states explored before finding the solution.

## Expected Program Behavior

The program should:
- Load and display the maze defined in `maze.txt`.
- Allow the user to initiate the search for the bot to find a path to the goal.
- Display the final path taken by the bot, all locations explored, and the total number of states explored.
- Indicate if no path to the target location is found without crashing.
