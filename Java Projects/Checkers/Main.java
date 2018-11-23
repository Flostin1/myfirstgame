/**
 * How it'll work:
 * * The game starts at red's turn and welcomes the two players (display messages).
 * * The user may push 'R' to open up the rulebook.
 * * If the user clicks on their piece, select it and display possible places it may go to (how to do this?)
 * 
 * Credits:
 * * Checker Board Image: https://www.souvnear.com/mnt-storage/products/Amritsar/FD-ASR-015/2.jpg (Change this link)
 * * Checker Pieces: https://studio.code.org/projects/applab/qOrtceIfe4F4g3q5NDoLfm0GYaN2iuIJL0rN4cA_-hY
 */

import java.lang.Math;

import java.io.File;
import java.io.IOException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Main {
    private static JFrame frame;
    private static DrawPanel canvas;
    private static MouseHandler mouseHandler;

    // The width and height of the canvas
    private int width = 600;
    private int height = 600;

    //Game variables
    private String turn = "Red";

    // Image files...
    private BufferedImage checkerBoard = loadImage("Checker_Board.jpg");

    public static void main(String[] args) throws IOException {
        new Main().init();
    }

    // Sets up the canvas and puts it into a JFrame
    private void init() throws IOException {
        frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new DrawPanel();
        mouseHandler = new MouseHandler();

        frame.getContentPane().add(BorderLayout.CENTER, canvas);

        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.pack();
        loop();
    }

    // Code to repeat...
    private void loop() {
        while (true) {
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }

            frame.repaint();
        }
    }

    // This function takes a filename and returns a BufferedImage
    public static BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("File '" + filename + "' not found.");
        }

        return null;
    }

    private class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        // Sets up the canvas
        public DrawPanel() {
            setOpaque(true);
            setBackground(Color.WHITE);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }

        @Override
        // Code to be drawn...
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;

            super.paintComponent(g);
            g2d.drawImage(checkerBoard, 0, 0, width, height, null);

            Piece.drawPieces(g2d);
            CurrentTile.drawSelector(g2d);
        }
    }

    // This class is responsible for listening for the user's mouse
    private class MouseHandler extends MouseAdapter implements MouseListener, MouseMotionListener {
        public MouseHandler() {
            canvas.addMouseListener(this);
            canvas.addMouseMotionListener(this);
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println("The mouse has entered.");
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println("The mouse has been clicked.");
        }

        public void mouseExited(MouseEvent e) {
            CurrentTile.onMouseExit();

            System.out.println("The mouse has exited.");
            CurrentTile.printData();
        }

        public void mouseMoved(MouseEvent e) {
            CurrentTile.onMouseMoved(e);
            CurrentTile.printData();
        }
    }
}