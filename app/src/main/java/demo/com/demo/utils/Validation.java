package demo.com.demo.utils;

import android.content.Context;
import android.widget.EditText;


public final class Validation {


     Context context;


    public static boolean validation (EditText editText){
        String pass =editText.getText().toString();
        if (pass.length() ==0 ){
            editText.setError("Field can not be blank");
            return false;
        }
        if (pass.length() < 4 ){
            editText.setError("Length must be atleast 4 characters");
            return false;
        }
        if (pass.matches(".*[!@#$%^&*+=?-].*")) {
            editText.setError("No special character allowed");
            return false;
        }
        if ( pass.contains(" ") ) {
            editText.setError("Space is not allowed");
            return false;
        }
        return true;
    }


}
