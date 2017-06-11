package Grafika;

import java.awt.*;
/**
 * Created by Matija on 12 Jun 17.
 */
public class Duz extends Figura {
    private Point startPoint, endPoint;

    public Duz(Point startPoint, Point endPoint, int thick, Color color){
        super(thick, color);

        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    public void iscrtaj(Graphics2D g){
        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineThick));
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

}
