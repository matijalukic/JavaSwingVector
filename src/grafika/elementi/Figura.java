package grafika.elementi;

import java.awt.*;
/**
 * Created by Matija on 12 Jun 17.
 */
public abstract class Figura {
    protected int lineThick = 1;
    protected Color lineColor = Color.BLACK;
    protected Point startPoint;
    protected int deltaStartX, deltaStartY; // rastojanje gde je mis uhvatio u odnosu na pocetak


    public Figura(int thick, Color color) {
        lineThick = thick;
        lineColor = color;
    }

    public abstract void iscrtaj(Graphics2D g); // paint figure

    public abstract boolean selected(Point coord); // da li je selektovana

    public abstract void move(Point delta); // pomeri figuru

    public abstract void moveNew(Point newPos); // pomeri na novu

    public void setNewCatch(Point catchPos) { // Izracunava relativne kordinate gde ga hvata mis
        deltaStartX = startPoint.x - catchPos.x;
        deltaStartY = startPoint.y - catchPos.y;
    }
}