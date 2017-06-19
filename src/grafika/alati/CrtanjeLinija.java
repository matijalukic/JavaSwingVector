package grafika.alati;

import grafika.*;
import grafika.elementi.*;

import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Matija on 14 Jun 17.
 */
public class CrtanjeLinija extends Alat {
    private Duz newLine;
    private Point start, end;

    public CrtanjeLinija(){
        super();
    }

    @Override
    public void mousePressed(MouseEvent e){

        end = start = e.getPoint(); // Pocetna tacka ujedno i krajnja

        // nova linija
        newLine = new Duz(start,end, (int) RadniProzor.lineThick.getSelectedItem(), RadniProzor.lineColor.getSelectedColor());
        WorkPanel.drawing.addFigure(newLine); // dodajemo je odmah

    }

    @Override
    public void mouseReleased(MouseEvent e){
        mouseDrag(e); // pozivamo drag da bi se zavrsio
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mouseDrag(MouseEvent e){
        end = e.getPoint();
        newLine.setNewEnd(end);

        StringBuilder rightLabelTxt = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        if(end != null)
            rightLabelTxt.append("Distance: " + df.format(Point.distance(start.x, start.y,  end.x, end.y)));

        rightLabelTxt.append( " X: " + end.x + " Y: " + end.y);

        RadniProzor.rightLabel.setText(rightLabelTxt.toString());
    }

}
