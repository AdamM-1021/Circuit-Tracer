***************
Circuit Tracer
CS 221
5/23/22
Adam McCall
***************

OVERVIEW:
 This program takes an array with a start and end point and finds
 the shortest circuit paths between them. It can be displayed through
 the console or with a GUI
 
 
INCLUDED FILES:
 CircuitTracer.java - source file
 CircuitBoard.java - source file
 CircuitPanel.java - source file
 PathPanel.java - source file
 README.txt - this file
 
 
COMPILING AND RUNNING:
 From the directory containing the source files, compile driver class
 with the command:
 $ javac CircuitTracer.java
 
 Run the class with the command:
 $ java CircuitTracer [-s | -q] [-c | -g] [filename]
 [-s | -q] : Stack or Queue storage model
 [-c | -g] : console or GUI output type
 [filename] : The name of the board you want to trace

 Output will either be given in the console or GUI
 

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 Circuit tracer uses a stack or queue storage model to search for
 the best possible paths to connect 2 points on a board. Using
 the Trace State's "isOpen" feature, the main CircuitTracer classes 
 to create a new state for each possible open path. These states
 are then passed into the selected storage model, and new states
 are made from those, until a path is touching the end point.
 
 There is a list, bestPaths, that keeps track of all lists with
 the lowest path length, and will clear the list if a new 'record'
 smallest path is found. After all possible paths are exhausted,
 the list of the best paths are returned either through the console
 or through a GUI.
 
 The GUI uses a split pane with a panel for the board to go and a
 list on the side, where you can select each of the best paths.
 It makes the board by using a grid layout and each coordinate on
 the board is an individual label. When a board is selected, the 
 board will display that panel specifically.
 
 
TESTING:
 
 CircuitTracer was tested with a pre-made testing class, which would
 test both valid and invalid input to check for unexpected errors.
 It handles all kinds of invalid input that were tested, and outputs
 a usage message if the class was called incorrectly. Most of the errors
 I initially got were from invalid input, which I added catches for.
 
 
 
DISCUSSION:

 Most of the errors with CircuitTracer itself dealt with invalid input,
 and I had to add extra catches to make sure the program doesn't encounter
 and unexpected error.
 
 There was also many bugs with the GUI that I had to research and work out.
 It took a while to learn to initialize the array correctly, and I didn't 
 have the list at that point. I initially tried to initialize the grid inside
 another label, which had to be changed to a panel instead. Then, once I
 added the selection list, the grid wouldn't be initialized. I had to add 
 an updater to the grid constructor, then the program functioned correctly.
 
 Once I learned how to implement the list selector, it was much easier to
 figure out the rest of GUI construction.
 
 
EXTRA CREDIT:

 I added a functioning GUI display that highlights the path and start and end
 points, and should scale so the grid boxes are always squares.


