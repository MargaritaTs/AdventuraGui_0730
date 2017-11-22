/**
 * Třída ObserverNovaHra
 * 
 * @author Margarita Tsakunova
 */
package utils;

import logika.IHra;


public interface ObserverNovaHra {
    
    /**
   * Metoda vytvoření nové hry
   * @param hra
   */ 
    void novaHra(IHra hra);
    
}
