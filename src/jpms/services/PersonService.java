package jpms.services;

import com.google.inject.Singleton;
import java.util.List;
import javax.persistence.Query;
import jpms.model.Person;


/**
 *
 * @author m.elz
 */

@Singleton
public class PersonService extends AbstractService implements IPersonService {

    @Override
    public boolean save(Person person) {
        boolean isSaved;
        
        startTransaction();
        
        //using merge instead of persist, cause we have already an instance of a personGroup in the person.
        //if we use persist, we would create a new database entry of personGroup!
        //NOTE: is used for 'insert' and 'update'!
        em.merge(person);
        isSaved = true;
        
        endTransaction();
        
        return isSaved;
    }

    @Override
    public List<Person> getPersons() {
        List<Person> resultList;
        
        startTransaction();
        
        Query query = em.createNamedQuery(Person.getPersons);
        resultList = query.getResultList();
        
        endTransaction();
        
        return resultList;
    }

    @Override
    public List<Person> getPersons(boolean passive) {
        List<Person> resultList;
        
        startTransaction();
        
        Query query = em.createNamedQuery(Person.getPassivePersons);
        query.setParameter("active", !passive);
        
        resultList = query.getResultList();
        
        endTransaction();
        
        return resultList;
    }

    @Override
    public List<Person> getPersons(boolean passive, Long choirId) {
        List<Person> resultList;
        
        startTransaction();
        
        Query query = em.createNamedQuery(Person.getPassivePersonsByChoir);
        query.setParameter("active", !passive);
        query.setParameter("choirId", choirId);
        
        resultList = query.getResultList();
        
        endTransaction();
        
        return resultList;
    }

    @Override
    public List<Person> getPersons(boolean passive, Long choirId, String searchText) {
        List<Person> resultList;
        
        startTransaction();
        
        Query query = em.createNamedQuery(Person.getPassivePersonsByFilter);
        query.setParameter("active", !passive);
        query.setParameter("choirId", choirId);
        query.setParameter("searchText", "%" + searchText + "%");
                
        resultList = query.getResultList();
        
        endTransaction();
        
        return resultList;
    }
          
}
