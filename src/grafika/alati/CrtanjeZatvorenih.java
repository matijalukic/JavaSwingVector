package grafika.alati;
import java.awt.*;
import java.awt.event.*;
import grafika.*;
import grafika.elementi.*;
import java.util.*;
/**
 * Created by Matija on 16 Jun 17.
 */
public class CrtanjeZatvorenih extends Alat {

    private Zatvorene zatvorenaLinija ;

    public CrtanjeZatvorenih(){
        super();
    }

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e) {
        Point newPoint = e.getPoint();

        if(zatvorenaLinija == null) { // nova linija
            zatvorenaLinija = new Zatvorene(new ArrayList<>(), (int) RadniProzor.lineThick.getSelectedItem() , RadniProzor.lineColor.getSelectedColor());
            zatvorenaLinija.addPoint(newPoint); // Prva tacka

            WorkPanel.drawing.addFigure(zatvorenaLinija); // dodajemo figuru
        }
        else {
            zatvorenaLinija.addPoint(newPoint);

            // Double click
            if (e.getClickCount() == 2) {
                // Prelazimo na novu
                zatvorenaLinija = null;

                System.out.println("Sledeci klik ce napraviti novu!");
            }
        }
    }

    @Override
    public void mouseDrag(MouseEvent e) {

    }

}
