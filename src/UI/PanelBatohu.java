/**
 * Třída implementuje panelBatohu zobrazující list s předměty vpravo v hlavním okně.
 * Zobrazuje předměty, které byly vloženy do batohu
 * 
 * @author Margarita Tsakunova
 */
package UI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.HerniPlan;
import logika.IHra;
import logika.Predmet;
import utils.Observer;

/**
 *
 * @author Margarita Tsakunova
 */

public class PanelBatohu implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;

    /*
    * Konstruktor pro panel batohu
    */
    public PanelBatohu(HerniPlan plan) {
       this.plan = plan;
       plan.registerObserver(this);
        init();
    }

    /*
    * Metoda vytvoří list pro předměty v batohu
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(150);
        
        update();
    }
    
    /*
    * Metoda vrací list
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list předmětů v batohu. Zobrazuje obrázky předmětů, 
    * které má hráč u sebe.
    */
    @Override 
    public void update() 
    {        
        Map<String, Predmet> seznam;
        seznam = plan.getBatoh().getSeznamPredmetu();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Predmet pomocny = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+pomocny.getObrazek()), 130, 130, false, false));
        data.add(obrazek);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
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
    public void novaHra(IHra hra) { //tady tecka se vrati na zacatek!!
        hra.getHerniPlan().deleteObserver(this);
        this.plan = hra.getHerniPlan();
        this.plan.registerObserver(this);
        update();
    } 

}
