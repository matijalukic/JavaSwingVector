package Grafika;

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

    public abstract void iscrtaj(Graphics2D g);
}
