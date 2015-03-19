package jpms.services;

import java.util.List;
import jpms.model.Fee;

/**
 *
 * @author m.elz
 */
public interface IDuesService {
    
    List<Fee> getDues(Long personId);
    
    void addFee(Fee fee);
    
    void deleteFee(Fee fee);
    
}
