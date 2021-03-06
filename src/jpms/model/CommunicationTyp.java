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
    
    @Override
    public String toString(){
        return typ;
    }
}
