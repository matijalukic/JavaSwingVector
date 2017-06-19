package grafika.elementi;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Matija on 16 Jun 17.
 */
public class Zatvorene extends Figura {

    private ArrayList<Point> tacke = new ArrayList<>();
    private boolean finished;

    public Zatvorene(ArrayList<Point> tacke, int thick, Color color) {
        super(thick, color);

        this.tacke = tacke;
        finished = false;
    }

    @Override
    public void setPoints(ArrayList<Point> newPoints){ // Postavi nove tacke
        if (newPoints != null) {
            this.startPoint = newPoints.get(0);
            tacke = newPoints;
        }
    }

    // Dodaj tacku
    public void addPoint(Point newPoint) {
        if(tacke.size() == 0) // na pocetku
            startPoint = newPoint;
        tacke.add(newPoint);
    }

    // Ozacava finished flag da se iscrta poslednja linija
    public void setFinished(boolean newFinished){
        finished = newFinished;
    }

    @Override
    public void iscrtaj(Graphics2D g) {

        if(figureSelected){ // Ako je selektovan
            g.setColor(selectionColor);
            g.setStroke(new BasicStroke(lineThick * 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            iscrtajLinije(g);
        }

        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineThick));

        iscrtajLinije(g);

    }

    public void iscrtajLinije(Graphics2D g){ // Samo iscrtavanje linija
        int tacaka = tacke.size();
        // Za svake 2 tacke iscrtavamo
        for (int i = 0; i < tacaka - 1; i++) {
            g.drawLine(tacke.get(i).x, tacke.get(i).y, tacke.get(i+1).x, tacke.get(i+1).y);
        }

        if(finished) { // Spajamo poslednju i prvu ako je gotovo
            g.drawLine(startPoint.x, startPoint.y, tacke.get(tacaka - 1).x, tacke.get(tacaka - 1).y);

        }
    }

    @Override
    public boolean selected(Point coord) {
        int tacaka = tacke.size();

        for(int i = 0; i < tacaka; i++){ // za sve duzi
            int slTacka = (i+1) % tacaka;
            Duz tempDuz = new Duz(
                    new Point(tacke.get(i).x,  tacke.get(i). y),
                    new Point(tacke.get(slTacka).x,      tacke.get(slTacka).y),
                    lineThick,
                    lineColor
            );

            if(tempDuz.selected(coord)) // ako je bilo koja duz
                return true;
        }
        return false;
    }

    @Override
    public void move(Point delta) {
        int listSize = tacke.size();

        for(int i = 0; i < listSize; i ++){
            tacke.get(i).translate(delta.x, delta.y);
        }
    }

    @Override
    public void moveNew(Point newPos) {
        Point pointNewStart = new Point(newPos.x + deltaStartX, newPos.y + deltaStartY);

        for(int i = 1; i < tacke.size(); i++){ // Sve tacke osim prve

            Point newPoint = new Point(pointNewStart.x - startPoint.x + tacke.get(i).x, pointNewStart.y - startPoint.y + tacke.get(i).y);

            tacke.set(i, newPoint);

        }
        startPoint = pointNewStart;
        tacke.set(0, startPoint);
    }


    @Override
    public String saveFormat(){
        StringBuffer retString = new StringBuffer();

        retString.append(super.saveFormat());


        for(Point tacka: tacke){
            retString.append(tacka.x).append(",").append(tacka.y).append(";"); // prva tacka
        }
        return retString.toString();
    }

}
