package jpms.services;

import java.util.List;
import javax.persistence.Query;
import jpms.model.PersonGroup;

/**
 *
 * @author m.elz
 */
public class ChoirService extends AbstractService implements IChoirService {

    @Override
    public boolean checkChoirName(String choirName) {
        boolean exsistsChoir = false;
        
        startTransaction();
        
        Query query = em.createNamedQuery(PersonGroup.findGroupByName);
        query.setParameter("groupName", choirName);
        
        List<PersonGroup> users = query.getResultList();
        
        if(users.size() == 1){
            exsistsChoir = true;
        }       
        
        endTransaction();
        
        return exsistsChoir;
    }

    @Override
    public boolean createChoir(String choirName) {
        boolean created = false;             
        
        if(!checkChoirName(choirName)){
            startTransaction();
            
            PersonGroup personGroup = new PersonGroup();
            personGroup.setName(choirName);

            em.persist(personGroup);

            created = true;

            endTransaction();
        }            
        
        return created;
    }

    @Override
    public List<PersonGroup> getChoirs() {
        List<PersonGroup> resultList;
        
        startTransaction();
        
        Query query = em.createNamedQuery(PersonGroup.getAll);
        resultList = (List<PersonGroup>) query.getResultList();
        
        endTransaction();
        
        return resultList;
    }

    @Override
    public boolean rename(Long id, String newChoirName) {
        boolean renamed;
        
        startTransaction();
        
        Query query = em.createNamedQuery(PersonGroup.findGroupById);
        query.setParameter("groupId", id);
        
        PersonGroup group = (PersonGroup) query.getSingleResult();
        
        group.setName(newChoirName);
        
        em.persist(group);
        renamed = true;
        
        endTransaction();
        
        return renamed;
    }
    
}
