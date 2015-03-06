
package jpms.model;

/**
 *
 * @author m.elz
 */
public enum VoiceType {
    
    S1 ("S1"),
    S2 ("S2"),
    A ("A"),
    T1 ("T1"),
    T2 ("T2"),
    B1 ("B1"),
    B2 ("B2");

    private final String typ;

    private VoiceType(String typ){
        this.typ = typ;
    }
    
    @Override
    public String toString(){
        return typ;
    }
}
