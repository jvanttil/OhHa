package md.grafiikka;

import java.awt.*;

/**
 * @author jvanttil
 */
public class viiva {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    
    public viiva() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = Color.black;
    }
    
    public void draw( Graphics page ) {
        page.setColor(color);
        page.drawLine(x1,y1,x2,y2);
    }
    
    public void liikutaalku( int x1, int y1 ) {
        this.x1 = x1;
        this.y1 = y1;
    }
    
    public void liikutaloppu( int x2, int y2 ) {
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public void setx1( int x1 ) {
        this.x1 = x1;
    }
    
    public void sety1( int y1 ) {
        this.y1 = y1;
    }
    
    public void setx2( int x2 ) {
        this.x2 = x2;
    }
    
    public void sety2( int y2 ) {
        this.y2 = y2;
    }
}
