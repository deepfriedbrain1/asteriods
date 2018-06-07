package src;

import java.awt.Rectangle;

/**
 * Bullet class - polygonal shape of a bullet
 * @author Alberto Fernandez Saucedo
 */
public class Bullet extends BaseVectorShape {
    
    // no args constructor
    Bullet(){
        setShape(new Rectangle(0, 0, 1, 1));
        setAlive(false);
    }//end no args constructor
    
    // bounding rectangle
    public Rectangle getBounds(){
        Rectangle rect;
        rect = new Rectangle((int)getX(), (int)getY(), 1, 1);
        return rect;
        
    }// end getBounds
    
}//end Bullet
