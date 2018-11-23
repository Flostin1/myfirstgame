import java.lang.Math;

import java.io.File;
import java.io.IOException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Piece {
    private static int size = 60;
    private static Piece currentPiece = null;

    // Checker pieces...
    private static BufferedImage[] pieceImages = new BufferedImage[]{
        Main.loadImage("Red_Checker.png"),
        Main.loadImage("Black_Checker.png")
    };

    // This stores the positions of the pieces on the board
    private static int[][] board = new int[][]{
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 2, 0, 2, 0, 2, 0, 2},
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 1}
    };

    public Piece(int x, int y, int value) {

    }

    public static void drawPieces(Graphics2D g2d) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                int piece = board[y][x];

                if (piece == 1 || piece == 2) {
                    g2d.drawImage(pieceImages[piece - 1], x * 75 + 7, y * 75 + 7, size, size, null);
                }
            }
        }
    }

    public static int[][] getBoard() {
        return board;
    }

    // Returns the value of a given position on the board
    public static int getBoardData(int x, int y) {
        // Returns the value if the given position is within the board
        if ((x > -1 || x < 8) && (y > -1 || x < 8)) {
            return board[y][x];
        } else {
            return -1;
        }
    }
}