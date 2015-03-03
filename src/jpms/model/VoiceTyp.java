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
public enum VoiceTyp {
    
    S1 ("S1"),
    S2 ("S2"),
    A ("A"),
    T1 ("T1"),
    T2 ("T2"),
    B1 ("B1"),
    B2 ("B2");

    private final String typ;

    private VoiceTyp(String typ){
        this.typ = typ;
    }
}
