import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/*** Maze would refer to the bot that would find
 *      a path from a given starting space to a
 *      given goal space in a maze that is read
 *      from a text file.
 */
public class maze {
    /*** explore() traverses through the maze in order
     * to find the goal state beginning from the
     * starting point.
     *
     * @param tile      refers to the 2D array that represents
     *                  the maze
     * @param state     refers to the node to be evaluated
     * @param n         refers to the maze size
     * @param qMoves    refers to the queue of nodes to be explored
     * @param explored  refers to an arraylist of the explored nodes
     */
    public static void explore(State[][] tile, State state, int n, Queue<State> qMoves, ArrayList<State> explored){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        State temp;
        int x = state.pCoordinate.x;
        int y = state.pCoordinate.y;

        if(isGoal(state)){
            explored.add(state);
            displayGoal(tile, state, explored);
            return;
        }

        // North Check
        if(isInBound(x-1, y, n)){
            if(isValidCoordinate(tile[x-1][y])){
                tile[x-1][y].explored = true;
                tile[x-1][y].prevCoordinate = tile[x][y].pCoordinate;
                qMoves.add(tile[x-1][y]);
            }
        }

        // South Check
        if(isInBound(x+1, y, n)){
            if(isValidCoordinate(tile[x+1][y])){
                tile[x+1][y].explored = true;
                tile[x+1][y].prevCoordinate = tile[x][y].pCoordinate;
                qMoves.add(tile[x+1][y]);
            }
        }

        // East Check
        if(isInBound(x, y+1, n)){
            if(isValidCoordinate(tile[x][y+1])){
                tile[x][y+1].explored = true;
                tile[x][y+1].prevCoordinate = tile[x][y].pCoordinate;
                qMoves.add(tile[x][y+1]);
            }
        }

        // West Check
        if(isInBound(x, y-1, n)){
            if(isValidCoordinate(tile[x][y-1])){
                tile[x][y-1].explored = true;
                tile[x][y-1].prevCoordinate = tile[x][y].pCoordinate;
                qMoves.add(tile[x][y-1]);
            }
        }

        System.out.println("\nExplored this iteration: " + "[" + x + "," + y + "]");
        explored.add(state);
        displayExploredStates(explored);

        if (qMoves.peek() != null) {
            for (State qMove : qMoves) {
                System.out.println("Frontier: " + "[" + qMove.pCoordinate.x + "," + qMove.pCoordinate.y + "]");
            }
        } else {
            System.out.println("\nCould not find Goal after " + State.stateNum + " state/s");
        }
        System.out.println("\n---SPACING---\n");

        try{
            temp = qMoves.remove();
            if(temp.status != 'G'){
                tile[temp.pCoordinate.x][temp.pCoordinate.y].status = 'O';
            }
        }catch (Exception e){
            return;
        }

        State.stateNum++;
        displayMaze(tile, n);

        explore(tile, temp, n, qMoves, explored);
    }

    public static void main(String[] args) throws IOException {

        RandomAccessFile br = new RandomAccessFile("maze.txt", "r");
        int mazeSize = Integer.parseInt(br.readLine());
        String temp;
        char[][] maze = new char[mazeSize][mazeSize];
        ArrayList<State> explored = new ArrayList<>();
        for(int i = 0; i < mazeSize; i++){
            temp = br.readLine();
            maze[i] = temp.toCharArray();
        }
        br.close();

        Queue<State> qMoves = new LinkedList<>();
        State[][] tile;
        tile = generateStates(mazeSize, maze);
        State startState = new State();

        System.out.println("Maze Size: " + mazeSize);
        for(int i = 0; i < mazeSize; i++){
            for(int j = 0; j < mazeSize; j++){
                if(tile[i][j].status == 'S'){
                    System.out.println("START: " + "[" + tile[i][j].pCoordinate.x + "," + tile[i][j].pCoordinate.y + "]");
                    startState = tile[i][j];
                }
            }
        }
        startState.explored = true;

        displayMaze(tile, mazeSize);

        explore(tile, startState, mazeSize, qMoves, explored);
    }

    /*** isInBound() returns a boolean value that symbolizes
     * if the current node is within the maze.
     *
     * @param x   refers to the x coordinate of the node
     * @param y   refers to the y coordinate of the node
     * @param n   refers to the maze size
     * @return    true if the node is within the maze, false
     *            if not.
     */
    public static boolean isInBound(int x, int y, int n){
        return  (x < n && x > -1) && (y < n && y > -1);
    }

