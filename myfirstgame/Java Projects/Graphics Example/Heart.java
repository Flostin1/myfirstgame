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

    private Vector position;
    private Vector velocity;

    private int diameter = 50;
    private double angle = 0;
    private double angularVelocity = 0;

    public Heart(double x, double y) {
        double theta = ThreadLocalRandom.current().nextDouble(0, Math.PI * 2);
        double magnitude = ThreadLocalRandom.current().nextDouble(5, 8);

        position = new Vector(x, y);
        velocity = new Vector(Math.cos(theta) * magnitude, Math.sin(theta) * magnitude);

        //this.diameter = ThreadLocalRandom.current().nextInt(40, 60);
        this.angularVelocity = ThreadLocalRandom.current().nextDouble(Math.PI / 35, Math.PI / 15);

        System.out.println(ANSI_GREEN + String.format("Direction: %f, Magnitude: %f", theta, magnitude) + ANSI_RESET);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(angle, position.x + diameter / 2, position.y + diameter / 2);
        g2d.drawImage(heart, (int)position.x, (int)position.y, diameter, diameter, null);
        g2d.rotate(-angle, position.x + diameter / 2, position.y + diameter / 2);
    }

    public void update() {
        position.add(velocity);
        angle += angularVelocity;
    }

    public void checkForWalls(int width, int height) {
        if (position.x > width - diameter || position.x < 0) {
            velocity.x *= -1;
        }

        if (position.y > height - diameter || position.y < 0) {
            velocity.y *= -1;
        }
    }

    private void heartCollision(Heart otherHeart) {
        Vector otherPosition = otherHeart.getPosition();
        Vector otherVelocity = otherHeart.getVelocity();

        if (Math.sqrt(Math.pow((position.x - otherPosition.x), 2) + Math.pow((position.y - otherPosition.y), 2)) < 50) {
            Vector firstVelocity = velocity.get();

            velocity.set(otherVelocity.get());
            otherVelocity.set(firstVelocity);
        }
    }

    public void checkForHearts(Heart[] hearts) {
        for (int i = 0; i < hearts.length; i++) {
            if (this != hearts[i]) {
                this.heartCollision(hearts[i]);
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

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector v) {
        velocity.set(v);
    }

    /*public Circle getCircle() {
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
    }*/
}