package game.inputs;

import game.GameManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    
    private GameManager gameManager;
    
    public MouseInputs (GameManager gameManager) {
        this.gameManager = gameManager;
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        gameManager.getShot().setX(e.getX() - gameManager.getShot().getWidth() / 2 + 1);
        gameManager.getShot().setY(e.getY() - gameManager.getShot().getHeight() / 2);
        gameManager.getShot().play();
        
        gameManager.getBallsHandler().checkCollision(e.getX(), e.getY());
        
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {} 

    @Override
    public void mouseDragged(MouseEvent e) {
        
        gameManager.getPlayer().setX(e.getX() - gameManager.getPlayer().getWidth() / 2);
        gameManager.getPlayer().setY(e.getY() - gameManager.getPlayer().getHeight() / 2);
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        gameManager.getPlayer().setX(e.getX() - gameManager.getPlayer().getWidth() / 2);
        gameManager.getPlayer().setY(e.getY() - gameManager.getPlayer().getHeight() / 2);
        
    }
}
