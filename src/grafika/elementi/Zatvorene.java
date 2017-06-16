package grafika.elementi;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Matija on 16 Jun 17.
 */
public class Zatvorene extends Figura {

    private ArrayList<Point> tacke = new ArrayList<>();

    public Zatvorene(ArrayList<Point> tacke, int thick, Color color) {
        super(thick, color);

        this.tacke = tacke;

    }

    // Dodaj tacku
    public void addPoint(Point newPoint) {
        if(tacke.size() == 0) // na pocetku
            startPoint = newPoint;
        tacke.add(newPoint);
    }


    @Override
    public void iscrtaj(Graphics2D g) {
        g.setColor(lineColor);
        g.setStroke(new BasicStroke(lineThick));


        int tacaka = tacke.size();
        // Za svake 2 tacke iscrtavamo
        for (int i = 0; i < tacaka; i++) {
            int slTacka = (i + 1) % tacaka;
            g.drawLine(tacke.get(i).x, tacke.get(i).y, tacke.get(slTacka).x, tacke.get(slTacka).y);
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


}
