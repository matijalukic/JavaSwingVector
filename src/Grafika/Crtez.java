package Grafika;

import Grafika.Figure.Figura;

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
    // Dodavanje Figure
    public void addFigure(Figura newFigure){
        lines.add(newFigure);
    }

    // Selektovanje figure na osnovu pointa
    public Figura selectFigure(Point coord){

        int indexFigure = lines.size() - 1;

        while(indexFigure >= 0){
            Figura tempFigura = lines.get(indexFigure);

            if(tempFigura.selected(coord))
                return tempFigura;

            indexFigure --;
        }

        return null;
    }

    public void paintAll(Graphics g){
        Graphics2D graph =  (Graphics2D)g;
        for(Figura toPaint : lines){
            toPaint.iscrtaj(graph);
        }
    }


}
