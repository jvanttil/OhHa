package md.grafiikka;

import java.awt.*;

/**
 * @author jvanttil
 */
public class pallo {
    
    private int diameter, x, y;
    private Color color;
    
    public pallo() {
        diameter = 10;
        color = Color.white;
        x = 0;
        y = 0;
    }
    
    public void draw( Graphics page ) {
        page.setColor(color);
        page.fillOval(x,y,diameter,diameter);
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
