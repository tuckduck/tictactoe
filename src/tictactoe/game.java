package tictactoe;

import java.util.*;

public class game {
  public static void main(String args[]){
    System.out.println("Hello welcome to Tic-Tac-Toe");
    Scanner player = new Scanner(System.in);
    do {
      char[] ref_board = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
      System.out.println(
          "What game do you want to play? 1 - human vs. human  |  2 - human vs. easy computer  |  3 - human vs. medium computer  |  4 - human vs. hard computer");
      int winner;
      boolean computer = false;
      switch (get_player_int(player)) {
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
        case 4:
          winner = hard_Computer(ref_board, player);
          break;
        default:
          winner = 3;
      }
      String winning_message = (winner == 3) ? "Tie game" : (winner == 1) ? (computer) ? "You Win!" : "Player X Wins!"
                                                                : (computer) ? "You Lose!" : "Player O Wins!";
      System.out.println(winning_message);
      System.out.println("Play Again? 0 - Yes  |  1 - No");
    } while (get_player_int(player) == 0);
  }
  private static int two_player_game(char[] board, Scanner player){
    board_analysis referee = new board_analysis();
    print_board(board);
    char person = 'X';
    HashSet<Integer> available= new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    while(!available.isEmpty()) {
      player_move(available, person, player, board, referee);
      print_board(board);
      if(referee.check_win())
        return (person == 'X') ? 1 : 2;
      person = (person == 'X') ? 'O' : 'X';
    }
    return 3;
  }
  private static void player_move(HashSet<Integer> available, char person,
                                   Scanner player, char[] board, board_analysis referee){
    int move = get_player_move(available, person, player);
    board[move] = person;
    available.remove(move);
    referee.incoming_move(move, person);
    print_board(board);
  }
  private static int get_player_move(HashSet<Integer> available, char person, Scanner player) {
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
  private static void process_computer_move(int move, HashSet<Integer> available, char[] board, board_analysis referee){
    board[move] = 'O';
    available.remove(move);
    referee.incoming_move(move, 'O');
    System.out.println("The computer chose spot " + move);
  }
  private static int easy_Computer(char[] board, Scanner player) {
    HashSet<Integer> available= new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    board_analysis referee = new board_analysis();
    Random randy = new Random();
    print_board(board);
    while(!available.isEmpty()) {
      player_move(available, 'X', player, board, referee);
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
    HashSet<Integer> available= new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    board_analysis referee = new board_analysis();
    Random randy = new Random();
    print_board(board);
    while(!available.isEmpty()) {
      player_move(available, 'X', player, board, referee);
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
  private static int hard_Computer(char[] board, Scanner player) {
    HashSet<Integer> available= new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    board_analysis referee = new board_analysis();
    Random randy = new Random();
    print_board(board);
    while(!available.isEmpty()) {
      player_move(available, 'X', player, board, referee);
      if(referee.check_win())
        return 1;
      if(!win_or_block(board, referee,available))
        if(!create_or_block_fork(board, referee, available))
          if(available.contains(4))
            process_computer_move(4, available, board, referee);
          else
            rand_Computer_move(board, randy,available,referee);
      print_board(board);
      if(referee.check_win())
        return 2;
    }
    return 3;
  }
  private static boolean create_or_block_fork(char [] board, board_analysis referee, HashSet<Integer> available){
    for(int choice:available){
      if(referee.theoretical_fork(choice, false)){
        board[choice] = 'O';
        referee.incoming_move(choice, 'O');
        System.out.println("The computer chose spot " + choice);
        available.remove(choice);
        return true;
      }
    }
    for(int choice:available){
      if(referee.theoretical_fork(choice, true)){
        board[choice] = 'O';
        referee.incoming_move(choice, 'O');
        System.out.println("The computer chose spot " + choice);
        available.remove(choice);
        return true;
      }
    }
    return false;
  }
  private static boolean win_or_block(char[] board, board_analysis referee, HashSet<Integer> available) {
    int move;
    if(available.contains(move = referee.get_priority_ones('O',available))){
      board[move] = 'O';
      referee.incoming_move(move, 'O');
      System.out.println("The computer chose spot " + move);
      available.remove(move);
      return true;
    }
    if(available.contains(move = referee.get_priority_ones('X',available))){
      board[move] = 'O';
      referee.incoming_move(move, 'O');
      System.out.println("The computer chose spot " + move);
      available.remove(move);
      return true;
    }
    return false;
  }
  private static void rand_Computer_move(char[] board, Random randy, HashSet<Integer> available,board_analysis referee) {
    int randy_int = -1;
    while(!available.isEmpty()){
      randy_int = randy.nextInt(9);
      if (available.contains(randy_int)) {
        available.remove(randy_int);
        board[randy_int] = 'O';
        referee.incoming_move(randy_int, 'O');
        System.out.println("The computer chose spot " + randy_int);
        break;
      }
    }
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