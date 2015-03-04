package jpms.services;

import com.google.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author m.elz
 */
public abstract class AbstractService {
    
    @Inject
    private EntityManagerFactory entityManagerFactory;
    
    protected EntityManager em;
    
    protected void startTransaction(){
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
    }
    
    protected void endTransaction(){
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
