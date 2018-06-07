package src;

import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Ship class - polygonal shape of the player's ship
 * @author Alberto Fernandez Saucedo
 */
public class Ship extends BaseVectorShape 
{
    // Specs for the ship 
    private final int[] shipX = {-6, -3, 0, 3, 6, 0};
    private final int[] shipY = {6, 7, 7, 7, 6, -7};
    
    // no args constructor
    Ship(){
        setShape(new Polygon(shipX, shipY, shipX.length));
        setAlive(true);
        
    }//end no args constructor
    
    // bounding via a rectangle
    public Rectangle getBounds(){
        Rectangle rect;
        rect = new Rectangle((int)getX() - 6, (int) getY() - 6, 12, 12);
        return rect;
        
    }//end getBounds
    
}// end Ship
