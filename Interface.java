package Interface;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import Board.*;

class Interface extends JFrame
{
    JPanel colorBlock;
    Board board;
    Dimension d;

    public Interface(Board board)
    {
        this.board = board;
        setTitle("Candi crach");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(15,15)); //FILA / COLUMNAS
    }

    private void showBoard(int time)
    {  
    	removeAll();      
        for (int i = 0; i<15; i++) {
        	for (int j = 0; j<15; j++ ) {
        		String colorN = board.getBlock(i,j);
        		Color color;
        		if (colorN.equals("R")) {
        			color = Color.RED;
        		} else if (colorN.equals("B")) {
					color = Color.BLUE;        			
        		} else if (colorN.equals("O")) {
					color = Color.ORANGE;        			
        		} else if (colorN.equals("G")) {
					color = Color.GREEN;        			
        		} else if (colorN.equals("Y")) {
					color = Color.YELLOW;       			
        		} else if (colorN.equals("$")) {
        			color = Color.PINK;
        		} else {
        			color = Color.PINK;
        		}
				colorBlock = new JPanel();
				d=new Dimension(40,40);
				colorBlock.add(new JLabel(colorN));
				colorBlock.setBackground(color);
				colorBlock.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
				add(colorBlock);
        	}
        }
        setVisible(true);
        pack();

        try{
			Thread.sleep(time * 10);
		}catch ( InterruptedException e){
			System.out.print("No se puede esperar");
		}
        
    }
    
    public static void main(String args[])
    {
    	int[] meta = {100,100,100,100,100};
		Board board = new Board(meta);
		
		board.fillBoard();

		board.showBoard(1,10);
        //Interface window = new Interface(board);
        //window.showBoard(50);
    }
}