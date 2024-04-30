package app.repository;

import app.entity.User;

import java.util.List;
import java.util.Optional;

public interface AppRepository<User> {
    String create(app.entity.User user);

    Optional<List<app.entity.User>> read();

    String update(app.entity.User user);

    String delete(Long id);

    Optional<app.entity.User> readById(Long id);
}
