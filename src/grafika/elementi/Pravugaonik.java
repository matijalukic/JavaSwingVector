package grafika.elementi;

import java.awt.*;

/**
 * Created by Matija on 16 Jun 17.
 */
public class Pravugaonik extends Figura {
    private Point endPoint;

    public Pravugaonik(Point startPoint, Point endPoint, int thick, Color color) {
        super(thick, color);
        // Podesavamo tacke
        super.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void iscrtaj(Graphics2D g) {
        if(figureSelected){
            g.setColor(selectionColor);
            g.setStroke(new BasicStroke(lineThick * 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            iscrtajLinije(g);
        }

        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineThick));

        iscrtajLinije(g);

    }

    public void iscrtajLinije(Graphics2D g){
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, startPoint.y);
        g.drawLine(startPoint.x, startPoint.y, startPoint.x, endPoint.y);
        g.drawLine(endPoint.x, endPoint.y, endPoint.x, startPoint.y);
        g.drawLine(endPoint.x, endPoint.y, startPoint.x, endPoint.y);
    }

    @Override
    public boolean selected(Point coord) {
        Duz gornja, donja, leva, desna; // Sve duzi
        gornja = new Duz(startPoint, new Point(endPoint.x, startPoint.y), lineThick, lineColor);
        donja = new Duz(endPoint, new Point(startPoint.x, endPoint.y), lineThick, lineColor);
        leva = new Duz(startPoint, new Point(startPoint.x, endPoint.y), lineThick, lineColor);
        desna = new Duz(endPoint, new Point(endPoint.x, startPoint.y), lineThick, lineColor);

        if(gornja.selected(coord) || donja.selected(coord) || leva.selected(coord) || desna.selected(coord))
            return true;

        return false;
    }

    @Override
    public void move(Point deltaPoint) {
        startPoint.translate(deltaPoint.x, deltaPoint.y);
        endPoint.translate(deltaPoint.x, deltaPoint.y);
    }

    @Override
    public void moveNew(Point newPos) {
        Point pointNewStart = new Point(newPos.x + deltaStartX, newPos.y + deltaStartY);
        Point newEnd = new Point(pointNewStart.x - startPoint.x + endPoint.x, pointNewStart.y - startPoint.y + endPoint.y);

        startPoint = pointNewStart;
        endPoint = newEnd;
    }

    public void setEndPoint(Point newEnd){
        endPoint = newEnd;
    }


}
