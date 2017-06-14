package Grafika;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Matija on 12 Jun 17.
 */
public class Crtez {
    private ArrayList<Figura> lines;

    public Crtez(Figura... figure){
        lines = new ArrayList<>();

        for(Figura figura : figure){
            lines.add(figura);
        }

    }

    public void addFigure(Figura newFigure){
        lines.add(newFigure);
    }

    public void paintAll(Graphics g){
        Graphics2D graph =  (Graphics2D)g;
        for(Figura toPaint : lines){
            toPaint.iscrtaj(graph);
        }
    }


}
