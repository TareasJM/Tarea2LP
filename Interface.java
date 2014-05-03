import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

class Interface extends JFrame
{
    JPanel main;
    JPanel control;
	JPanel[] colorBlock;
	int row;
	int col;
	Board board;
	boolean paiting;

    public Interface()
    {
    	row = col = -1;
        paiting = true;
        setTitle("Candi crach");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(1,2));
        this.main = new JPanel();
        this.main.setLayout(new GridLayout(15,15)); //FILA / COLUMNAS
        colorBlock = new JPanel[225];
        for (int i = 0; i<225; i++) {               
            colorBlock[i] = new JPanel();
            colorBlock[i].setPreferredSize(new Dimension(30,30));
            colorBlock[i].setBackground(Color.WHITE);
            colorBlock[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
            colorBlock[i].setVisible(true);
            main.add(colorBlock[i]);

        }
        add(main);
        this.control = new JPanel();
        this.control.setLayout(new GridLayout(10,1));
        this.control.setSize(600,100);
        this.control.setBackground(Color.BLACK);
        this.control.setVisible(true);
        add(control);
        pack();
        setVisible(true);
        paiting = false;
    }

    public void updateBoard(Board board)
    {

        this.board = board;
        showBoard(10);

    }

    public void showBoard(int time)
    {   
        paiting = true;
    	int n = 0;
        for (int i = 0; i<15; i++) {
        	for (int j = 0; j<15; j++ ) {
                Bloque temp = board.getBlock(i,j);
                Color color = Color.WHITE;
                if (temp == null){
                    color = Color.WHITE;
                }

                else if( temp instanceof BloqueColor)
                {
                    BloqueColor temp2 = (BloqueColor)temp;
                    String colorN = temp2.getColor();
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
                    } 
                }
                else{
                    color = Color.PINK;    
                }
                colorBlock[n].setBackground(color);
                n++;
        	}
        	doLayout();
        }
        pack();

        try{
            Thread.sleep(time * 10);
        }catch ( InterruptedException e){
            System.out.print("No se puede esperar");
        }
        paiting = false;
        
    }
    
/*    public void handleClick(JPanel panel)
    {
        panel.addMouseListener(new MouseAdapter() 
        {   

            @Override
            public void mouseClicked(MouseEvent e) {
                if(paiting == false)
                {
                	e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
                    int newCol = e.getX()/40;
                    int newRow = e.getY()/40;
                    if((Math.abs(newCol-col) == 1 && newRow == row) || (Math.abs(newRow-row) == 1 && newCol == col)){
                        System.out.println("entro");
                    	board.moveBlock(row,col,newRow,newCol);
                    	row = col = -1;
                    }
                    else{
                    	row = newRow;
                    	col = newCol;
                    }
                }
             }

        });
    }*/
    
}
