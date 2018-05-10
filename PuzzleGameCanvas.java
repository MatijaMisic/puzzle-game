package puzzle.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Matija
 */
public class PuzzleGameCanvas extends JFrame {

    JLabel grid, blueCube, blackCube, greenCube, redCube;
    Point initialClick;
    MouseListener ml;
    MouseMotionListener motL;
    ArrayList<JLabel> myList = new ArrayList<JLabel>();

    public PuzzleGameCanvas() {

        // seting JFrame
        setLayout(new BorderLayout());
        setSize(800, 600);
        setTitle("Puzzle Game");

        //instantiating elements
        grid = new JLabel();
        blueCube = new JLabel();
        blackCube = new JLabel();
        greenCube = new JLabel();
        redCube = new JLabel();

        //adding elements to the list
        myList.add(blueCube);
        myList.add(blackCube);
        myList.add(greenCube);
        myList.add(redCube);

        //seting elemnts position and size
        grid.setBounds(260, 40, 250, 250);
        blueCube.setBounds(20, 40, 150, 150);
        blackCube.setBounds(80, 300, 100, 100);
        greenCube.setBounds(240, 350, 100, 150);
        redCube.setBounds(400, 350, 150, 100);

        //adding images to the elements
        grid.setIcon(new ImageIcon("images/grid.jpg"));
        blueCube.setIcon(new ImageIcon("images/blueCube.jpg"));
        blackCube.setIcon(new ImageIcon("images/blackCube.jpg"));
        greenCube.setIcon(new ImageIcon("images/greenCube.jpg"));
        redCube.setIcon(new ImageIcon("images/redCube.jpg"));

        //instantiating mouseListener and mouseMotionListener
        myMouseListener();
        MyMouseMotionListener();

        //adding mouse motion listener to the element
        blueCube.addMouseListener(ml);
        blueCube.addMouseMotionListener(motL);
        blackCube.addMouseListener(ml);
        blackCube.addMouseMotionListener(motL);
        greenCube.addMouseListener(ml);
        greenCube.addMouseMotionListener(motL);
        redCube.addMouseListener(ml);
        redCube.addMouseMotionListener(motL);

        //adding elements  to JFrame on specific z-depth
        add(blueCube, -1);
        add(blackCube, -1);
        add(greenCube, -1);
        add(redCube, -1);
        add(grid);

        setLayout(null); //canceling default layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close window

        setLocationRelativeTo(null); // canceling defaulut  float layout
        setVisible(true); //seting JFrame visual

        //welcome dialog box
        JOptionPane.showMessageDialog(null, "Welcome to the Puzzle Game! \n "
                + "The main goal is to place the colored objects into the grid.");

    }

    public MouseMotionListener MyMouseMotionListener() {
        motL = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                //getting the draggable object and checking position in relation to the mouse
                Object source = e.getSource();

                if (source instanceof JLabel) {

                    JLabel jLabelMoved = (JLabel) source;

                    int mouseX = jLabelMoved.getLocation().x;
                    int mouseY = jLabelMoved.getLocation().y;

                    int xMoved = (mouseX + e.getX()) - (mouseX + initialClick.x);
                    int yMoved = (mouseY + e.getY()) - (mouseY + initialClick.y);

                    int X = mouseX + xMoved;
                    int Y = mouseY + yMoved;

                    //setting the new position of the draggable element
                    jLabelMoved.setLocation(X, Y);

                    //setting borders of the movable element
                    int new_x = jLabelMoved.getLocation().x;
                    int new_y = jLabelMoved.getLocation().y;

                    int new_w = jLabelMoved.getWidth();
                    int new_h = jLabelMoved.getHeight();

                    if ((new_x + new_w) > (getWidth() - 20)) {
                        new_x = (getWidth() - 20) - new_w;
                    }
                    if (new_x < 0) {
                        new_x = 0;
                    }
                    if ((new_y + new_h) > (getHeight() - 40)) {
                        new_y = (getHeight() - 40) - new_h;
                    }
                    if (new_y < 0) {
                        new_y = 0;
                    }

                    jLabelMoved.setLocation(new_x, new_y);

                    //setting movable object to the new layer
                    add(jLabelMoved, 0);
                    jLabelMoved.repaint();
                }

            }

            ;

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
        return motL;
    }

    public MouseListener myMouseListener() {

        ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                //iterating through the list to check location on each element
                int iterator = 0;
                for (JLabel lbl : myList) {
                    if (lbl.getLocation().x < 511 - lbl.getWidth() && lbl.getLocation().x > 259 && lbl.getLocation().y > 39 && lbl.getLocation().y < 291) {
                        iterator++;
                    }
                }

                //showing dialog box when each element is in the grid
                if (iterator == 4) {
                    DialogBox();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        return ml;
    }

    public void Restart() {
        //restarting location of each element
        blueCube.setLocation(20, 40);
        blackCube.setLocation(80, 300);
        greenCube.setLocation(240, 350);
        redCube.setLocation(400, 350);
    }

    public void DialogBox() {

        //winning dialog box
        int response = -1;

        response = JOptionPane.showConfirmDialog(null, "Well, done!Play again?", "Wohhoo you did it", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (response == JOptionPane.YES_OPTION) {
            Restart();
        }
    }
}
