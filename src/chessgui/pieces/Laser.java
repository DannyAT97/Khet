package chessgui.pieces;
import java.util.*;
import javax.swing.*;
/**
 * A Class to represent the laser in the game of khet
 */
public class Laser extends JComponent {
	
    private final int[] whiteStartPos = new int[]{7, 9};
    private final int whiteStartDirection = 0;

    private final int[] redStartPos = new int[]{0, 0};
    private final int redStartDirection = 180;

    protected String laserDetails = "No Details";
    protected String effect = "";
    protected int lastDirection;
    protected int[] lastHit;
    
    protected boolean showLaser = false;

    public String getDetails() {
        return laserDetails;
    }

    /**
     * method to toggle showing the laser 
     */
    public void toggleShowLaser() {
        if (showLaser) {
            this.setVisible(true);
            showLaser = false;
        } else {
            this.setVisible(false);
            showLaser = true;
        }
    }

    /**
     * gets a list of tiles the laser moves across
     * 
     * @param board the game board
     * @param team a string representing the team name
     * @return ArrayList of int[]'s arrays representing traversed points
     */
    public ArrayList<int[]> getTraversedTiles(GamePiece board[][], String team) {
        if (team.equals("white"))
            return genTraversedTiles(board, whiteStartPos, whiteStartDirection);
        else
            return genTraversedTiles(board, redStartPos, redStartDirection);
    }

    /**
     * generates a list of traversed tiles internally for this class
     * @param board the game board
     * @stPt the starting point of the laser
     * @return ArrayList of int[]'s representing traversed points
     */
    private ArrayList<int[]> genTraversedTiles(GamePiece board[][], int[] stPt, int stDir){
        int[] piece = new int[]{stPt[0],stPt[1]};
        int laserDirection = stDir, newDirection = stDir;
        ArrayList<int[]> tiles = new ArrayList<int[]>();

        while(!(piece[0] > 7 || piece[1] > 9 || piece[0] < 0 || piece[1] < 0) && (laserDirection >= 0)) {
            if(board[piece[0]][piece[1]] != null) {
                newDirection = board[piece[0]][piece[1]].getNewDirection(laserDirection);
            }
            laserDirection = newDirection;
            tiles.add(new int[]{piece[0],piece[1]});
            piece = step(piece, laserDirection);
        }

        lastDirection = laserDirection;
        
        if (piece[0] > 7 || piece[1] > 9 || piece[0] < 0 || piece[1] < 0) {
            laserDetails = "Laser Missed, out of bounds";
            effect = "missed";
        }
        if (newDirection == -1) {
            laserDetails = "Laser destroyed a "
            + board[piece[0]][piece[1]].team + " "
            + board[piece[0]][piece[1]].name;
            effect = "hit piece";
            lastHit = new int[]{piece[0],piece[1]};
        }
        if (newDirection == -5) {
            laserDetails = 
			"Something went wrong with the laser: ERROR returned -5";
            effect = "error";
        }
        return tiles;
    } 

    /**
     * method to step the laser a tile in its current direction
     * @param p point to change
     * @param direction direction to move
     * @return the stepped point
     */
    private int[] step(int[] p, int direction) {
        if (direction == 0) { // north
            p[0] = p[0]-1;
            p[1] = p[1];
        }
        if (direction == 90) { //east
            p[0] = p[0];
            p[1] = p[1]+1;
        }
        if (direction == 180) { //south
            p[0] = p[0]+1;
            p[1] = p[1];
        }
        if (direction == 270) { //west
            p[0] = p[0];
            p[1] = p[1]-1;
        }
        return p;
    }
}
