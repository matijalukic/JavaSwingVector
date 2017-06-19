package grafika.elementi;

import java.awt.*;
import java.util.*;

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

    public Duz(ArrayList<Point> points, int thick, Color color) {
        super(thick, color);

        if (points != null) {
            this.startPoint = points.get(0);
            this.endPoint = points.get(1);
        }
    }

    @Override
    public void setPoints(ArrayList<Point> newPoints){ // Postavi nove tacke
        if (newPoints != null) {
            this.startPoint = newPoints.get(0);
            this.endPoint = newPoints.get(1);
        }
    }

    @Override
    public ArrayList<Point> getPoints(){
        ArrayList<Point> retList = new ArrayList<>();
        retList.add(startPoint);
        retList.add(endPoint);

        return retList;
    }


    public void iscrtaj(Graphics2D g){
        if(figureSelected) { // Ako je selektovano
            g.setColor(selectionColor);
            g.setStroke(new BasicStroke(3 * lineThick, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            iscrtajLinije(g);
        }


        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineThick));
        iscrtajLinije(g);
    }

    public void iscrtajLinije(Graphics2D g){
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

    }

    // Da li je selektovana
    @Override
    public boolean selected(Point coord){
        double A, B = 1, C, k, d = Integer.MAX_VALUE;

        if(startPoint.getX() != endPoint.getX()) { // ako nije uspravna
            k = (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
            A = -k;

            C = k * endPoint.getX() - endPoint.getY();

            d = (A * coord.getX() + B * coord.getY() + C) / Math.sqrt(A * A + B * B);


        }
        else{ // Ako je uspravna
            if( (endPoint.y <= coord.y && startPoint.y >= coord.y) || (endPoint.y >= coord.y && startPoint.y <= coord.y)){
                d =  coord.x - startPoint.x;// razlika izmelju x koordinata
            }
        }

        if (Math.abs(d) < catchCoef * lineThick) {
            return true;
        }

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
    public String saveFormat(){
        StringBuffer retString = new StringBuffer();

        retString.append(super.saveFormat());

        retString.append(startPoint.x).append(",").append(startPoint.y).append(";"); // prva tacka
        retString.append(endPoint.x).append(",").append(endPoint.y).append(";"); // druga tacka
        return retString.toString();
    }

    @Override
    public String toString(){
        return "lineThick: " + lineThick + " color:" + lineColor.toString() + " start:" + startPoint + " end:" + endPoint + " ---- selected";
    }

}
