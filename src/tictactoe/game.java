package tictactoe;
import java.util.*;

public class game {
  public static void main(String args[]){
    System.out.println("Hello welcome to Tic-Tac-Toe");
    Scanner player = new Scanner(System.in);
    while(true) {
      char[] ref_board = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
      System.out.println(
          "What game do you want to play? 1 - human vs. human  |  2 - human vs. easy computer  |  3 - human vs. medium computer");
      int gamemode = get_player_int(player);
      int winner;
      boolean computer = false;
      switch (gamemode) {
        case 1:
          winner = two_player_game(ref_board, player);
          break;
        case 2:
          winner = easy_Computer(ref_board, player);
          computer = true;
          break;
        case 3:
          winner = medium_Computer(ref_board, player);
          break;
        default:
          winner = 3;
      }
      String winning_message = (winner == 3) ? "Tie game" : (winner == 1) ? (computer) ? "You Win!" : "Player X Wins!"
                                                                : (computer) ? "You Lose!" : "Player O Wins!";
      System.out.println(winning_message);
      System.out.println("Play Again? 0 - Yes  |  1 - No");
      if(get_player_int(player) != 0)
        break;
    }
  }
  private static int two_player_game(char[] board, Scanner player){
    board_analysis referee = new board_analysis();
    print_board(board);
    char person = 'X';
    int move;
    ArrayList<Integer> available= new ArrayList<>(java.util.Arrays.asList(0,1,2,3,4,5,6,7,8));
    while(!available.isEmpty()) {
      move = get_player_move(available, person, player);
      available.remove(Integer.valueOf(move));
      board[move] = person;
      referee.incoming_move(move, person);
      print_board(board);
      if(referee.check_win())
        return (person == 'X') ? 1 : 2;
      person = (person == 'X') ? 'O' : 'X';
    }
    return 3;
  }
  private static int get_player_move(ArrayList<Integer> available, char person, Scanner player) {
    int move;
    while (true) {
      System.out.println("Player " + person + "'s turn, where do you want to go? (0-8)");
      move = get_player_int(player);
      if (available.contains(move))
        break;
      System.out.println("That spot is taken, choose again");
    }
    return move;
  }
  private static int get_player_int(Scanner player) {
    int move;
      while (true) {
          try {
            move = player.nextInt();
            break;
          } catch (InputMismatchException e) {
            System.out.println("Invalid input, must be an integer");
            player.nextLine();
          }
        }
    return move;
  }

  private static int easy_Computer(char[] board, Scanner player) {
    ArrayList<Integer> available= new ArrayList<>(java.util.Arrays.asList(0,1,2,3,4,5,6,7,8));
    board_analysis referee = new board_analysis();
    Random randy = new Random();
    print_board(board);
    int move;
    while(!available.isEmpty()) {
      move = get_player_move(available, 'X', player);
      available.remove(Integer.valueOf(move));
      referee.incoming_move(move, 'X');
      board[move] = 'X';
      print_board(board);
      if(referee.check_win())
        return 1;
      rand_Computer_move(board, randy,available,referee);
            print_board(board);
      if(referee.check_win())
        return 2;
    }
    return 3;
  }
  private static int medium_Computer(char[] board, Scanner player) {
    ArrayList<Integer> available= new ArrayList<>(java.util.Arrays.asList(0,1,2,3,4,5,6,7,8));
    board_analysis referee = new board_analysis();
    Random randy = new Random();
    print_board(board);
    int move;
    while(!available.isEmpty()) {
      move = get_player_move(available, 'X', player);
      available.remove(Integer.valueOf(move));
      referee.incoming_move(move, 'X');
      board[move] = 'X';
      print_board(board);
      if(referee.check_win())
        return 1;
      if(!win_or_block(board, referee,available))
        rand_Computer_move(board, randy,available,referee);
      print_board(board);
      if(referee.check_win())
        return 2;
    }
    return 3;
  }
  private static boolean win_or_block(char[] board, board_analysis referee, ArrayList<Integer> available) {
    HashSet<Integer> possibilities = referee.get_priority_ones('O');
    int move;
    for(Integer possibility:possibilities) {
      if(available.contains(possibility)){
        move = possibility;
        board[move] = 'O';
        referee.incoming_move(move, 'O');
        System.out.println("The computer chose spot " + move);
        available.remove(Integer.valueOf(move));
        return true;
      }
    }
    possibilities = referee.get_priority_ones('X');
    for(Integer possibility:possibilities) {
      if(available.contains(possibility)){
        move = possibility;
        board[move] = 'O';
        referee.incoming_move(move, 'O');
        System.out.println("The computer chose spot " + move);
        available.remove(Integer.valueOf(move));
        return true;
      }
    }
    return false;
  }
  private static void rand_Computer_move(char[] board, Random randy, ArrayList<Integer> available,board_analysis referee) {
    int randy_int = -1;
    for(int i = 0; i < 10; i++) {
      randy_int = randy.nextInt(9);
      if (available.contains(randy_int))
        break;
    }
    available.remove(Integer.valueOf(randy_int));
    board[randy_int] = 'O';
    referee.incoming_move(randy_int, 'O');
    System.out.println("The computer chose spot " + randy_int);
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