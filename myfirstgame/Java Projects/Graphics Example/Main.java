import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

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
    static JFrame frame;
    static DrawPanel drawPanel;

    private Heart[] hearts;

    // The width and height of the canvas
    private int width = 600;
    private int height = 600;
    
    public static void main(String[] args) throws IOException {
        new Main().init();
    }

    private void init() throws IOException {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        int index = 0;
        int size = 5;
        double sizeRatio = (width / size);

        hearts = new Heart[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                hearts[index++] = new Heart(i * sizeRatio + sizeRatio / 4, j * sizeRatio + sizeRatio / 4);
            }
        }

        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.pack();
        loop();
    }

    private void loop() {
        while (true) {
            for (Heart heart : hearts) {
                heart.update();
                heart.checkForWalls(width, height);
                heart.checkForHearts(hearts);
            }

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            frame.repaint();
        }
    }

    class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public DrawPanel() {
            setOpaque(true);
            setBackground(Color.WHITE);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (Heart heart : hearts) {
                heart.draw(g);
            }
        }
    }
}