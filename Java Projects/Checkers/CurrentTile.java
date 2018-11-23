import java.lang.Math;

import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.event.MouseEvent;

public class CurrentTile {
    private static int value = -1;
    private static int x;
    private static int y;

    private static Color semiGreen = new Color(0, 255, 0, 100);

    public static void onMouseExit() {
        x = y = Integer.MAX_VALUE;
        value = -1;
    }

    public static void onMouseMoved(MouseEvent e) {
        x = (int)Math.floor(e.getX() / 75);
        y = (int)Math.floor(e.getY() / 75);
        value = Piece.getBoardData(x, y);
    }

    public static void drawSelector(Graphics2D g2d) {
        if (value > 0) {
            g2d.setColor(semiGreen);
            g2d.fillOval(x * 75 + 7, y * 75 + 7, 60, 60);
        }
    }

    public static void printData() {
        System.out.println(String.format("The mouse is at: (%d, %d)", x, y));
        System.out.println(String.format("The current piece selected is: %d", value));
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getValue() {
        return value;
    }
}