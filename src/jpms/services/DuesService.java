package jpms.services;

import java.util.List;
import javax.persistence.Query;
import jpms.model.Fee;

/**
 *
 * @author m.elz
 */
public class DuesService extends AbstractService implements IDuesService {

    @Override
    public List<Fee> getDues(Long personId) {
        startTransaction();
        
        Query query = em.createNamedQuery(Fee.getDuesByPersonId);
        query.setParameter("personId", personId);
        
        List<Fee> dues = query.getResultList();
            
        endTransaction();
        
        return dues;
    }

    @Override
    public void addFee(Fee fee) {
        startTransaction();
        
        em.persist(fee);
        
        endTransaction();
    }

    @Override
    public void deleteFee(Fee fee) {
        startTransaction();
        
        Query query = em.createNamedQuery(Fee.getFeeById);
        query.setParameter("id", fee.getId());
        
        Fee managedFee = (Fee) query.getResultList().get(0);
        
        em.remove(managedFee);
        
        endTransaction();
    }
    
}
