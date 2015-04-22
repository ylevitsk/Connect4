import java.util.Random;
import java.util.Scanner;


public class connect4 {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final int TOTAL = 43;
	public static String [][] board = new String[ROWS][COLS];
	public static int [] pieces = new int[COLS];
	public static int totalpieces = 0;
	public static int recentCol = 0;
	public static int player = 0;
	public static final int WIN = ROWS*COLS * 100;
	
	public static void main(String[] args) {
		for(int i= 0; i< ROWS; i++){
			for(int j = 0; j < COLS; j++){
				board[i][j] = "*";
				pieces[j] =1;
			}
			
		}
		print();
		Scanner s = new Scanner(System.in);
		while(!isGameOver()){
			if(s.nextLine() != null){
				makeMove();
				print();
				if(player == 0)
					player =1;
				else
					player = 0;			
			}			
		}
		System.out.println("Game Over");
	}


	public static void makeMove(){
		Random rn = new Random();
		int num = rn.nextInt(COLS);	
		recentCol = num;
		if(pieces[num] != COLS){
			if(player == 1)
				board[ROWS- pieces[num]][num] = "X";
			else
				board[ROWS - pieces[num]][num]= "O";
			pieces[num]++;
			totalpieces++;
		}
		else{
		    makeMove();
		}
	}
	public static void print(){
		for(int i= 0; i< ROWS; i++){
			for(int j = 0; j < COLS; j++){
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println();
	}
	public static boolean isGameOver(){
		int column = recentCol;
		int piece = pieces[column]-1;
		
		if(totalpieces == 0)
			return false;
		if(totalpieces == TOTAL)
			return true;
		if(check4(piece, column, -1,0)== WIN)//vertical
			return true;
		for(int i= 0; i<=3; i++){
			if(check4(piece-i, column-i, 1, 1)==WIN) //diagnal upper-right
				return true;
			if(check4(piece-i, column+i, 1, -1)==WIN) //diagnal lower-right
				return true;
			if(check4(piece, column-i, 0, 1)==WIN) //horizontal
				return true;
		}
		return false;
	}
	public static int check4(int row, int col, int x, int y){
		row--;
		int endRow = row + 3*x;
		int endCol = col + 3*y;
		int p= 0, oppon = 0;
		String symbol = "X";
		if(player== 1)
			symbol = "O";
		
		if ((row < 0) || (col < 0) || (endRow < 0) || (endCol < 0)
		  || (row >= ROWS) || (endRow >= ROWS)|| (col >= COLS) || (endCol >= COLS))
			return 0;
		for(int i = 1; i <=4; i++){
			if(board[5-row][col].equalsIgnoreCase(symbol))
				p++;
			else if(!board[5-row][col].equalsIgnoreCase("*"))
				oppon++;
			row += x;
			col += y; 
		}
		if(player > 0 && oppon >0)
			return 0;
		else if (p == 4)
			return WIN;
		else if(oppon == 4)
			return -WIN;
		else 
			return p - oppon;
	
	}
}
