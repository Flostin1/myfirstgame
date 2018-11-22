/**
 * Credits:
 * * Checker Board Image: https://www.souvnear.com/mnt-storage/products/Amritsar/FD-ASR-015/2.jpg
 * * Checker Pieces: https://studio.code.org/projects/applab/qOrtceIfe4F4g3q5NDoLfm0GYaN2iuIJL0rN4cA_-hY
 */

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

public class Main {
    private static JFrame frame;
    private static DrawPanel canvas;

    // The width and height of the canvas
    private int width = 600;
    private int height = 600;

    private Piece[] checkerPieces;

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
        checkerPieces = Piece.generatePieces();

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
                Thread.sleep(10);
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

    class DrawPanel extends JPanel {
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
            Piece.drawPieces(checkerPieces, g2d);
        }
    }
}