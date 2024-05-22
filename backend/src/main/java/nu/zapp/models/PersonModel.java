package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionInvalidInput;
import org.springframework.stereotype.Component;

@Component
public class PersonModel {

    public String postalCodeCheck(String postalcode){
        // This function will need to go to a generic place because both employees and customers can use it
        if(!postalcode.matches("^\\d{4}[a-zA-Z]{2}")){
            throw new ExceptionInvalidInput("postcode");
        }
        String postalcodeCap = postalcode.substring(0,4) + postalcode.substring(4, 6).toUpperCase();
        return postalcodeCap;
    }

}
