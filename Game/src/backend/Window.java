package backend;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @author David
 */
public class Window extends Canvas{
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
        game.start();
    }
}
