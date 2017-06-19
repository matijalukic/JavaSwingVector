package grafika;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import grafika.alati.CrtanjeIzlomljenih;
import grafika.alati.CrtanjeZatvorenih;
import grafika.elementi.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Matija on 12 Jun 17.
 */
public class Crtez {
    private ArrayList<Figura> lines;
    private BufferedWriter writeToFile;
    private BufferedReader loadToFile;

    private final static Pattern pointFormat = Pattern.compile("([\\-0-9]+),([\\-0-9]+)");
    private final static Pattern lineFormat = Pattern.compile("([^\\(]+)\\(([\\-0-9]+),([\\-0-9]+)\\):((([\\-0-9]+),([\\-0-9]+);){2,})");

    public Stack<Figura> undoFigures = new Stack<>();


    public static Map<String, Figura> mapTypes = new HashMap<>();

    static{
        // Svi tipovi
        mapTypes.put("grafika.elementi.Duz", new Duz(null, 1, Color.BLACK));
        mapTypes.put("grafika.elementi.Linije", new Linije(null, 1, Color.BLACK));
        mapTypes.put("grafika.elementi.Pravugaonik", new Pravugaonik(null, 1, Color.BLACK));
        mapTypes.put("grafika.elementi.Zatvorene", new Zatvorene(null, 1, Color.BLACK));
    }

    public Crtez(Figura... figure){
        lines = new ArrayList<>();

        for(Figura figura : figure){
            lines.add(figura);
        }

    }
    // Zapamti u fajl
    public void saveFile(File toFile){
        try {
            writeToFile = new BufferedWriter(new FileWriter(toFile));

            for (Figura figura : lines) {
                writeToFile.write(figura.saveFormat());
                writeToFile.write("\r\n");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                writeToFile.close();
            }
            catch (IOException ex){}
        }
    }

    // Dodavanje elementi
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

    // Paint svih figura
    public void paintAll(Graphics g){
        Graphics2D graph =  (Graphics2D)g;
        for(Figura toPaint : lines){
            toPaint.iscrtaj(graph);
        }
    }

    // Dohvatanje poslednje
    public Figura popLast(){
        int indexFigure = lines.size() - 1;

        if(indexFigure >= 0){
            Figura toReturn = lines.get(indexFigure);
            lines.remove(indexFigure);
            return toReturn;
        }

        return null;
    }

    // Delete jedne figure
    public void deleteFigura(Figura toDelete){
        int indexFigure = lines.size() - 1;

        // Pronalazimo
        while (indexFigure >= 0 && lines.get(indexFigure) != toDelete){
            indexFigure --;
        }



        if(indexFigure != -1) { // Ako smo pronasli
            // Ubacujemo na stek
            //undoFigures.push(lines.get(indexFigure));

            // Brisemo je
            lines.remove(indexFigure);
        }
    }

    // Load file-a
    public void loadFile(File fileToLoad){
        deleteAll(); // Brisemo sve prethodne

        int failedReads = 0, reads = 0;

        try {
            loadToFile = new BufferedReader(new FileReader(fileToLoad));


            // citamo sve linije
            String line;

            while((line = loadToFile.readLine()) != null){
                Matcher lineMatcher = lineFormat.matcher(line);

                if(lineMatcher.matches()){

                    String className = lineMatcher.group(1);

                    int lineThick = Integer.parseInt(lineMatcher.group(2));
                    Color lineColor = new Color(Integer.parseInt(lineMatcher.group(3)));

                    String tacke = lineMatcher.group(4);

                    Matcher pointMatcher = pointFormat.matcher(tacke);
                    System.out.println(tacke);

                    ArrayList<Point> pointsForFigura = new ArrayList<>();

                    while(pointMatcher.find()){ // Za svaku tacku
                        int x = Integer.parseInt(pointMatcher.group(1));
                        int y = Integer.parseInt(pointMatcher.group(2));
                        pointsForFigura.add(new Point(x,y)); // Ubacujemo u listu tacaka
                    }

                    Figura newFigure;
                    switch(className){
                        default:
                        case "grafika.elementi.Duz":
                            newFigure = new Duz(pointsForFigura, lineThick,lineColor);
                            break;
                        case "grafika.elementi.Pravugaonik":
                            newFigure = new Pravugaonik(pointsForFigura, lineThick, lineColor);
                            break;
                        case "grafika.elementi.Linije":
                            newFigure = new Linije(pointsForFigura, lineThick, lineColor);
                            break;
                        case "grafika.elementi.Zatvorene":
                            newFigure = new Zatvorene(pointsForFigura, lineThick, lineColor);
                            break;
                    }

                    // Ubaci novu figuru
                    addFigure(newFigure);



                }
                else
                    failedReads++;

                reads++; // povecavamo broj
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                loadToFile.close();
            }
            catch (IOException ex){}
        }
    }

    // Obrisi sve
    public void deleteAll(){
        lines.clear();
    }

    // undo
    public void undo(){

        // Poslednja
        if(lines.size() > 0) {
            Figura lastFigura = lines.get(lines.size() - 1);

            if(lastFigura instanceof Zatvorene){
                ((Zatvorene) lastFigura).setFinished(true); // onaj koji  se prebacuje na stack zavrsava se
            }

            // Resetujemo alate za koje radimo undo
            if(WorkPanel.selectedTool instanceof CrtanjeZatvorenih)
                ((CrtanjeZatvorenih) WorkPanel.selectedTool).resetTool();
            if(WorkPanel.selectedTool instanceof CrtanjeIzlomljenih)
                ((CrtanjeIzlomljenih)WorkPanel.selectedTool).resetTool();

            undoFigures.push(lastFigura);

            // Uklanjamo je
            lines.remove(lines.size() - 1);

        }


    }

    public void redo(){
        // Ako nije prazan
        if(!undoFigures.empty()){
            // Popujemo
            Figura redoFigure = undoFigures.pop();
            // Dodajemo novu
            addFigure(redoFigure);


        }

    }

}
