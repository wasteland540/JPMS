package jpms.services;

import com.google.inject.Singleton;
import jpms.model.Person;


/**
 *
 * @author m.elz
 */

@Singleton
public class PersonService extends AbstractService implements IPersonService {

    @Override
    public boolean save(Person person) {
        boolean isCreated;
        
        startTransaction();
        
        //using merge instead of persist, cause we have already an instance of a personGroup in the person.
        //if we use persist, we would create a new database entry of personGroup!
        em.merge(person);
        isCreated = true;
        
        endTransaction();
        
        return isCreated;
    }
          
}
