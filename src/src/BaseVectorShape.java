package src;

import java.awt.Shape;

/**
 * Base vector shape class for polygonal shapes
 * 
 * @author Alberto Fernandez Saucedo
 */
public class BaseVectorShape 
{
    // variables
    private Shape shape;
    private boolean alive;
    private double x, y;
    private double velocity_x, velocity_y;
    private double moveAngle, faceAngle;
    
    // accessor methods
    public Shape getShape(){
        return shape;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getVelX(){
        return velocity_x;
    }
    
    public double getVelY(){
        return velocity_y;
    }
    
    public double getMoveAngle(){
        return moveAngle;
    }
    
    public double getFaceAngle(){
        return faceAngle;
    }
    
    // mutator and helper methods
    
    
}// end BaseVectorShape
