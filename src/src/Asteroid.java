package src;

import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Asteroid class - for polygonal asteroid shapes
 * @author Alberto Fernandez Saucedo
 */
public class Asteroid extends BaseVectorShape {
    
    // variables
    private final int[] asterX = {-20,-13,0,20,22,20,12,2,-10,-22,-16};
    private final int[] asterY = {20,23,17,20,16,-20,-22,-14,-17,-20,-5};
    
    //no args constructor
    Asteroid(){
        setShape(new Polygon(asterX, asterY, asterX.length));
        setAlive(true);
        setRotationVelocity(0.0);
    }//end no args constructor
    
    // rotation speed
    protected double rVelocity;
    
    public double getRotationVelocity(){
        return rVelocity;
    }
    
    public void setRotationVelocity(double velocity){
        rVelocity = velocity;
    }
    
    // bounding Rectangle
    public Rectangle getBounds(){
        Rectangle rect;
        rect = new Rectangle((int)getX()-20, (int)getY()-20, 40, 40);
        return rect;
    
    }//end getBounds 
}//end Asteroid
