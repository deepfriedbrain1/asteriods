package src;

import java.awt.Shape;

/**
 * Base vector shape class for polygonal shapes
 * 
 * @author Alberto Fernandez Saucedo
 */
public class BaseVectorShape 
{
    // no arg constructor
    BaseVectorShape(){
        setShape(null);
        setAlive(false);
        setX(0.0);
        setY(0.0);
        setVelX(0.0);
        setVelY(0.0);
        setMoveAngle(0.0);
        setFaceAngle(0.0);
        
    }// end constructor
    
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
    public void setShape(Shape shape){
        this.shape = shape;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public void incX(double amount){
        this.x += amount;
    }
    
    public void incY(double amount){
        this.y += amount;
    }
    
    public void setVelX(double velocity){
       this.velocity_x = velocity;
    }
    
    public void setVelY(double velocity){
        this.velocity_y = velocity;
    }
    
    public void incVelX(double velocity){
        this.velocity_x += velocity;
    }
    
    public void incVelY(double velocity){
        this.velocity_y += velocity;
    }
    
    public void setFaceAngle(double angle){
        this.faceAngle = angle;
    }
    
    public void setMoveAngle(double angle){
        this.moveAngle = angle;
    }
    
    public void incFaceAngle(double amount){
        this.faceAngle += amount;
    }
    
    public void incMoveAngle(double amount){
        this.moveAngle += amount;
    }
    
}// end BaseVectorShape
