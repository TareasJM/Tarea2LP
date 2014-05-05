import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



class Interface extends JFrame
{
    public JPanel main;
    public JPanel control;
    public JPanel blockPuntaje;
    public JLabel puntaje;
	public JPanel[] colorBlock;
    public JLabel[] meta;
    public String[] puntaje2; 
    public Color[] colores;
	public int row;
	public int col;
	public Board board;
	public boolean paiting;

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
            // handleClick(colorBlock[i]);
            main.add(colorBlock[i]);

        }
        this.meta = new JLabel[5];
        this.puntaje2 = new String[] {"R: ","B: ","O: ","G: ","Y: "};
        this.colores = new Color[] {Color.RED,Color.BLUE,Color.ORANGE,Color.GREEN,Color.YELLOW};
        add(main);
        this.control = new JPanel();
        this.control.setLayout(new GridLayout(6,11));
        this.control.setBackground(Color.WHITE);
        this.control.setVisible(true);
        add(control);
        for (int i=0;i<5;i++ ) {
            this.blockPuntaje = new JPanel();
            this.blockPuntaje.setBackground(this.colores[i]);
            this.blockPuntaje.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));          
            this.control.add(this.blockPuntaje);
            meta[i] = new JLabel(puntaje2[i]+"");
            this.blockPuntaje.add(meta[i]);
        }
        // JTextField ola = new JTextField();
        // this.control.add(ola);


        pack();
        setVisible(true);
        paiting = false;
    }

    public void updateBoard(Board board)
    {

        this.board = board;   
        showBoard();

    }

    public void showBoard()
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
        for (int i=0; i<5; i++) {
            meta[i].setText(puntaje2[i]+board.getMeta()[i]);
        }
        pack();
        paiting = false;
        
    }

    public void close(){
        this.dispose();
    }
    
    public void handleClick(JPanel panel)
    {
        panel.addMouseListener(new MouseAdapter() 
        {   

            @Override
            public void mouseClicked(MouseEvent e) {
                if(paiting == false)
                {
                	// e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
                 //    int newCol = e.getX()/40;
                 //    int newRow = e.getY()/40;
                 //    if((Math.abs(newCol-col) == 1 && newRow == row) || (Math.abs(newRow-row) == 1 && newCol == col)){
                 //        System.out.println("entro");
                 //    	board.moveBlock(row,col,newRow,newCol);
                 //    	row = col = -1;
                 //    }
                 //    else{
                 //    	row = newRow;
                 //    	col = newCol;
                 //    }
                    System.out.println("click");
                    return;
                }
             }

        });
    }       
    
}
