package grafika.elementi;

import java.awt.*;
/**
 * Created by Matija on 12 Jun 17.
 */
public class Duz extends Figura {
    private Point endPoint;


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

    // Da li je selektovana
    @Override
    public boolean selected(Point coord){
        double A, B = 1, C, k, d;

        k = (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
        A = - k;

        C = k * endPoint.getX() - endPoint.getY();

        d = (A*coord.getX() + B*coord.getY() + C) / Math.sqrt(A*A + B*B);

        if(Math.abs(d) < 2*lineThick)
            return true;

        return false;
    }

    // Pomeri Figuru
    @Override
    public void move(Point deltaPoint){
        startPoint.translate(deltaPoint.x, deltaPoint.y);
        endPoint.translate(deltaPoint.x, deltaPoint.y);
    }
    @Override
    public void moveNew(Point newPos){
        Point pointNewStart = new Point(newPos.x + deltaStartX, newPos.y + deltaStartY);
        Point newEnd = new Point(pointNewStart.x - startPoint.x + endPoint.x, pointNewStart.y - startPoint.y + endPoint.y);

        startPoint = pointNewStart;
        endPoint = newEnd;
    }


    // Postavljanje novog kraja
    public void setNewEnd(Point newEnd){
        endPoint = newEnd;
    }

    @Override
    public String toString(){
        return "lineThick: " + lineThick + " color:" + lineColor.toString() + " start:" + startPoint + " end:" + endPoint + " ---- selected";
    }

}
