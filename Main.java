import java.util.*;
import java.io.*;
import java.math.*;
public class Main {

	private static char currBoard[][]= {
										{'_','_','_'},
										{'O','_','_'},			
										{'_','_','_'}
										};
	
	
	
	private static boolean checkWinner(char board[][]) {//return true if someone has won.

		
		for(int i=0;i<3;i++) {//check columns
			if(board[0][i]=='S' && board[1][i]=='O' && board[2][i]=='S') {
				return true;
			}
		}
		
		for(int i=0;i<3;i++) {//check rows
			if(board[i][0]=='S' && board[i][1]=='O' && board[i][2]=='S') {
				return true;
			}
		}
		
		if(board[0][0]=='S' && board[1][1]=='O' && board[2][2]=='S') {//check main diagonal
			return true;
		}
		else if(board[0][2]=='S' && board[1][1]=='O' && board[2][0]=='S') {//check the other diagonal
			return true;
			
		}
		
		return false;
	}
	
	private static void findBestMove(char board[][])	 { 	//epestrepse tin kalyteri kinisi
		
		Move bestMove=new Move();
		bestMove.row=-1;
		bestMove.col=-1;
		double bestVal=-1000;	
		char moveChar='c';
		
		for(int i=0;i<3;i++) {//find available cells.
			for(int j=0;j<3;j++) {
				if(board[i][j]=='_') {
					board[i][j]='S';
					double moveVal=minimax(board,3,true);
					board[i][j]='_';
					
					if(moveVal>bestVal){
						moveChar='S';
						bestVal=moveVal;
						bestMove.row=i;
						bestMove.col=j;
					}
					
					board[i][j]='O';
					moveVal=minimax(board,3,true);
					board[i][j]='_';
					if(moveVal>bestVal){
						moveChar='O';
						bestVal=moveVal;
						bestMove.row=i;
						bestMove.col=j;
					}
					
				}
			}
		}
		currBoard[bestMove.row][bestMove.col]= moveChar ; // update ton pinaka me ti sosti kinisi poy bgainei apo ta if 
				
		System.out.println(bestMove.row+"\t"+bestMove.col);
		System.out.println(moveChar);
		//System.out.print(bestScore);
	}

	private static int evaluateBoard(char board[][],boolean isMax) { //aksiologise tin kathe kinisi , me to boolean blepo poios paizei kai epistrefo thetiko i arnitiko antistoixas
		if(checkWinner(board) && isMax) {
			return 100;			
		}
		else if(checkWinner(board) && isMax==false) {
			return -100;
		}
		for(int i=0;i<2;i++) {
			if((board[i][0]=='S' && board[i][1]=='O') || (board[i][1]=='O' && board[i][2]=='S')){
				return -100;
			}
		}
		for(int i=0;i<2;i++) {
			if((board[0][i]=='S' && board[1][i]=='O') || (board[1][i]=='O' && board[2][i]=='S')){
				return -100;
			}
		}
		if((board[0][0]=='S' && board[1][1]=='O') || (board[1][1]=='O' && board[2][2]=='S')){
			return -100;
		}
		if((board[0][2]=='S' && board[1][1]=='O') || (board[1][1]=='O' && board[2][0]=='S')){
			return -100;
		}
		if(board[0][0] =='S'&& board[0][1]=='_' && board[0][2]=='S') {
			return -100;
		}
		if(board[1][0] =='S'&& board[1][1]=='_' && board[1][2]=='S') {		// gia S keno S orizontia
			return -100;
		}
		if(board[2][0] =='S'&& board[2][1]=='_' && board[2][2]=='S') {
			return -100;
		}
		if(board[0][0] =='S'&& board[0][1]=='_' && board[0][2]=='S') {
			return - 100;
		}
		if(board[0][1] =='S'&& board[1][1]=='_' && board[2][1]=='S') {
			return - 100;
		}
		if(board[0][2] =='S'&& board[1][2]=='_' && board[2][2]=='S') {
			return - 100;
		}
			return 0;
	}
	
	private static void printArray(char board[][]) {
		
		    System.out.println(board[0][0] + "-" + board[0][1] + "-" + board[0][2]);  
		    System.out.println(board[1][0] + "-" + board[1][1] + "-" + board[1][2]);  
		    System.out.println(board[2][0] + "-" + board[2][1] + "-" + board[2][2]);           
		    System.out.println("----------------");
		    }
	

	
	
	private  static double minimax(char board[][],int depth1,boolean isMaximizer) {
		//System.out.print(isMaximizer);
		/*if(checkWinner(board)) {
			
			return 100;
		}*/
		int boardScore=evaluateBoard(board,isMaximizer);
		if(boardScore==100) {
			
			return 100;
		}
		if(boardScore==-100) {
			
			return -100;
		}
		if(boardScore==0) {
			
			return 0;
		}
		
		
			if(isMaximizer) {
				
				double bestScore=-1000;	
			//System.out.print(bestScore);
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						if(board[i][j]=='_') {//find available cells.
							
							board[i][j]='S';//place S and call minimax.
							bestScore = Math.max(minimax(board,depth1-1,false),bestScore);
							board[i][j]='_';//undo the move.
							board[i][j]='O';//place S and call minimax.
							bestScore = Math.max(minimax(board,depth1-1,false),bestScore);
							board[i][j]='_';//undo the move.
						}
					}
			}
			//System.out.print(bestScore);
			return bestScore;
		}
		
		else {//minimizer's turn.
			
			double bestScore=Double.POSITIVE_INFINITY;
			//System.out.print(bestScore);
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					
					if(board[i][j]=='_') {//find available cells.
						//System.out.print("maxif");
						board[i][j]='S';//place S and call minimax.
						bestScore = Math.min(minimax(board,depth1-1,true),bestScore);
						board[i][j]='_';//undo the move.
						board[i][j]='O';//place S and call minimax.
						bestScore = Math.min(minimax(board,depth1-1,false),bestScore);
						board[i][j]='_';//undo the move.
					}
				}
			}	
			//System.out.print(bestScore);
			return bestScore;
		}
		
	}
	
	private static boolean hasRemainingMove(char currBoard[][]) {
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j < 3 ; j++ ) {
				if(currBoard[i][j]=='_') {
					return true ;
				}
			}
		}
		return false;
		
	}
	
 	public static void main(String[] args) {
 		Scanner input = new Scanner(System.in);
			printArray(currBoard);
		int i,j;
		char ch;
		while(hasRemainingMove(currBoard)) {
			
			System.out.println("CPU MOVE.");
			findBestMove(currBoard);
			printArray(currBoard);
			if(checkWinner(currBoard) == true) {
				printArray(currBoard);
				System.out.println("exases");
				break;
			}
			System.out.println("PLAYER'S MOVE.");
			
			System.out.println("write S or O:");
			ch=input.next().charAt(0);
			
			System.out.println("write i:");
			i=input.nextInt();
			
			System.out.println("write j:");
			j=input.nextInt();
			
			currBoard[i][j]=ch;
			if(checkWinner(currBoard) == true) {
				printArray(currBoard);
				System.out.println("nikises");
				break;
			}
			printArray(currBoard);
		}
	
		
 	}	

		//double c=minimax(initboard,3,true);
			//System.out.print(c);
			//System.out.print(checkWinner(initboard));
		
	}//end of class


