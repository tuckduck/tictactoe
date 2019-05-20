import java.util.Scanner;
public class game {
  public static void main(String args[]){
    System.out.println("Hello welcome to Tic-Tac-Toe");
    //System.out.println("Here are the board position numbers: \n");
    char[] ref_board = {'0','1','2','3','4','5','6','7','8'};
    //print_board(ref_board);
    //char[] game_board = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
    int winner = two_player_game(ref_board);
    if(winner == 1){
      System.out.println("Player X Wins!");
    }
    else
      System.out.println("Player O Wins!");

  }
  public static int two_player_game(char[] board){
    print_board(board);
    Scanner player = new Scanner(System.in);
    Boolean winner = false;
    int move = -1;
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
  public static boolean win_check(char[] board){
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
  public static void print_board(char[] board){
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

