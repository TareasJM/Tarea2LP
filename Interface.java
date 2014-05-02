import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
              handleDrag(p1);
	           	bloque++;
           }
           for (int k=0; k<5; k++) {
           		p1=new JPanel();
	           	d=new Dimension(40,40);
              JLabel labbel = new JLabel("Punt");
              p1.setPreferredSize(d);
              if(k == 2 && i == 1)
              {
                p1.setBackground(Color.RED);
                p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
                p1.add(new JLabel("R"));

              }
              else if(k == 2 && i == 4)
              {
                p1.setBackground(Color.BLUE);
                p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
                p1.add(new JLabel("B"));
              }
              else if(k == 2 && i == 7)
              {
                p1.setBackground(Color.ORANGE);
                p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
                p1.add(new JLabel("O"));
              }
              else if(k == 2 && i == 10)
              {
                p1.setBackground(Color.GREEN);
                p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
                p1.add(new JLabel("G"));
              }
              else if(k == 2 && i == 13)
              {
                p1.setBackground(Color.YELLOW);
                p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
                p1.add(new JLabel("Y"));
              }
              else if(k == 2 && i%2 == 0)
              {
                labbel.setLocation(4,18);
                p1.add(labbel);
              }

              
	           	add(p1);

           }
           
        }

        setVisible(true);
        pack();
          
    }

    public void handleDrag(JPanel panel)
    {
        final JPanel p = panel;
        panel.addMouseMotionListener(new MouseMotionAdapter() 
        {   

            @Override
            public void mouseDragged(MouseEvent me) 
            {   
                if(me.getComponent().getLocation().y == me.getY())
                {
                me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
                p.setLocation(me.getX(), me.getY());
                System.out.println("PANEL dragged (" + p.getLocation() + ',' + p.getY() + ')');            
                System.out.println("Mouse dragged (" + me.getX() + ',' + me.getY() + ')');            
                }
            }

        });
  }
    public void mouseMoved(MouseEvent e) 
    {

    }
    
    public static void main(String args[])
    {
        new Interface();
    }
}