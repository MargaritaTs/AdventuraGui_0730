package logika;


/**
 *  Třída Predmet - popisuje jednotlivé předměty.
 * 
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *  "Predmet" reprezentuje jednu věc nacházející se v určitém prostoru.
 *  Předmět může být přenositelným a naopak.
 *
 *@author     Margarita Tsakunova
 *@version    pro školní rok 2016/2017
 */

public class Predmet
{
    private String nazev;
    private boolean prenositelny;  // zda předmět je přenositelný
    
    
    
     /***************************************************************************
     * Konstuktor třídy
     * 
     * @param nazev         název nového předmětu
     * @param prenositelny  true, pokud je možné předmět vložit do batohu
     */
    public Predmet(String nazev, boolean prenositelny)
    {
        this.nazev = nazev;
        this.prenositelny = prenositelny;
    }
    
    /**
     * Metoda vrací název předmětu.
     * 
     * @return  název předmětu
     */
    public String getNazev() {
        return nazev;
    }
    
     /**
     * Metoda rozhodne, zda předmět je přenositelný.
     * 
     * @return true - pokud předmět je přenositelný
     */
    public boolean jePrenositelny() {
        return prenositelny;
    }
   
    
}
