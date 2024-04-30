package app.utils;

import java.util.HashMap;
import java.util.Map;
public class UserValidatorss {

    public Map<String, String> validateUserData(Map<String, String> data) {

        Map<String, String> errors = new HashMap<>();

        if (data.containsKey("id") & AppValidators.isIdValid(data.get("id")))
            errors.put("id", Constant.WRONG_ID_MSG);

        if (data.containsKey("name")) {
            if (data.get("name") != null & data.get("name").isEmpty())
                errors.put("name", Constant.INPUT_REQ_MSG);
        }

        if (data.containsKey("email") & AppValidators.isEmailValid(data.get("email")))
            errors.put("email", Constant.WRONG_EMAIL_MSG);

        return errors;
    }
}
