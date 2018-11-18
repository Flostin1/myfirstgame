import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

import java.io.File;
import java.io.IOException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Heart {
    private static final BufferedImage heart = getImage("Pixel Heart.png");
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private double xPosition = 200;
    private double yPosition = 200;
    private int diameter = 50;

    private double xSpeed = 0;
    private double ySpeed = 0;

    private double angle = 0;
    private double angularVelocity = 0;

    public Heart(double xPosition, double yPosition) {
        double theta = ThreadLocalRandom.current().nextDouble(0, Math.PI * 2);
        double magnitude = ThreadLocalRandom.current().nextDouble(5, 8);

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.diameter = ThreadLocalRandom.current().nextInt(40, 60);

        this.xSpeed = Math.cos(theta) * magnitude;
        this.ySpeed = Math.sin(theta) * magnitude;
        this.angularVelocity = ThreadLocalRandom.current().nextDouble(Math.PI / 35, Math.PI / 15);

        System.out.println(ANSI_GREEN + String.format("Direction: %f, Magnitude: %f", theta, magnitude) + ANSI_RESET);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(angle, xPosition + diameter / 2, yPosition + diameter / 2);
        g2d.drawImage(heart, (int)xPosition, (int)yPosition, diameter, diameter, null);
        g2d.rotate(-angle, xPosition + diameter / 2, yPosition + diameter / 2);
    }

    public void update() {
        xPosition += xSpeed;
        yPosition += ySpeed;
        angle += angularVelocity;
    }

    public void checkForWalls(int width, int height) {
        if (xPosition > width - diameter || xPosition < 0) {
            xSpeed *= -1;
        }

        if (yPosition > height - diameter || yPosition < 0) {
            ySpeed *= -1;
        }
    }

    private void heartCollision(Heart firstHeart, Heart secondHeart) {
        Circle firstCircle = firstHeart.getCircle();
        Circle secondCircle = secondHeart.getCircle();

        if (firstCircle.intersects(secondCircle)) {
            //firstSpeed = 
        }
    }

    public void checkForHearts(Heart[] hearts) {
        for (int i = 0; i < hearts.length; i++) {
            for (int j = 0; j < hearts.length; j++) {
                if (i != j) {
                    heartCollision(hearts[i], hearts[j]);
                }
            }
        }
    }

    private static BufferedImage getImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("File '" + filename + "' not found.");
        }

        return null;
    }

    public void setSpeed(double xSpeed, double ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Circle getCircle() {
        return new Circle(xPosition, yPosition, diameter / 2);
    }

    private class Circle {
        public double x = 0;
        public double y = 0;
        public double radius = 0;

        public Circle(double x, double y, double radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public void setPosition(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public boolean intersects(Circle otherCircle) {
            double horizontalSquare = Math.pow((this.x - otherCircle.x), 2);
            double verticalSquare = Math.pow((this.y - otherCircle.y), 2);
                        
            return Math.sqrt(horizontalSquare + verticalSquare) < (this.radius + otherCircle.radius);
        }
    }
}