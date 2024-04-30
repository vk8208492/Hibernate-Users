package app.services;

import app.entity.Users;
import app.exceptions.UserExceptions;
import app.repository.impl.UserRepository;
import app.utils.Constant;
import app.entity.UsersMapper;
import app.utils.UserValidators;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
public class UserServices {

    UserRepository repository = new UserRepository();

    public String createUser(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidators().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                return e.getErrors(errors);
            }
        }
        return repository.create(new UsersMapper().mapUserData(data));
    }

    public String readUsers() {

        Optional<List<Users>> optional = repository.read();

        if (optional.isPresent()) {

            List<Users> list = optional.get();

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
            } else return Constant.DATA_ABSENT_MSG;
        } else return Constant.DATA_ABSENT_MSG;
    }

    public String updateUser(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidators().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                return e.getErrors(errors);
            }
        }
        return repository.update(new UsersMapper().mapUserData(data));
    }

    public String deleteContact(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidators().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                return e.getErrors(errors);
            }
        }
        return repository.delete(new UsersMapper().mapUserData(data).getId());
    }

    public String readContactById(Map<String, String> data) {
        Map<String, String> errors =
                new UserValidators().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                return e.getErrors(errors);
            }
        }

        Optional<Users> optional =
                repository.readById(Long.parseLong(data.get("id")));

        if (optional.isPresent()) {

            Users users = optional.get();
            return "\nUSER: " + users + "\n";
        } else return Constant.DATA_ABSENT_MSG;
    }
}
