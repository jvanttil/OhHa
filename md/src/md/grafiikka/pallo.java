package md.grafiikka;

import java.awt.*;

/**
 * @author jvanttil
 */
public class pallo {
    
    private int diameter, x, y;
    private Color color;
    
    public pallo() {
        this.diameter = 10;
        this.color = Color.black;
        this.x = 0;
        this.y = 0;
    }
    
    public pallo( int x, int y, Color color, int diameter ) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.diameter = diameter;
    }
    
    public void draw( Graphics page ) {
        page.setColor(color);
        page.fillOval(x,y,diameter,diameter);
    }
    
    public void liikuta( int x, int y, int z ) {
        this.diameter = z;
        this.x = x-(int)(diameter/2);
        this.y = y-(int)(diameter/2);
        
    }
    
    public void setdiameter( int sd ) {
        diameter = sd;
    }
    
    public void setcolor( Color sc ) {
        color = sc;
    }
    
    public void setx( int sx ) {
        x = sx;
    }
    
    public void sety( int sy ) {
        y = sy;
    }
    
}
