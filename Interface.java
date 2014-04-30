import javax.swing.*;
import java.awt.*;
import java.util.Random;
class Interface extends JFrame
{
JPanel p1,p2;
Dimension d;

    public Interface()
    {
        createAndShowGUI();
    }

    public Color RandomColor()
    { 
      Random randomGenerator = new Random();
      int randomInt = randomGenerator.nextInt(100);

      if (randomInt < 19)
      {
         return Color.RED;
      }
      else if (randomInt < 39)
      {
         return Color.BLUE;
      }
      else if (randomInt < 59) {
         return Color.ORANGE;
      }
      else if (randomInt < 79) {
         return Color.GREEN;
      }
      return Color.YELLOW;
    }
    
    private void createAndShowGUI()
    {
        setTitle("JPanel Example in Java");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(15,20)); //FILA / COLUMNAS
        int bloque = 1;

        for (int i = 0; i<15; i++) {
           
           for (int j=0; j<15; j++) {
           		p1=new JPanel();
	           	d=new Dimension(40,40);
	           	p1.add(new JLabel(Integer.toString(bloque)));
	           	p1.setPreferredSize(d);
	           	p1.setBackground(RandomColor());
	           	p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
	           	add(p1);
	           	bloque++;
           }
           for (int k=0; k<5; k++) {
           		p1=new JPanel();
	           	d=new Dimension(40,40);
	           	p1.setPreferredSize(d);
	           	add(p1);
	           	JTextArea textArea = new JTextArea(5, 30);
				JScrollPane scrollPane = new JScrollPane(textArea);
				setLayout.add(scrollPane, BorderLayout.CENTER);
           }
           
        }
        //setSize(15,15);
        setVisible(true);
        
        // Pack the frame so that no/very little extra
        // space is visible
        pack();
        
    }
    
    public static void main(String args[])
    {
        new Interface();
    }
}