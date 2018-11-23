import java.lang.Math;

import java.awt.event.MouseEvent;

public class CurrentTile {
    private static int value = -1;
    private static int x;
    private static int y;

    public static void onMouseExit() {
        x = y = Integer.MAX_VALUE;
        value = -1;
    }

    public static void onMouseMoved(MouseEvent e) {
        x = (int)Math.floor(e.getX() / 75);
        y = (int)Math.floor(e.getY() / 75);
        value = Piece.getBoardData(x, y);
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