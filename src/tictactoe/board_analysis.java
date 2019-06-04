package tictactoe;

import java.util.HashSet;

class board_analysis {
  private int[][] move_to_vector;
  private int[][] win_vectors;
  private int[] vector_counts_player1;
  private int[] vector_counts_player2;
  private HashSet<Integer> player1_moves;
  private HashSet<Integer> player2_moves;
  board_analysis(){
    this.move_to_vector = new int[][]{{0,3,6},{0,4},{0,5,7},{1,3},{1,4,6,7},{1,5},{2,3,7},{2,4},{2,5,6}};
    this.win_vectors = new int[][]{{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    this.vector_counts_player1 = new int[8];
    this.vector_counts_player2 = new int[8];

    this.player1_moves = new HashSet<>();
    this.player2_moves = new HashSet<>();
  }
  boolean check_win() {
    for(int i =0;i < 8; i++) {
      if (vector_counts_player1[i] == 3 || vector_counts_player2[i] == 3) {
        return true;
      }
    }
    return false;
  }

  int get_priority_ones(char identifier, HashSet<Integer> avaliable){
    int[] vector_counts = (identifier == 'X') ? this.vector_counts_player1 : this.vector_counts_player2;
    for(int i = 0; i < 8; i++) {
      if(vector_counts[i] == 2){
        for( int candidate:this.win_vectors[i]){
          if(avaliable.contains(candidate))
            return candidate;
        }
        }
    }
    return -1;
  }
  void incoming_move(int move, char person) {
    if(person == 'X')
      this.player1_moves.add(move);
    else
      this.player2_moves.add(move);
    int[] vector = (person == 'X') ? this.vector_counts_player1 : this.vector_counts_player2;
    for(int entry:this.move_to_vector[move]){
      vector[entry]+=1;
    }
  }
  boolean theoretical_fork(int move, boolean block){
    int [] vector_counts = (block) ? this.vector_counts_player1.clone() : this.vector_counts_player2.clone();
    for(int entry:this.move_to_vector[move]){
      vector_counts[entry] += 1;
    }
    int two_for_fork = 0;
    int[] oth_vector_counts = (block) ? this.vector_counts_player2.clone() : this.vector_counts_player1.clone();
    for(int i = 0; i<8; i++){
      System.out.println(vector_counts[i]);
      if(vector_counts[i] == 2) {
        System.out.println("other" + oth_vector_counts[i]);
        if (oth_vector_counts[i] == 0)
          two_for_fork += 1;
      }
    }
    System.out.println("fork" + two_for_fork);
    return (two_for_fork >= 2);
  }
}