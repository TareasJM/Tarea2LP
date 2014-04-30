import javax.swing.*;
import java.awt.*;
import java.util.Random;
class JPanelExample extends JFrame
{
JPanel p1,p2;
Dimension d;

    public JPanelExample()
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
        setLayout(new GridLayout(15,15)); //FILA / COLUMNAS
        
        for (int i = 0; i<225; i++) {
           
           // An empty panel with FlowLayout
           p1=new JPanel();
           
           // Panel with custom layout
           //p2=new JPanel(new GridBagLayout());
           
           // Set some preferred size
           d=new Dimension(40,40);

           p1.add(new JLabel(Integer.toString(i)));

           p1.setPreferredSize(d);
           //p2.setPreferredSize(d);
           
           // Set some background
           p1.setBackground(RandomColor());
           //p2.setBackground(Color.DARK_GRAY);

           // Set some border
           // Here a line border of 5 thickness, dark gray color and rounded
           // edges
           p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
           
           // Set some tooltip text
           //p1.setToolTipText("Panel 1");
           //p2.setToolTipText("Panel 2");
           
           // Add panels
           add(p1);
           //add(p2);
        }
        //setSize(15,15);
        setVisible(true);
        
        // Pack the frame so that no/very little extra
        // space is visible
        pack();
        
    }
    
    public static void main(String args[])
    {
        new JPanelExample();
    }
}