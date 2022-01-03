package validation;

import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean  Validate(LinkedHashMap<TextField,Pattern> map){
        for (TextField textField:map.keySet()
             ) {
            Pattern pattern=map.get(textField);
            if (!pattern.matcher(textField.getText()).matches()){
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("-fx-border-color: green");
        }
        return true;
    }
}
