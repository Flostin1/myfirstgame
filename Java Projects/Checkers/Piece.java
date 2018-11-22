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

public class Piece {
    // This stores the positions of the pieces on the board
    private static int[][] board = new int[][]{
        {0, 2, 0, 2, 0, 2, 0, 2},
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 2, 0, 2, 0, 2, 0, 2},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0}
    };

    // Checker pieces...
    private static BufferedImage redChecker = Main.loadImage("Red_Checker.png");
    private static BufferedImage blackChecker = Main.loadImage("Black_Checker.png");

    public static Piece[] generatePieces() {
        Piece[] pieces = new Piece[24];

        int index = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                int piece = board[y][x];

                if (piece == 1 || piece == 2) {
                    pieces[index++] = new Piece(x * 40, y * 40, piece);
                }
            }
        }

        return pieces;
    }

    private int x;
    private int y;
    private int team;

    public Piece(int x, int y, int team) {
        this.x = x;
        this.y = y;
        this.team = team;
    }
}