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
public enum CommunicationTyp {
    
    Phone ("Telefon"),
    Mobile ("Handy"),
    Email ("E-mail");
    
    private final String typ;
    
    private CommunicationTyp(String typ){
        this.typ = typ;
    }
    
}
