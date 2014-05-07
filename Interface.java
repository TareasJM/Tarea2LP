import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class Interface extends JFrame
{
    public JPanel main;
    public JPanel control;
    public JPanel blockPuntaje;
    public JLabel puntaje;
    public JBackgroundPanel[] colorBlock;
    public JLabel[] meta;
    public String[] puntaje2; 
    public BufferedImage[] colores;
	public int row;
	public int col;
	public Board board;
	public boolean paiting;
    JLabel caca;

    public Interface()
    {
    	row = col = -1;
        paiting = true;
        setTitle("Chancho Crach");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(1,2));
        this.main = new JPanel();
        this.main.setLayout(new GridLayout(15,15)); //FILA / COLUMNAS
        colorBlock = new JBackgroundPanel[225];

        for (int i = 0; i<225; i++) {               
            colorBlock[i] = new JBackgroundPanel();
            colorBlock[i].setPreferredSize(new Dimension(30,30));
            colorBlock[i].setColor(0);
            colorBlock[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
            colorBlock[i].setVisible(true);
            main.add(colorBlock[i]);

        }
        this.meta = new JLabel[5];
        this.puntaje2 = new String[] {"R: ","B: ","O: ","G: ","Y: "};
        colores = new BufferedImage[7];
        for (int i = 0;i<7 ;i++ ) {
            try{
                colores[i]=ImageIO.read(new File("./resources/"+i+".png"));
            }catch(Exception e){
                 e.printStackTrace();
            }
        }
        add(main);
        this.control = new JPanel();
        this.control.setLayout(new GridLayout(6,11));
        this.control.setBackground(Color.WHITE);
        this.control.setVisible(true);
        add(control);
        Color[] bgc = new Color[] {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN, Color.YELLOW};
        for (int i=0;i<5;i++ ) {
            this.blockPuntaje = new JPanel();
            this.blockPuntaje.setBackground(bgc[i]);
            this.blockPuntaje.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));          
            this.control.add(this.blockPuntaje);
            meta[i] = new JLabel(puntaje2[i]+"");
            this.blockPuntaje.add(meta[i]);
        }

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
                int color = 6;
                if (temp == null){
                    color = 6;
                }
                else if( temp instanceof BloqueColor)
                {
                    BloqueColor temp2 = (BloqueColor)temp;
                    String colorN = temp2.getColor();
                    if (colorN.equals("R")) {
                        color = 0;
                    } else if (colorN.equals("B")) {
                        color = 1;                 
                    } else if (colorN.equals("O")) {
                        color = 2;                   
                    } else if (colorN.equals("G")) {
                        color = 3;                    
                    } else if (colorN.equals("Y")) {
                        color = 4;                   
                    } 
                }
                else{
                    color = 5;    
                }
                if (colorBlock[n].getColor() != color) {
                    colorBlock[n].setColor(color);
                    colorBlock[n].setBackgroundPanel(colores[color]);
                    colorBlock[n].paintComponent(colorBlock[n].getGraphics());
                    colorBlock[n].setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
                }
                n++;
        	}
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
    

    public class JBackgroundPanel extends JPanel {
		private BufferedImage img;
		private int color;

		public void setBackgroundPanel(BufferedImage img) {
			this.img = img;
		}

		public int getColor(){
			return this.color;
		}

		public void setColor(int color){
			this.color = color;
		}

		@Override
			protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, 30, 30, null);
		}
    }

}