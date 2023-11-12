package game.models;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends ScreenObject {
    
    private BufferedImage aimIcon;
    
    public Player () {
        
        setWidth(64);
        setHeight(64);
        
        try {
            importAimIcon();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public BufferedImage getAimIcon () {
        return aimIcon;
    }

    private void importAimIcon() throws FileNotFoundException {
        
        FileInputStream fis = new FileInputStream("res/aim.png");
        
        try {
            
            aimIcon = ImageIO.read(fis);
            
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } finally {
            
            try {
                fis.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(aimIcon, getX(), getY(), getWidth(),
                 getHeight(), null);
    }
}
