package Maze;

import java.util.*;
import java.util.stream.Collectors;


/** TODO
 ** Implement DFS algorithm to solve the maze.
 */
public class DFSMaze {

    private static void printCounterValues(Counter counter) {
        System.out.println("Total Nodes Traversed: " + counter.totalNodesTraversed);
        //System.out.println("Nodes in Stack: " + counter.stackedNodes);
        System.out.println("Max Stack Size: " + counter.maxStackSize);
    }

    /** TODO
     * Returns the distance of the path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the path within the maze, null if not solvable
     */
    public static Integer findPath(ArrayList<ArrayList<Tile>> maze) {
        Counter counter = new Counter();
        // Check if input is valid
        //printMaze(maze);
        if (maze.size() == 0)
            return null;

        // Find first exit or if there are any:
        Tile spot = maze.get(0).get(0);
        int i = 0;
        while (spot != Tile.Exit && i < maze.size() - 1) {
            spot = maze.get(++i).get(maze.size() - 1);
        }

        // Create a matrix to see what we have visited already
        boolean[][] visited = new boolean[maze.size()][maze.get(0).size()];

        // Create an empty stack
        Stack<Node> stack = new Stack<>();

        // The first exit needs to be marked
        visited[i][maze.size() - 1] = true;
        stack.push(new Node(i, maze.size() - 1, 0));

        // Loop until the stack is empty, and we found our result
        while (!stack.isEmpty()) {
            counter.stackedNodes = stack.size(); // Update the number of nodes in the stack
            System.out.println("Nodes in Stack current iteration: " + counter.stackedNodes);
            counter.maxStackSize = Math.max(counter.maxStackSize, counter.stackedNodes); // Update max stack size
            counter.totalNodesTraversed++; // Increment the number of traversed nodes

            Node node = stack.pop();
            i = node.i;
            int j = node.j;
            int dist = node.dist;

            for (int k = -1; k <= 1; k++) {
                if (i + k >= 0 && i + k < maze.size() && maze.get(i + k).get(j) != Tile.Wall && !visited[i + k][j]) {
                    visited[i + k][j] = true;
                    stack.push(new Node(i + k, j, dist + 1));

                    if (maze.get(i + k).get(j) == Tile.Exit) {
                        printCounterValues(counter);
                        return dist + 1;
                    }
                }

                if (j + k >= 0 && j + k < maze.get(0).size() && maze.get(i).get(j + k) != Tile.Wall && !visited[i][j + k]) {
                    visited[i][j + k] = true;
                    stack.push(new Node(i, j + k, dist + 1));

                    if (maze.get(i).get(j + k) == Tile.Exit) {
                        printCounterValues(counter);
                        return dist + 1;
                    }
                }
            }
        }

        printCounterValues(counter);
        return null;
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }

    static class Node {
        int i, j, dist;

        Node(int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }
}

