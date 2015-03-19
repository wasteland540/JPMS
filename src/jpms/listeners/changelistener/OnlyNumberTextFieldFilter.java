package jpms.listeners.changelistener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author m.elz
 */
public class OnlyNumberTextFieldFilter implements ChangeListener<Number> {

    private final TextField field;
    
    public OnlyNumberTextFieldFilter(TextField field) {
        this.field = field;
    }
    
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
        if(newValue.intValue() > oldValue.intValue()){
            char ch = field.getText().charAt(oldValue.intValue());

            //Check if the new character is the number or other's
            if(!(ch >= '0' && ch <= '9' || ch == ',' || ch == '.')){       
                 //if it's not number then just setText to previous one
                 field.setText(field.getText().substring(0,field.getText().length()-1)); 
            }
       }
    }
    
}
