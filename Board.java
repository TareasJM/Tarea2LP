package Board;

import java.util.Random;
import java.util.Scanner;

public class Board{

	private String[][] board;
	private int emptyBlocks;
	private int[] meta;

	public Board(int[] meta){
		this.meta = meta;
		this.board = new String[15][15]; //[y][x] -> [rows][cols]
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				setBlock(row,col," ");
			}
		}
		this.emptyBlocks = 225;
	}

	public void clone(Board original){
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				setBlock(row,col,original.getBlock(row,col));
			}
		}
		this.emptyBlocks = original.emptyBlocks;
		this.meta = original.meta.clone();
	}

	public void setBlock(int row, int col, String value){
		this.board[row][col] = value;
	}

	public String getBlock(int row, int col){
		return this.board[row][col];
	}

	public int getEmptyBlocks(){
		return this.emptyBlocks;
	}

	public boolean getDone(){
		boolean m = true;
		for (int i = 0; i<5; i++) {
			if (this.meta[i]>0){
				m = false;
				break;
			}
		}
		return m;
	}


	public void showBoard(int clear, int time){
		
		if (clear > 0) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}else{
			System.out.print("\n");
		}
		System.out.println("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4\n");
		for (int row=0; row<15; row++) {
			System.out.print(row%10+"  ");
			for (int col=0; col<15; col++) {
				System.out.print(getBlock(row,col)+" ");
			}
			System.out.println("  "+row%10);
		}
		System.out.println("\n   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4");

		System.out.print("Te quedan R: "+this.meta[0]);
		System.out.print(", B: "+this.meta[1]);
		System.out.print(", O: "+this.meta[2]);
		System.out.print(", G: "+this.meta[3]);
		System.out.println(", Y: "+this.meta[4]);

		try{
			Thread.sleep(time * 10);
		}catch ( InterruptedException e){
			System.out.print("No se puede esperar");
		}
	}

	//Esto deberia ser crearBloqueAleatorio
	public String randomColor(){
		Random rand = new Random(); 
		int number = rand.nextInt(1000);
		if (number < 190){
			return "R";
		}
		else if (number < 380){
			return "B";
		}
		else if (number < 570) {
			return "O";
		}
		else if (number < 760) {
		 	return "G";
		}
		else if (number < 950) {
			return "Y";
		}
		else if (number < 975) {
			return "$";//fila
		}
		else{
			return "&";//columna
		}
	}

	public void fillBoard(){
		while(this.emptyBlocks > 0){

			for (int row=0; row<15; row++) {
				for (int col=0; col<15; col++) {
					if (getBlock(row,col) == " " && row > 0) {
						setBlock(row,col,getBlock(row-1,col));
						setBlock(row-1,col," ");
					}
					else if(getBlock(row,col) == " " && row == 0){
						setBlock(row,col,randomColor());
						this.emptyBlocks--;
					}
				}
			}
			showBoard(1,10);
		}
	}

	private void moveBlock(int row, int col, String dir){
		Board tempB = new Board(new int[5]);
		tempB.clone(this);
		String temp1 = getBlock(row,col);
		String temp2;
		if (dir.equals("u") && row > 0) {
			temp2 = getBlock(row-1,col);
			setBlock(row, col, "A");
			setBlock(row-1, col, "V");
			showBoard(1,80);
			setBlock(row, col, temp2);
			setBlock(row-1, col, temp1);
			showBoard(1,80);
		}else if (dir.equals("d") && row < 14){
			temp2 = getBlock(row+1,col);
			setBlock(row, col, "V");
			setBlock(row+1, col, "A");
			showBoard(1,80);
			setBlock(row, col, temp2);
			setBlock(row+1, col, temp1);
			showBoard(1,80);
		}else if (dir.equals("l") && col > 0){
			temp2 = getBlock(row,col-1);
			setBlock(row, col, "<");
			setBlock(row, col-1, ">");
			showBoard(1,80);
			setBlock(row, col, temp2);
			setBlock(row, col-1, temp1);
			showBoard(1,80);
		}else if (dir.equals("r") && col < 14){
			temp2 = getBlock(row,col+1);
			setBlock(row, col, ">");
			setBlock(row, col+1, "<");
			showBoard(1,80);
			setBlock(row, col, temp2);
			setBlock(row, col+1, temp1);
			showBoard(1,80);
		}else{
			return;
		}

		int destroyed = checkBoard();
		if (destroyed == 0) {
			clone(tempB);
			showBoard(1,500);
		}
	}

	private void destroyCol(int col){
		for (int i = 0 ; i<15; i++) {
			if (board[i][col] == "R") {
				this.meta[0]--;
			}else if (board[i][col] == "B") {
				this.meta[1]--;
			}else if (board[i][col] == "O") {
				this.meta[2]--;
			}else if (board[i][col] == "G") {
				this.meta[3]--;
			}else if (board[i][col] == "Y") {
				this.meta[4]--;
			}
			board[i][col] = "|";
		}
	}

	private void destroyRow(int row){
		for (int i = 0 ; i<15; i++) {
			if (board[row][i] == "R") {
				this.meta[0]--;
			}else if (board[row][i] == "B") {
				this.meta[1]--;
			}else if (board[row][i] == "O") {
				this.meta[2]--;
			}else if (board[row][i] == "G") {
				this.meta[3]--;
			}else if (board[row][i] == "Y") {
				this.meta[4]--;
			}
			board[row][i] = "-";
		}
	}

	private int checkCol(int col){
		int destroyed = 0;
		int adjoining = 1;
		String prev = "";
		String collect = "";
	
		for (int i=14; i>=0; i--) {
			boolean dCol = false;
			boolean dRow = false;
			int b = 0;

			if (board[i][col] == "|" || board[i][col] == "-") {
				adjoining = 1;
				prev = "|";
				continue;
			}
	
			if (board[i][col] == prev ) {
				adjoining++;
			}
			else if (board[i][col] == "&") {
				adjoining++;
				dCol = true;
			}
			else if (board[i][col] == "$") {
				adjoining++;
				dRow = true;
			}
			
			if (i == 0 && board[i][col] == prev) {
				b = 1;
				prev = "|";
			}
			if (board[i][col] != prev) {
	
				if (adjoining > 2) {
					if (collect == "R") {
						this.meta[0] -=adjoining;
					}else if (collect == "B") {
						this.meta[1] -=adjoining;
					}else if (collect == "O") {
						this.meta[2] -=adjoining;
					}else if (collect == "G") {
						this.meta[3] -=adjoining;
					}else if (collect == "Y") {
						this.meta[4] -=adjoining;
					}
					if (dCol) {
						destroyCol(col);
						destroyed+=15;
					}
					else{
						for (int j=adjoining-b; j>0-b; j--) {
							board[i+j][col] = "|";
							destroyed++;
						}
					}
					if (dRow) {
						destroyRow(i);
						destroyed+=15;
					}
				}
				adjoining = 1;
				collect = prev = board[i][col];
				
			}
		}
		return destroyed;
	}

	private int checkRow(int row){
		int destroyed = 0;
		int adjoining = 1;
		String prev = "";
		String collect = "";
	
		for (int i=14; i>=0; i--) {
			boolean dCol = false;
			boolean dRow = false;
			int b = 0;

			if (board[row][i] == "|" || board[row][i] == "-") {
				adjoining = 1;
				prev = "-";
				continue;
			}
	
			if (board[row][i] == prev ) {
				adjoining++;
			}
			else if (board[row][i] == "&") {
				adjoining++;
				dCol = true;
			}
			else if (board[row][i] == "$") {
				adjoining++;
				dRow = true;
			}
			if (i == 0 && board[row][i] == prev) {
				b = 1;
				prev = "|";
			}
			if (board[row][i] != prev) {

				if (adjoining > 2) {
					if (collect == "R") {
						this.meta[0] -=adjoining;
					}else if (collect == "B") {
						this.meta[1] -=adjoining;
					}else if (collect == "O") {
						this.meta[2] -=adjoining;
					}else if (collect == "G") {
						this.meta[3] -=adjoining;
					}else if (collect == "Y") {
						this.meta[4] -=adjoining;
					}
					if (dRow) {
						destroyRow(row);
						destroyed+=15;
					}
					else{
						for (int j=adjoining-b; j>0-b; j--) {
							board[row][i+j] = "-";
							destroyed++;
						}
					}
					if (dCol) {
						destroyCol(i);
						destroyed+=15;
					}
				}
				adjoining = 1;
				collect = prev = board[row][i];
				
			}
		}
		return destroyed;
	}

	private void destroyChecked(){
		boolean destroyed = false;
		Board temp = new Board(new int[5]);
		temp.clone(this);
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				if (temp.getBlock(row,col) == "-" || temp.getBlock(row,col) == "|") {
					setBlock(row,col," ");
					emptyBlocks++;
					destroyed = true;
				}
			}
		}
		if (destroyed) {
			temp.showBoard(1,5);
			showBoard(1,5);
			temp.showBoard(1,5);
			showBoard(1,5);
			temp.showBoard(1,5);
			showBoard(1,5);
			temp.showBoard(1,5);
			fillBoard();
		}
	}

	public int checkBoard(){
		int destroyed = 0;
		int destTemp = 0;
		boolean col = true, row = true;
		while(col || row){
			col = row = false;
			destTemp = 1;
			while(destTemp > 0){
				destTemp = 0;
				for (int i=0; i<15; i++) {
					destTemp += checkCol(i);
					destroyChecked();
				}
				destroyed += destTemp;
				if (destTemp > 0) {
					col = true;
				}
			}
			destTemp = 1;
			while(destTemp > 0){
				destTemp = 0;
				for (int i=14; i>=0; i--) {
					destTemp += checkRow(i);
					destroyChecked();
				}
				destroyed += destTemp;
				if (destTemp > 0) {
					row = true;
				}
			}
		}
		return destroyed;
	}

	// public static void main(String[] args){
	// 	Scanner keyboard = new Scanner(System.in);

	// 	System.out.println("Ingrese un numero bloques:");
	// 	String[] c = keyboard.nextLine().split(" ");
	// 	int[] meta = {Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]),Integer.parseInt(c[3]),Integer.parseInt(c[4])};
	// 	Board board = new Board(meta);
		
	// 	board.fillBoard();

	// 	board.showBoard(1,10);

	// 	board.checkBoard();

	// 	while(!board.getDone()){
	// 		System.out.println("Ingrese un movimiento:");
	// 		c = keyboard.nextLine().split(" ");
	// 		board.moveBlock(Integer.parseInt(c[0]),Integer.parseInt(c[1]),c[2]);
	// 	}
	// }
}