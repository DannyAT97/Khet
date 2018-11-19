package chessgui;

import chessgui.pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JComponent {
        
    public int turnCounter = 0;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    private final int Square_Width = 84;
    private final double Square_Height = 81;

    
    public ArrayList<Piece> redPieces;
    public ArrayList<Piece> whitePieces;
    

    public ArrayList<DrawingShape> Static_Shapes;
    public ArrayList<DrawingShape> Piece_Graphics;

    public Piece Active_Piece;

    private final int rows = 8;   
    private final int cols = 10;
    private Integer[][] BoardGrid;
    private String board_file_path = "images" + File.separator + "khet_board.png";
    private String active_square_file_path = "images" + File.separator + "active_square_1.png";

    public void initGrid()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                BoardGrid[i][j] = 0;
            }
        }

        //Image white_piece = loadImage("images/white_pieces/" + piece_name + ".png");
        //Image black_piece = loadImage("images/black_pieces/" + piece_name + ".png"); 
        
        redPieces.add(new King(0,3,true,"redPyramid0.png",this));
        redPieces.add(new King(0,4,true,"redPyramid90.png",this));
        
        redPieces.add(new King(2,1,true,"redPyramid180.png",this));
        
        redPieces.add(new King(4,0,true,"redObelisk0.png",this));
        redPieces.add(new King(4,3,true,"redDjed180.png",this));
        
        redPieces.add(new King(5,0,true,"redPharaoh0.png",this));
        redPieces.add(new King(5,3,true,"redDjed270.png",this));
        
        redPieces.add(new King(6,0,true,"redObelisk0.png",this));
        redPieces.add(new King(6,5,true,"redPyramid90.png",this));
        
        redPieces.add(new King(7,0,true,"redPyramid90.png",this));
        redPieces.add(new King(7,3,true,"redPyramid90.png",this));
        redPieces.add(new King(7,4,true,"redPyramid0.png",this));
        
        
        
        whitePieces.add(new King(2,3,false,"whitePyramid180.png",this));
        whitePieces.add(new King(2,4,false,"whitePyramid270.png",this));
        whitePieces.add(new King(2,7,false,"whitePyramid270.png",this));
        
        whitePieces.add(new King(3,2,false,"whitePyramid270.png",this));
        whitePieces.add(new King(3,7,false,"whiteObelisk0.png",this));
        
        whitePieces.add(new King(4,4,false,"whiteDjed90.png",this));
        whitePieces.add(new King(4,7,false,"whitePharaoh0.png",this));
        
        whitePieces.add(new King(5,4,false,"whiteDjed0.png",this));
        whitePieces.add(new King(5,7,false,"whiteObelisk0.png",this));
        
        whitePieces.add(new King(7,6,false,"whitePyramid0.png",this));
        whitePieces.add(new King(9,3,false,"whitePyramid270.png",this));
        whitePieces.add(new King(9,4,false,"whitePyramid180.png",this));
        

    }

    public Board() {

        BoardGrid = new Integer[rows][cols];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        redPieces = new ArrayList();
        whitePieces = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(840, 650));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));

        this.addMouseListener(mouseAdapter);
        this.addComponentListener(componentAdapter);
        this.addKeyListener(keyAdapter);


        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }


    private void drawBoard()
    {
        Piece_Graphics.clear();
        Static_Shapes.clear();
        //initGrid();
        
        Image board = loadImage(board_file_path);
        Static_Shapes.add(new DrawingImage(board, new Rectangle2D.Double(0, 0, board.getWidth(null), board.getHeight(null))));
        
        if (Active_Piece != null)
        {
            Image active_square = loadImage("images" + File.separator + "active_square.png");
            Static_Shapes.add(new DrawingImage(active_square, new Rectangle2D.Double(Square_Width*Active_Piece.getX(),Square_Height*Active_Piece.getY(), active_square.getWidth(null), active_square.getHeight(null))));
        }
        for (int i = 0; i < redPieces.size(); i++)
        {
            int COL = redPieces.get(i).getX();
            int ROW = redPieces.get(i).getY();
            Image piece = loadImage("images" + File.separator + "pieces_red" + File.separator + redPieces.get(i).getFilePath());  
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(Square_Width*COL,Square_Height*ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        for (int i = 0; i < whitePieces.size(); i++)
        {
            int COL = whitePieces.get(i).getX();
            int ROW = whitePieces.get(i).getY();
            Image piece = loadImage("images" + File.separator + "pieces_white" + File.separator + whitePieces.get(i).getFilePath());  
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(Square_Width*COL,Square_Height*ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        this.repaint();
    }

    
    public Piece getPiece(int x, int y) {
        for (Piece p : redPieces)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        for (Piece p : whitePieces)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        return null;
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {

                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int d_X = e.getX();
            int d_Y = e.getY();  
            int Clicked_Row = d_Y / Square_Width;
            int Clicked_Column = d_X / Square_Width;
            boolean is_whites_turn = true;
            if (turnCounter%2 == 1)
            {
                is_whites_turn = false;
            }
            
            Piece clicked_piece = getPiece(Clicked_Column, Clicked_Row);
            
            if (Active_Piece == null && clicked_piece != null && 
                    ((is_whites_turn && clicked_piece.isWhite()) || (!is_whites_turn && clicked_piece.isBlack())))
            {
                Active_Piece = clicked_piece;
            }
            else if (Active_Piece != null && Active_Piece.getX() == Clicked_Column && Active_Piece.getY() == Clicked_Row)
            {
                Active_Piece = null;
            }
            else if (Active_Piece != null && Active_Piece.canMove(Clicked_Column, Clicked_Row) 
                    && ((is_whites_turn && Active_Piece.isWhite()) || (!is_whites_turn && Active_Piece.isBlack())))
            {
                // if piece is there, remove it so we can be there
                if (clicked_piece != null)
                {
                    if (clicked_piece.isWhite())
                    {
                        redPieces.remove(clicked_piece);
                    }
                    else
                    {
                        whitePieces.remove(clicked_piece);
                    }
                }
                // do move
                Active_Piece.setX(Clicked_Column);
                Active_Piece.setY(Clicked_Row);
                
                // if piece is a pawn set has_moved to true
                if (Active_Piece.getClass().equals(Pawn.class))
                {
                    Pawn castedPawn = (Pawn)(Active_Piece);
                    castedPawn.setHasMoved(true);
                }
                
                
                Active_Piece = null;
                turnCounter++;
            }
            
            drawBoard();
        }

        @Override
        public void mouseDragged(MouseEvent e) {		
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
        }	

        
    };

    private void adjustShapePositions(double dx, double dy) {

        Static_Shapes.get(0).adjustPosition(dx, dy);
        this.repaint();

    } 
        
        
      
    private Image loadImage(String imageFile) {
        try {
                return ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
                return NULL_IMAGE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawBackground(g2);
        drawShapes(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(getBackground());
        g2.fillRect(0,  0, getWidth(), getHeight());
    }
       

    private void drawShapes(Graphics2D g2) {
        for (DrawingShape shape : Static_Shapes) {
            shape.draw(g2);
        }	
        for (DrawingShape shape : Piece_Graphics) {
            shape.draw(g2);
        }
    }

    private ComponentAdapter componentAdapter = new ComponentAdapter() {

        @Override
        public void componentHidden(ComponentEvent e) {

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentResized(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }	
    };

    private KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }	
    };

}



interface DrawingShape {
    boolean contains(Graphics2D g2, double x, double y);
    void adjustPosition(double dx, double dy);
    void draw(Graphics2D g2);
}


class DrawingImage implements DrawingShape {

    public Image image;
    public Rectangle2D rect;

    public DrawingImage(Image image, Rectangle2D rect) {
            this.image = image;
            this.rect = rect;
    }

    @Override
    public boolean contains(Graphics2D g2, double x, double y) {
            return rect.contains(x, y);
    }

    @Override
    public void adjustPosition(double dx, double dy) {
            rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());	
    }

    @Override
    public void draw(Graphics2D g2) {
            Rectangle2D bounds = rect.getBounds2D();
            g2.drawImage(image, (int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMaxY(),
                                            0, 0, image.getWidth(null), image.getHeight(null), null);
    }	
}
