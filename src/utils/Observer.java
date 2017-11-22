/**
 * Třída Observer
 * 
 * @author Margarita Tsakunova
 */
package utils;

import logika.IHra;


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
