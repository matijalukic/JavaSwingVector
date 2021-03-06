package grafika.alati;

import grafika.elementi.*;
import grafika.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Matija on 16 Jun 17.
 */
public class CrtanjePravugaonika extends Alat {
    private Pravugaonik pravugaonik;
    private Point start,end;
    public CrtanjePravugaonika(){
        super();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        end = start = e.getPoint(); // Pocetna tacka ujedno i krajnja

        // nova linija
        pravugaonik = new Pravugaonik(start,end, (int) RadniProzor.lineThick.getSelectedItem(), RadniProzor.lineColor.getSelectedColor());
        WorkPanel.drawing.addFigure(pravugaonik); // dodajemo je odmah
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDrag(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseDrag(MouseEvent e) {
        end = e.getPoint();

        pravugaonik.setEndPoint(end);

        StringBuilder rightLabelTxt = new StringBuilder();

        int width = Math.abs(end.x - start.x);
        int height = Math.abs(end.y - start.y);

        rightLabelTxt.append("Width: " + width + " Height: " + height);
        rightLabelTxt.append( " X: " + end.x + " Y: " + end.y);

        RadniProzor.rightLabel.setText(rightLabelTxt.toString());

    }

}
