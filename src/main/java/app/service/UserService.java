package app.service;

import app.entity.User;
import app.exception.UserException;
import app.repository.impl.UserRepository;
import app.utils.Constants;
import app.entity.UserMapper;
import app.utils.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
public class UserService {

    UserRepository repository = new UserRepository();

    public String createUser(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserException("Check inputs", errors);
            } catch (UserException e) {
                return e.getErrors(errors);
            }
        }
        return repository.create(new UserMapper().mapUserData(data));
    }

    public String readUsers() {

        Optional<List<User>> optional = repository.read();

        if (optional.isPresent()) {

            List<User> list = optional.get();

            if (!list.isEmpty()) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder stringBuilder = new StringBuilder();
                list.forEach((user) ->
                        stringBuilder.append(count.incrementAndGet())
                                .append(") ")
                                .append(user.toString())
                                .append("\n")
                );
                return "\nUsers:\n" + stringBuilder;
            } else return Constants.DATA_ABSENT_MSG;
        } else return Constants.DATA_ABSENT_MSG;
    }

    public String updateUser(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserException("Check inputs", errors);
            } catch (UserException e) {
                return e.getErrors(errors);
            }
        }
        return repository.update(new UserMapper().mapUserData(data));
    }

    public String deleteContact(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserException("Check inputs", errors);
            } catch (UserException e) {
                return e.getErrors(errors);
            }
        }
        return repository.delete(new UserMapper().mapUserData(data).getId());
    }

    public String readContactById(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserException("Check inputs", errors);
            } catch (UserException e) {
                return e.getErrors(errors);
            }
        }

        Optional<User> optional =
                repository.readById(Long.parseLong(data.get("id")));

        if (optional.isPresent()) {

            User user = optional.get();
            return "\nUSER: " + user + "\n";
        } else return Constants.DATA_ABSENT_MSG;
    }
}
