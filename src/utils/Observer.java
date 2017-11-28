
package utils;

import logika.IHra;

/**
 * Třída Observer
 * 
 * @author Margarita Tsakunova
 * @version pro školní rok 2017/2018
 */
public interface Observer {
    
   /**
   * Metoda aktualizace pozorovatele
   */ 
   void update(); 
   
   /**
   * Metoda vytvoření nové hry
   * @param hra
   */ 
   void novaHra(IHra hra);
    
}
