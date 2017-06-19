package grafika.elementi;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Matija on 16 Jun 17.
 */
public class Zatvorene extends Linije {
    private boolean finished;

    public Zatvorene(ArrayList<Point> tacke, int thick, Color color) {
        super(tacke, thick, color);

        finished = false;
    }


    // Ozacava finished flag da se iscrta poslednja linija
    public void setFinished(boolean newFinished){
        finished = newFinished;
    }
    public boolean isFinished(){ return finished; }

    @Override
    public void iscrtajLinije(Graphics2D g){ // Samo iscrtavanje linija
        super.iscrtajLinije(g);
        int tacaka = tacke.size();

        if(finished) { // Spajamo poslednju i prvu ako je gotovo
            g.drawLine(startPoint.x, startPoint.y, tacke.get(tacaka - 1).x, tacke.get(tacaka - 1).y);

        }
    }

    @Override
    public boolean selected(Point coord) {
        // Ako su dosadasnje linije selektovane
        if(super.selected(coord))
            return true;

        int tacaka = tacke.size();

        Duz tempDuz = new Duz(
            new Point(tacke.get(0).x,  tacke.get(0). y),
            new Point(tacke.get(tacaka - 1).x,      tacke.get(tacaka - 1).y),
            lineThick,
            lineColor
        );

        if(tempDuz.selected(coord)) // poslednja duz selektovana
            return true;

        return false;
    }

}
