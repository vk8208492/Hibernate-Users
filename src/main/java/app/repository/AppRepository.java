package app.repository;

import java.util.List;
import java.util.Optional;

public interface AppRepository<User> {


    String create(User user);

    Optional<List<User>> read();

    String update(User user);

    String delete(Long id);

    Optional<User> readById(Long id);
}
