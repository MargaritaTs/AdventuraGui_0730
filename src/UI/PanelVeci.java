/**
 * Třída implementuje panelVeci zobrazující list s předměty vlevo v hlavním okně.
 * Předměty se nachází v různých prostorech
 * 
 * @author Margarita Tsakunova
 */
package UI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.IHra;
import logika.Predmet;
import utils.Observer;

/**
 * Panel zobrazujíci předměty v aktuálním prostoru
 * 
 * @author Margarita Tsakunova
 */

public class PanelVeci implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;
    private TextArea centralText;

    /*
    * Konstruktor pro panel
    */
    public PanelVeci(HerniPlan plan, TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       
       centralText = text;
        init();
    }

    /*
    * Metoda vytvoří list pro předměty
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(200);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 2) 
                {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Predmet> seznam;
                    seznam = plan.getAktualniProstor().getVeci();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }
                    
                    String vstupniPrikaz = "seber "+nazev;
                    String odpovedHry = plan.getHra().zpracujPrikaz("seber "+nazev);

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    plan.notifyAllObservers();
                }
            }
        });
        
        
        
        
        update();
    }
    
    /*
    * Metoda vrací list
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list předmětu. Zobrazuje obrázky předmětů, které se
    * nachází v jednotlivých prostorech
    */
    @Override 
    public void update() 
    {        
        Map<String, Predmet> seznam;
        seznam = plan.getAktualniProstor().getVeci();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Predmet pomocny = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocny.getObrazek()), 130, 130, false, false));
        data.add(obrazek);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
    
    /**
     * Metoda smaže pozorovatele(tečku na mapě) a vrátí ho do počátečního stavu
     * při spuštění nové hry
     * @param hra
     */
    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this);
        this.plan = hra.getHerniPlan();
        this.plan.registerObserver(this);
        update();
    } 
}