package tictactoe;

public class board_analysis {
  private int[] win_vector_player1;
  private int[] win_vector_player2;
  public board_analysis(){
    this.win_vector_player1 = new int[8];
    this.win_vector_player2 = new int[8];
  }
  public boolean check_win() {
    for(int i =0;i < 8; i++) {
      if (win_vector_player1[i] == 3 || win_vector_player2[i] == 3) {
        return true;
      }
    }
    return false;
  }
  public void incoming_move(int move, char person) {
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
