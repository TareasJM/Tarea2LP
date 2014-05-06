import java.io.Console;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;


public class Board{

	private Bloque[][] board;
	private int emptyBlocks;
	private int[] meta;
	private int time;
	private Interface window;
	private int col,row;
	private boolean painting;

	public Board(){
		this.meta = new int[] {0,0,0,0,0};
		this.board = new Bloque[15][15]; //[y][x] -> [rows][cols]
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				setBlock(row,col,null);
			}
		}
		this.emptyBlocks = 225;
		this.window = new Interface();
		this.row = this.col = -1;
		this.painting = false;
		this.time = 0;
		handleClick(this.window.main, true);
	}

	public void setBlock(int row, int col, Bloque bloque){
		this.board[row][col] = bloque;
	}

	public Bloque getBlock(int row, int col){
		return this.board[row][col];
	}

	public int getEmptyBlocks(){
		return this.emptyBlocks;
	}

	public boolean getDone(){
		for (int i = 0; i<5; i++) {
			if (this.meta[i]>0){
				return false;
			}
		}
		return true;
	}

	public int[] getMeta(){
		return this.meta;
	}

	public void setMeta(int[] meta){
		this.meta = meta;
	}

	public int getTime(){
		return this.time;
	}

	public void setTime(int time){
		this.time = time;
	}

	public void showBoard(int clear){
		painting = true;
		this.window.updateBoard(this);
		// this.window.showBoard();
		
		// if (clear > 0) {
		// 	System.out.print("\033[H\033[2J");
		// 	System.out.flush();
		// }else{
		// 	System.out.print("\n");
		// }
		// System.out.println("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4\n");
		// for (int row=0; row<15; row++) {
		// 	System.out.print(row%10+"  ");
		// 	for (int col=0; col<15; col++) {

		// 		Bloque temp = getBlock(row,col);
		// 		if (temp == null) {
		// 			System.out.print("  ");
		// 		}
		// 		else if( temp instanceof BloqueColor)
		// 		{
		// 			BloqueColor temp2 = (BloqueColor)temp;
		// 			System.out.print(temp2.getColor()+" ");
		// 		}
		// 		else if (temp instanceof BloqueComodin) 
		// 		{
		// 			System.out.print("& ");
		// 		}
		// 	}
		// 	System.out.println(" "+row%10);
		// }
		// System.out.println("\n   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4");

		// System.out.print("Te quedan R: "+this.meta[0]);
		// System.out.print(", B: "+this.meta[1]);
		// System.out.print(", O: "+this.meta[2]);
		// System.out.print(", G: "+this.meta[3]);
		// System.out.println(", Y: "+this.meta[4]);

		try{
			Thread.sleep(time);
		}catch ( InterruptedException e){
			System.out.print("No se puede esperar");
		}
		painting = false;
	}

	public Bloque bloqueRandom(){
		Random rand = new Random(); 
		int number = rand.nextInt(100);
		Bloque bloque;
		if (number < 95){
			bloque = new BloqueColor();
		}
		else{
			bloque = new BloqueComodin();
		}
		return bloque;
	}

	public void fillBoard(){
		while(this.emptyBlocks > 0){

			for (int row=0; row<15; row++) {
				for (int col=0; col<15; col++) {
					if (getBlock(row,col) == null && row > 0) {
						setBlock(row,col,getBlock(row-1,col));
						setBlock(row-1,col,null);
					}
					else if(getBlock(row,col) == null && row == 0){
						setBlock(row,col,bloqueRandom());
						this.emptyBlocks--;
					}
				}
			}
			showBoard(1);
		}
	}

	public void moveBlock(int row, int col, int newRow, int newCol){
        if((Math.abs(newCol-col) == 1 && newRow == row) || (Math.abs(newRow-row) == 1 && newCol == col))
        {

			Bloque temp1 = getBlock(row,col);
			Bloque temp2 = getBlock(newRow, newCol);
			
			
			setBlock(row, col, temp2);
			setBlock(newRow, newCol, temp1);
			showBoard(1);
			int destroyed = checkBoard();

			if (destroyed == 0) {

				setBlock(row, col, temp1);
				setBlock(newRow, newCol, temp2);
				showBoard(1);
			}
		}
	}

	private void destroyCol(int col){
		for (int i = 0 ; i<15; i++) {

			Bloque temp = getBlock(i,col);

			if (temp != null) {
				if( temp instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					 
					if (temp2.getColor().equals("R")) {
						this.meta[0]--;
					}else if (temp2.getColor().equals("B")) {
						this.meta[1]--;
					}else if (temp2.getColor().equals("O")) {
						this.meta[2]--;
					}else if (temp2.getColor().equals("G")) {
						this.meta[3]--;
					}else if (temp2.getColor().equals("Y")) {
						this.meta[4]--;
					}
				}
				int x = board[i][col].DestruirBloque();
				if (x > 0) {
					board[i][col] = null;
				}
			}
		}
		showBoard(1);
	}

	private void destroyRow(int row){
		for (int i = 0 ; i<15; i++) {
			Bloque temp = getBlock(row, i);

			if (temp != null) {
				if( temp instanceof BloqueColor)
				{
						
					BloqueColor temp2 = (BloqueColor)temp;
					 
					if (temp2.getColor().equals("R")) {
						this.meta[0]--;
					}else if (temp2.getColor().equals("B")) {
						this.meta[1]--;
					}else if (temp2.getColor().equals("O")) {
						this.meta[2]--;
					}else if (temp2.getColor().equals("G")) {
						this.meta[3]--;
					}else if (temp2.getColor().equals("Y")) {
						this.meta[4]--;
					}
				}
				int x = board[row][i].DestruirBloque();
				if (x > 0) {
					board[row][i] = null;
				}
			}
		}
		showBoard(1);
	}

	private int checkCol(int col){
		int destroyed = 0;
		int adjoining = 1;
		String prev = "";
		String collect = "";
	
		for (int i=14; i>=0; i--) {
			Bloque temp = getBlock(i, col);
			int b = 0;

			if( temp instanceof BloqueColor)
			{
				BloqueColor temp2 = (BloqueColor)temp;
				 

				if (temp2.getColor() == "*") {
					adjoining = 1;
					prev = "*";
					continue;
				}
		
				if (temp2.getColor() == prev ) {
					adjoining++;
				}

				if (i == 0 && temp2.getColor() == prev) {
					b = 1;
					prev = "*";
				}
				if (temp2.getColor() != prev) {
		
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
						for (int j=adjoining-b; 0-b<j && i+j < 15; j--) {
							if (board[i+j][col] != null) {	
								int x = board[i+j][col].DestruirBloque();
								if (x==0) {
									destroyed++;
								}
								else if (x==1) {
									destroyRow(i);
									destroyed+=15;
									board[i+j][col] = null;
								}
								else if (x==2) {
									destroyCol(col);
									destroyed+=15;
									board[i+j][col] = null;
									return destroyed;
								}
							}
						}
					}
					adjoining = 1;
					collect = prev = temp2.getColor();
				}
			}
			else if (temp instanceof BloqueComodin){
				adjoining++;
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
			Bloque temp = getBlock(row, i);
			int b = 0;

			if( temp instanceof BloqueColor)
			{
				BloqueColor temp2 = (BloqueColor)temp;
				 

				if (temp2.getColor() == "*") {
					adjoining = 1;
					prev = "*";
					continue;
				}
		
				if (temp2.getColor() == prev ) {
					adjoining++;
				}

				if (i == 0 && temp2.getColor() == prev) {
					b = 1;
					prev = "*";
				}
				if (temp2.getColor() != prev) {
		
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
						for (int j=adjoining-b; 0-b<j && i+j < 15; j--) {
							if (board[row][i+j] != null) {	
								int x = board[row][i+j].DestruirBloque();
								if (x==0) {
									destroyed++;
								}
								else if (x==1) {
									destroyRow(row);
									destroyed+=15;
									board[row][i+j] = null;
									return destroyed;
								}
								else if (x==2) {
									destroyCol(i);
									board[row][i+j] = null;
									destroyed+=15;
								}
							}
						}
					}
					adjoining = 1;
					collect = prev = temp2.getColor();
				}
			}
			else if (temp instanceof BloqueComodin){
				adjoining++;
			}
		}
		return destroyed;
	}

	private void destroyCheck(){
		boolean destroyed = false;
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				Bloque temp = getBlock(row,col);
				if (temp == null){
					emptyBlocks++;
					destroyed = true;
				}
				else if( temp instanceof BloqueColor){
					BloqueColor temp2 = (BloqueColor)temp;

					if (temp2.getColor() == "*") {
						setBlock(row,col,null);
						emptyBlocks++;
						destroyed = true;
					}
				}
			}
		}
		if (destroyed) {
			showBoard(1);
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
					destroyCheck();
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
					destroyCheck();
				}
				destroyed += destTemp;
				if (destTemp > 0) {
					row = true;
				}
			}
		}
		return destroyed;
	}

	public void handleClick(JPanel i, boolean add)
	{
    	final Board b = this;
    	final JPanel y = i;

    	if (add)
    	{
    		i.addMouseListener(new MouseAdapter()
    		{   
    			@Override	
	            public void mouseClicked(MouseEvent e) 
	            {

	                if(painting == false)
	                {
	                	e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
	                 
	                	System.out.println(""+e.getY()/30+"-"+e.getX()/30);
	                	// return ola;
	                }
	                // return null;
	             }

	        });
	       // return chao;
	    }
		else
		{
			i.removeMouseListener(i.getMouseListeners()[0]);		
			// return null;
		}
    }

 	static String[] read() throws IOException{
			

        String c = "";
        PipedOutputStream pipeOut = new PipedOutputStream();
        PipedInputStream pipeIn = new PipedInputStream(pipeOut);
        System.setOut(new PrintStream(pipeOut));
        Scanner sc = new Scanner(pipeIn);

        c = sc.nextLine();
        return c.split("-");
        
    }


	public static void main(String[] args){
		String[] click;
		int[] oldClick = new int[] {0,0};
		int[] meta = {100,100,100,100,100};

		if(args.length == 5)
		{
			for(int i=0; i < 5; i++)
			{
				meta[i] = Integer.parseInt(args[i]);
			}
		}
		Board board = new Board();
		board.fillBoard();
 		board.checkBoard();
 		board.setMeta(meta);
 		board.showBoard(1);
 		board.setTime(100);
		while(!board.getDone()){

	 		board.showBoard(1);
			if(!board.painting){
				try{
					click = read();
					board.moveBlock(oldClick[0],oldClick[1],Integer.parseInt(click[0]),Integer.parseInt(click[1]));
					oldClick[0] = Integer.parseInt(click[0]);
					oldClick[1] = Integer.parseInt(click[1]);
				}
				catch(IOException e){
					
				}
			}
		}
		JOptionPane.showMessageDialog(null,"Ganaste CHANCHO CRASH");
		board.window.close();
	}
}