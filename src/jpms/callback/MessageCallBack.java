package jpms.callback;

import jpms.messaging.ViewModelMessage;

/**
 *
 * @author m.elz
 */
public interface MessageCallBack {
    
    void execute(ViewModelMessage message);
    
}
