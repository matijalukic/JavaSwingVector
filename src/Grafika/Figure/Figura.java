package Grafika.Figure;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Matija on 12 Jun 17.
 */
public abstract class Figura {
    protected int lineThick = 1;
    protected Color lineColor = Color.BLACK;

    public Figura(int thick, Color color){
        lineThick = thick;
        lineColor = color;
    }

    public abstract void iscrtaj(Graphics2D g); // paint figure
    public abstract boolean selected(Point coord); // da li je selektovana
    public abstract void move(Point delta); // pomeri figuru
    public abstract void moveNew(Point newPos); // pomeri na novu
    public abstract void setNewCatch(Point catchPos); // Izracunava relativne kordinate gde ga hvata mis
}
