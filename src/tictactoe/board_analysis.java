package tictactoe;

import java.util.HashSet;

class board_analysis {
  private int[] win_vector_player1;
  private int[] win_vector_player2;
  board_analysis(){
    this.win_vector_player1 = new int[8];
    this.win_vector_player2 = new int[8];
  }
  boolean check_win() {
    for(int i =0;i < 8; i++) {
      if (win_vector_player1[i] == 3 || win_vector_player2[i] == 3) {
        return true;
      }
    }
    return false;
  }
  HashSet<Integer> get_priority_ones(char identifier){
    HashSet<Integer> priorities = new HashSet<>();
    int[] vector = (identifier == 'X') ? this.win_vector_player1: this.win_vector_player2;
    if(vector[0] == 2){
      priorities.add(0);
      priorities.add(1);
      priorities.add(2);
    }
    if(vector[1] == 2){
      priorities.add(3);
      priorities.add(4);
      priorities.add(5);
    }
    if(vector[2] == 2){
      priorities.add(6);
      priorities.add(7);
      priorities.add(8);
    }
    if(vector[3] == 2){
      priorities.add(0);
      priorities.add(3);
      priorities.add(6);
    }
    if(vector[4] == 2){
      priorities.add(1);
      priorities.add(4);
      priorities.add(7);
    }
    if(vector[5] == 2){
      priorities.add(2);
      priorities.add(5);
      priorities.add(8);
    }
    if(vector[6] == 2){
      priorities.add(0);
      priorities.add(4);
      priorities.add(8);
    }
    if(vector[7] == 2){
      priorities.add(2);
      priorities.add(4);
      priorities.add(6);
    }
    return priorities;
  }
  void incoming_move(int move, char person) {
    int[] vector = (person =='X') ? this.win_vector_player1 : this.win_vector_player2;
    switch(move){
      case 0:
        vector[0] += 1;
        vector[3] += 1;
        vector[6] += 1;
        break;
      case 1:
        vector[0] += 1;
        vector[4] += 1;
        break;
      case 2:
        vector[0] += 1;
        vector[5] += 1;
        vector[7] += 1;
        break;
      case 3:
        vector[1] += 1;
        vector[3] += 1;
        break;
      case 4:
        vector[1] += 1;
        vector[4] += 1;
        vector[6] += 1;
        vector[7] += 1;
        break;
      case 5:
        vector[1] += 1;
        vector[5] += 1;
        break;
      case 6:
        vector[2] += 1;
        vector[3] += 1;
        vector[7] += 1;
        break;
      case 7:
        vector[2] += 1;
        vector[4] += 1;
        break;
      case 8:
        vector[2] += 1;
        vector[5] += 1;
        vector[6] += 1;
        break;
      default:
        throw new IllegalArgumentException("Invalid move attempted");

    }
  }
}
