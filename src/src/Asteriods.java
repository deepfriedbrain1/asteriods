package src;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Primary class for the game
 * @author Alberto Fernandez Saucedo
 */
public class Asteriods extends Applet implements Runnable, KeyListener 
{
    
    Thread thread; // the main thead that becomes the game loop
    
    BufferedImage backBuffer; // used as a double buffer
    
    Graphics2D g2d; // main drawing objecgt for the backBuffer
    
    boolean showBounds = false; // toggle for drawing bounding boxes
    
    final int ASTEROIDS = 20; // constant for asteroid array
    Asteroid[] ast = new Asteroid[ASTEROIDS]; 
    
    final int BULLETS = 10; // constant for bullet array
    Bullet[] bullet = new Bullet[BULLETS];
    int currentBullet = 0;
    
    // the player's ship
    Ship ship = new Ship();
    
    // create the identitiy transform(0,0)
    AffineTransform identity = new AffineTransform();
    
    // create a random number generator
    Random random = new Random();
    
    // applet init event
    public void init(){
        backBuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        g2d = backBuffer.createGraphics();
        
        ship.setX(320);
        ship.setY(240);
        
        for(int i = 0; i < BULLETS; i++)
            bullet[i] = new Bullet();
        
        for(int i = 0; i < ASTEROIDS; i++){
            ast[i] = new Asteroid();
            ast[i].setRotationVelocity(random.nextInt(3)+1);
            ast[i].setX((double)random.nextInt(600)+20);
            ast[i].setY((double)random.nextInt(440)+20);
            ast[i].setMoveAngle(random.nextInt(360));
            double angle = ast[i].getMoveAngle()-90;
            ast[i].setVelX(calcAngleMoveX(angle));
            ast[i].setVelY(calcAngleMoveY(angle));
            
            // start the user input listener
            addKeyListener(this);
           
        }
    }//end init
    
    public void update(Graphics g){
        g2d.setTransform(identity);
        
        g2d.setPaint(Color.BLACK);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("Ship: " + Math.round(ship.getX()) + "," +
                Math.round(ship.getY()), 5, 10);
        g2d.drawString("Move angle: " + Math.round(ship.getMoveAngle())+90, 5, 25);
        g2d.drawString("Face angle: " + Math.round(ship.getFaceAngle()), 5, 40);
        
        drawShip();
        drawBullets();
        drawAsteroids();
        
        paint(g);
    }
    
    public void drawShip(){
        g2d.setTransform(identity);
        g2d.translate(ship.getX(), ship.getY());
        g2d.rotate(Math.toRadians(ship.getFaceAngle()));
        g2d.setColor(Color.ORANGE);
        g2d.fill(ship.getShape());
    }
    
    public void drawAsteroids(){
        for(int i = 0; i < ASTEROIDS; i++){
            if(ast[i].isAlive()){
                g2d.setTransform(identity);
                g2d.translate(ast[i].getX(), ast[i].getY());
                g2d.rotate(Math.toRadians(ast[i].getMoveAngle()));
                g2d.setColor(Color.DARK_GRAY);
                g2d.fill(ast[i].getShape());
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(backBuffer, 0, 0, this);
    }
    
    @Override
    public void start(){
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        while(t == thread){
            try{
                gameUpdate();
                Thread.sleep(20); // target framerate is 50 fps
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
            repaint();
        }
    }
    
    public void stop(){
        thread = null;
    }
    
    private void gameUpdate(){
        updateShip();
        updateBullets();
        updateAsteroids();
        checkCollisions();
    }

    public void updateShip(){
        ship.incX(ship.getVelX());
        
        if(ship.getX() < -10)
            ship.setX(getSize().width+10);
        else if (ship.getX() > getSize().width+10)
            ship.setX(-10);
        
        ship.incY(ship.getVelY());
        
        if(ship.getY() < -10)
            ship.setY(getSize().height+10);
        else if(ship.getY() > getSize().height+10)
           ship.setY(-10);
    }
    
    public void updateBullets(){
        for(int i = 0; i < BULLETS; i++){
            if(bullet[i].isAlive()){
                bullet[i].incX(bullet[i].getVelX());
                
                if(bullet[i].getX() < 0 ||
                   bullet[i].getX() > getSize().width)
                {
                    bullet[i].setAlive(false);
                }
                
                bullet[i].incY(bullet[i].getVelY());
                
                if(bullet[i].getY() < 0 || bullet[i].getY() > getSize().height)
                {
                    bullet[i].setAlive(false);
                }
            }
        }
    }//end updateBullets
    
    public void updateAsteroids(){
        for(int i = 0; i < ASTEROIDS; i++){
            if(ast[i].isAlive()){
                ast[i].incX(ast[i].getVelX());
                
                if(ast[i].getX() < -20)
                    ast[i].setX(getSize().width+20);
                else if(ast[i].getX() > getSize().width+20)
                    ast[i].setX(-20);
                
                ast[i].incY(ast[i].getVelY());
                
                if(ast[i].getY() < -20)
                    ast[i].setY(getSize().height+20);
                else if(ast[i].getY() > getSize().height+20)
                    ast[i].setY(-20);
                
                ast[i].incMoveAngle(ast[i].getRotationVelocity());
                
                if(ast[i].getMoveAngle() < 0)
                    ast[i].setMoveAngle(360 - ast[i].getRotationVelocity());
                else if(ast[i].getMoveAngle() > 360)
                    ast[i].setMoveAngle(ast[i].getRotationVelocity());
            }
        }
    }//end updateAsteroids
    
    public void checkCollisions(){
        for(int i = 0; i < ASTEROIDS; i++){
            if(ast[i].isAlive()){
                
                for(int n = 0; n < BULLETS; n++){
                    if(bullet[n].isAlive()){
                        
                        if(ast[i].getBounds().contains(
                            bullet[n].getX(), bullet[n].getY())){
                            bullet[n].setAlive(false);
                            ast[i].setAlive(false);
                            continue;
                        }
                    }
                }
                
                if(ast[i].getBounds().intersects(ship.getBounds())){
                    ast[i].setAlive(false);
                    ship.setX(320);
                    ship.setY(240);
                    ship.setFaceAngle(0);
                    ship.setVelX(0);
                    ship.setVelY(0);
                    continue;
                }
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
