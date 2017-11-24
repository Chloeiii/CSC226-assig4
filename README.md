### CSC226-assig4 :ribbon:
----
#### Programming part:
    In this part of the assignment you will get practice in recursive backtrack programming and with some of the Java bitwise operators.
    Your objective is to write a program for solving mazes and for counting the number of solutions to a maze. We supply the code for
    creating a good-looking random maze.

    We will think of the maze as being stored in a two-dimensional array m[][] of integers using an encoding for each cell that is
    explained below. Each cell is bounded by some number of walls, xx, where 0≤x≤40≤x≤4. Which walls are present or not is encoded as a
    byte, using the 4 low order bits as in 0000NESW (North, West, South, East). There is also an artificial border of 16s around the
    entire maze. Take a look at the output further down for other examples of mazes using this encoding. In these examples, the
    artificial border is not shown.
    /*============================================================*/
    //      8
    //    1 * 4    <-- encodings of various directions around a cell
    //      2
    //
    //      +--+--+    +--+--+
    //      |     |    |11 12|    11  12   a maze and its representation
    //      +--+  +    +--+  +
    //      |     |    |11 06|    11  06
    //      +--+--+    +--+--+
    //
    //     16 16 16 16   initial maze contents returned by constructor
    //     16 15 15 16
    //     16 15 15 16
    //     16 16 16 16
    //
    /*============================================================*/
    A template for your program may be found here: MyProgramTemplate.txt. You should understand the code in this template before writing
    any code of your own. Some aspects of the code form part of the written questions below. Note that a random number generator is used
    to produce the maze and knock down walls. A seed is provided to the generator, so that we can reproduce exact mazes over and over as
    we test your programs.
    You should not change any of the code unless there is a comment asking you to. As always, you can add additional methods and
    variables. You should not need any additional classes.

    With the main as provided, java Maze 3 3 1 1 3 3 should produce the output shown below. Make sure you understand how the numbers
    correspond to walls. In the second output one wall has been removed, and there are now two possible solutions. The third output
    shows the solution to the first maze; each cell along the solution path has 16 added to the initial cell number.

    NOTE: Each solution path in the samples below is between the upper left corner and the lower right corner. However, your code should
    be capable of finding solutions between any two distinct cells, given the corresponding row and column numbers.

     9 10 12
     5 13  5
     3  6  7
    Solutions = 1
     9  8 12
     5  5  5
     3  6  7
    Solutions = 2
    25 26 28
     5 13 21
     3  6 23
    And java Maze 12 12 1 1 12 12 should produce the following output.
     9  8 10 10  8 14  9 10 10  8 10 12
     7  5 11 10  6  9  2 14  9  6  9  6
     9  6  9 10 12  3 12  9  6 11  2 14
     1 10  2 14  1 14  5  3 10 12  9 12
     5  9 12  9  6  9  6  9 12  3  6  5
     3  6  7  3 10  2 10  4  3 14  9  4
     9 10 10 10 10 12 13  5  9 10  6  7
     3 10 12  9 12  3  6  5  3 10 10 12
     9 12  5  7  3  8 12  3 10  8 14  5
     5  7  3 12  9  6  3 12 11  6  9  6
     5  9 12  5  3 10 12  3 10 12  3 12
     3  6  3  2 10 10  6 11 10  2 10  6
    Solutions = 1
     9  8 10 10  8 14  9 10 10  8 10 12
     7  5 11 10  6  9  2 14  9  6  9  6
     9  6  9 10 12  3  8  8  6 11  2 14
     1 10  2 14  1 14  5  3 10 12  9 12
     5  9 12  9  6  9  4  9 12  3  6  5
     3  6  7  3 10  2  2  4  3 14  9  4
     9 10 10 10 10 12 13  5  9 10  6  7
     3 10 12  9 12  3  2  4  3 10 10 12
     9 12  5  7  3  8  8  2 10  8 14  5
     5  3  2 12  9  6  3 12 11  6  9  6
     5  9 12  5  3 10 12  3 10 12  3 12
     3  2  2  2 10 10  6 11 10  2 10  6
    Solutions = 12
    25 24 10 10  8 14 25 26 26 24 10 12
     7 21 11 10  6 25 18 14 25 22  9  6
    25 22 25 26 28 19 28 25 22 11  2 14
    17 26 18 14 17 14 21 19 26 28 25 28
     5  9 12 25 22 25 22  9 12 19 22 21
     3  6  7 19 26 18 10  4  3 14 25 20
     9 10 10 10 10 12 13  5 25 26 22  7
     3 10 12  9 12  3  6  5 19 26 26 28
     9 12  5  7  3  8 12  3 10  8 14 21
     5  7  3 12  9  6  3 12 11  6 25 22
     5  9 12  5  3 10 12  3 10 12 19 28
     3  6  3  2 10 10  6 11 10  2 10 22
 ----
#### Written part
    These first problems are related to the code above.

    Explain what Create is doing. It is guaranteed to generate a maze that always has a unique solution. Why?
    If there are nn rows and mm columns, then how many times is Create called when making a maze?
    What is the purpose of the p^2?
----
#### Additional written part
    Below is the rotation system of a plane graph. Draw all possible embeddings in the plane (i.e., each face should be an outer face in
    exactly one of the embeddings).
    1: 5, 7
    2: 5, 3, 6
    3: 6, 2
    4: 7, 5
    5: 1, 2, 4, 7
    6: 2, 3
    7: 1, 5, 4
    Below is the rotation system for a graph embedded on a surface. What surface is it embedded on? Draw a nice diagram of the graph as
    embedded on this surface.
    a: b,d,g
    b: d,c,a
    c: b,e,d
    d: a,c,b
    e: h,c,f
    f: h,g,e
    g: a,h,f
    h: f,e,g
    From the book 4.4.17.
    From the backtracking handout: Question 2 about estimating the size of the tree. Compare with the actual size of the tree (which can
    be obtained by running the program and counting the number of recursive calls).
    From the book: Exercise 6.38.
    Find the max-flow and min-cut in the attached network as it would be found by the Ford-Fulkerson algorithm.
----
#### Examples
    C:\Users\Frank\Documents\Classes\226>java MyMaze 4 6 1 1 4 6
    11  8 10 12 11 12
     9  6 11  2 12  5
     1 10 12 13  5  5
     3 14  3  6  3  6
       +---+---+---+---+---+---+
       |               |       |
       +---+   +---+   +---+   +
       |       |           |   |
       +   +---+---+---+   +   +
       |           |   |   |   |
       +   +---+   +   +   +   +
       |       |       |       |
       +---+---+---+---+---+---+
    Solutions = 1  

    27 24 26 28 11 12
     9  6 11 18 28  5
     1 10 12 13 21  5
     3 14  3  6 19 22
       +---+---+---+---+---+---+
       | *   *   *   * |       |
       +---+   +---+   +---+   +
       |       |     *   * |   |
       +   +---+---+---+   +   +
       |           |   | * |   |
       +   +---+   +   +   +   +
       |       |       | *   * |
       +---+---+---+---+---+---+


    11  8 10 12 11 12
     9  0 10  2 12  5
     1  2 12 13  5  5
     3 14  3  6  3  6
       +---+---+---+---+---+---+
       |               |       |
       +---+   +---+   +---+   +
       |                   |   |
       +   +   +---+---+   +   +
       |           |   |   |   |
       +   +---+   +   +   +   +
       |       |       |       |
       +---+---+---+---+---+---+
    Solutions = 2
