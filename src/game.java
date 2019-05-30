import java.util.Scanner;
import java.util.Random;
public class game {
  public static void main(String args[]){
    System.out.println("Hello welcome to Tic-Tac-Toe");
    //System.out.println("Here are the board position numbers: \n");
    char[] ref_board = {'0','1','2','3','4','5','6','7','8'};
    //print_board(ref_board);
    //char[] game_board = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
    Scanner player = new Scanner(System.in);
    System.out.println("What game do you want to play? 1 - human vs. human  |  2 - human vs. easy computer");
    int gamemode = player.nextInt();
    int winner;
    boolean computer = false;
    switch(gamemode) {
      case 1:
        winner = two_player_game(ref_board, player);
        break;
      case 2:
        winner = easy_Computer(ref_board, player);
        computer = true;
        break;
      default:
        winner = 2;
    }
    String winning_message = (winner == 1)?(computer)?"You Win!":"Player X Wins!":(computer)?
                                                                                        "You Lose!":"Player O Wins!";
    System.out.println(winning_message);
  }
  private static int two_player_game(char[] board, Scanner player){
    print_board(board);
    Boolean winner = false;
    int move;
    while(!winner) {
      System.out.println("Player X's turn, where do you want to go? (0-8)");
      move = player.nextInt();
      board[move] = 'X';
      print_board(board);
      if(win_check(board))
        return 1;
      System.out.println("Player O's turn, where do you want to go? (0-8)");
      move = player.nextInt();
      board[move] = 'O';
      winner = win_check(board);
      print_board(board);
    }
    return 2;

  }
  private static int easy_Computer(char[] board, Scanner player) {
    Random randy = new Random();
    print_board(board);
    Boolean winner = false;
    int move;
    while(!winner) {
      System.out.println("Player X's turn, where do you want to go? (0-8)");
      move = player.nextInt();
      board[move] = 'X';
      print_board(board);
      if (win_check(board))
        return 1;
      rand_Computer_move(board, randy);
      winner =  win_check(board);
      print_board(board);
    }
    return 2;

  }
  private static char[] rand_Computer_move(char[] board, Random randy) {
    int randy_int = randy.nextInt(9);
    board[randy_int] = 'O';
    System.out.println("The computer chose spot " + randy_int);
    return board;
  }
  private static boolean win_check(char[] board){
    for(int i=0; i<=6; i+=3) {
      if(board[i] == ' ')
        continue;
      if (board[i] == board[i+1]) {
        if (board[i] == board[i + 2]) {
          return true;
        }
      }
    }
    for(int i=0;i<3;i++){
      if(board[i] == ' ')
        continue;
      if(board[i] == board[i+3]){
        if(board[i] == board[i+6]){
          return true;
        }
      }
    }
    if(board[4] != ' ') {
      if ((board[0] == board[4]) && (board[0] == board[8]))
        return true;
      if ((board[2] == board[4]) && (board[2] == board[6]))
        return true;
    }
    return false;
  }
  private static void print_board(char[] board){
    for(int i=0; i<=6; i+=3){
      for(int j=0; j<2; j++){
        System.out.print(board[i+j] + "  |  ");
      }
      System.out.println(board[i+2]);
      if(i != 6){
        System.out.println("-------------");
      }
    }
  }
}

