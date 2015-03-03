/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpms.model;

/**
 *
 * @author m.elz
 */
public enum CountryTyp {
    
    Germany ("Deutschland");
    
    private final String typ;
    
    private CountryTyp(String typ){
        this.typ = typ;
    }
}
