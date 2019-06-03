# tictactoe
Tic-tac-toe Java8 Implementation

An implementation of Tic-Tac-Toe in Java8 by Tucker Reinhardt

game Class main method handles the menu loop with game mode selection.
Method for each gamemode runs the game loop, creating a board_analysis object which performs the operations on the board
to check for wins and determine AI moves. Specific actions are seperated into methods, to keep the game loops readable.

board_analysis Class handles the win-condition checking and determines AI moves. incoming_move method operates upon member
variables to reflect a particular move made by a player. These member variables allow for quick win-checking and AI moves.
