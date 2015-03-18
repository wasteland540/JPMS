package jpms.services;

import java.util.List;
import jpms.model.Person;

/**
 *
 * @author m.elz
 */
public interface IPersonService {
    
    boolean save(Person person);
    
    List<Person> getPersons();
    
    List<Person> getPersons(boolean passive);
    
}