    /*** isValidCoordinate() returns a boolean value that
     * symbolizes if the node should be added to the
     * queue of nodes to be explored.
     *
     * @param state refers to the node to be evaluated
     * @return      true if the state is not a wall,
     *              if it has not been explored and if
     *              it is not in queue, false if not.
     */
    public static boolean isValidCoordinate(State state){
        return state.status != '#' && !state.explored;
    }

    /*** isGoal() returns a boolean value that symbolizes
     * if the current node is the goal state.
     *
     * @param state refers to the node to be evaluated
     * @return      true if the node is the goal state,
     *              false if not.
     */
    public static boolean isGoal(State state){
        return state.status == 'G';
    }

    /*** generateStates generates the 2D ARRAY of states
     * and initializes its attributes
     *
     * @param mazeSize  size of the maze
     * @param maze      char[][] array read from maze.txt
     * @return          2D ARRAY OF STATES
     */
    public static State[][] generateStates(int mazeSize, char[][] maze){

        State[][] tile = new State[mazeSize][mazeSize];
        for(int i=0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                tile[i][j] = new State();
                tile[i][j].status = maze[i][j];
                tile[i][j].pCoordinate = new Point(i, j);
            }
        }
        return tile;
    }

    /*** displayGoal displays the last explored space,
     * how many states the bot explored before finding
     * the goal, the explored states and the final
     * path to the goal state.
     *
     * @param tile      2D ARRAY OF STATES
     * @param state     refers to the node to be evaluated
     * @param explored  refers to an arraylist of the explored nodes
     */
    public static void displayGoal(State[][] tile, State state, ArrayList<State> explored){
        State current;
        ArrayList<State> finalPath = new ArrayList<>();
        System.out.println("\n---SPACING---\n");
        System.out.println("Search Finished and has reach the goal!");
        System.out.println("Last Explored: " + "[" + state.pCoordinate.x + "," + state.pCoordinate.y + "]");
        System.out.println("Finished after " + State.stateNum + " state/s");

        System.out.println("\nAll Explored States: ");
        for (State value : explored) {
            System.out.print("[" + value.pCoordinate.x + "," + value.pCoordinate.y + "]");
        }

        System.out.println("\n\nFinal Path: ");
        current = state;
        while (current.status != 'S'){
            finalPath.add(current);
            if (current.prevCoordinate != null) {
                current = tile[current.prevCoordinate.x][current.prevCoordinate.y];
            }
        }
        Collections.reverse(finalPath);
        for (State value : finalPath){
            System.out.print("[" + value.pCoordinate.x + "," + value.pCoordinate.y + "]");
        }
    }

    /*** displayExploredStates displays the explored
     * states
     *
     * @param explored  refers to an arraylist of the explored nodes
     */
    public static void displayExploredStates(ArrayList<State> explored){
        System.out.println("Explored: ");
        for (State value : explored) {
            System.out.print("[" + value.pCoordinate.x + "," + value.pCoordinate.y + "]");
        }
        System.out.println();
    }

    /*** displayMaze displays the maze
     *
     * @param tile  2D array of states
     * @param n     refers to the maze size
     */
    public static void displayMaze(State[][] tile, int n){
        System.out.println("State#: " + State.stateNum);
        System.out.print("Maze: ");
        for(int i = 0; i < n; i++) {
            if(i != 0){
                System.out.print("      ");
            }
            for (int j = 0; j < n; j++) {
                System.out.print(tile[i][j].status + " ");
            }
            System.out.println();
        }
    }

    /*** State refers to the spaces in the maze.
     *      It could be one of the following:
     *      1. Empty Space  - denoted by '.'
     *      2. Wall         - denoted by '#'
     *      3. Start        - denoted by 'S'
     *      4. Goal         - denoted by 'G'
     *          pCoordinate refers to which
     *      position the state is in the maze.
     *          Status refers to how it's
     *      symbolized as shown before by its
     *      denotations.
     *          Explored refers to if the node
     *      has been explored before.
     *          Queued refers to if the node has
     *      already been added to the queue.
     *          prevCoordinate refers to which
     *      node is beside it (or its parent
     *      node).
     */
    static class State{
        Point pCoordinate = new Point();
        //z implies null
        char status = 'z';
        boolean explored = false;
        Point prevCoordinate = null;
        static int stateNum = 0;
    }
}