import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WorldMap extends JFrame implements MouseMotionListener {

JPanel panel = new JPanel();
JLabel map = new JLabel();
Image image = Toolkit.getDefaultToolkit().getImage(
        getClass().getResource("fondo2"));
ImageIcon icon;

public WorldMap() {
    addMouseMotionListener(this);
    icon = new ImageIcon(image);
    map.setIcon(icon);
    panel.add(map);
    add(panel);
    setTitle("World Map");
    setSize(400, 400);
    setResizable(false);
    setVisible(true);
}

public static void main(String[] args) {
    new WorldMap();
}

public void mouseDragged(MouseEvent e) {
    e.translatePoint(e.getComponent().getLocation().x, e.getComponent()
            .getLocation().y);
    panel.setLocation(e.getX(), e.getY());
    repaint();
}

public void mouseMoved(MouseEvent e) {
}

}