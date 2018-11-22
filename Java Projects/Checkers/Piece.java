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
    private static int size = 60;

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
    private static BufferedImage[] pieceImages = new BufferedImage[]{
        Main.loadImage("Red_Checker.png"),
        Main.loadImage("Black_Checker.png")
    };

    // Creates the pieces for the game
    public static Piece[] generatePieces() {
        Piece[] pieces = new Piece[24];

        int index = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                int piece = board[y][x];

                if (piece == 1 || piece == 2) {
                    pieces[index++] = new Piece(x * 75 + 7, y * 75 + 7, piece - 1);
                }
            }
        }

        return pieces;
    }

    public static void drawPieces(Piece[] pieces, Graphics2D g2d) {
        for (Piece piece : pieces) {
            g2d.drawImage(pieceImages[piece.team], piece.x, piece.y, size, size, null);
        }
    }

    public int x;
    public int y;
    public final int team;

    public Piece(int x, int y, int team) {
        this.x = x;
        this.y = y;
        this.team = team;
    }
}