package grafika.alati;

import grafika.elementi.*;
import grafika.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Matija on 16 Jun 17.
 */
public class CrtanjeIzlomljenih extends Alat {
    private Linije izlomljena ;

    public CrtanjeIzlomljenih(){
        super();
    }

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e) {
        Point newPoint = e.getPoint();

        if(izlomljena == null) { // nova linija
            izlomljena = new Linije(new ArrayList<>(), (int) RadniProzor.lineThick.getSelectedItem() , RadniProzor.lineColor.getSelectedColor());
            izlomljena.addPoint(newPoint); // Prva tacka

            WorkPanel.drawing.addFigure(izlomljena); // dodajemo figuru
        }
        else {
            izlomljena.addPoint(newPoint);

            // Double click
            if (e.getClickCount() == 2) {
                // Prelazimo na novu
                izlomljena = null;

                System.out.println("Sledeci klik ce napraviti novu!");
            }
        }
    }

    @Override
    public void mouseDrag(MouseEvent e) {

    }
}
