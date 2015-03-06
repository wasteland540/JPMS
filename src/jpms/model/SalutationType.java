package jpms.model;

/**
 *
 * @author m.elz
 */
public enum SalutationType {
    
    Frau ("Frau"),
    FrauDr ("Frau Dr."),
    Herr ("Herr"),
    HerrDr ("Herr Dr.");

    private final String typ;

    private SalutationType(String typ){
        this.typ = typ;
    }
 
    @Override
    public String toString(){
        return typ;
    }
}
