package app.repository;

import app.entity.Users;

import java.util.List;
import java.util.Optional;

public interface AppRepositorys<User> {
    String create(Users user);

    Optional<List<Users>> read();

    String update(Users user);

    String delete(Long id);

    Optional<Users> readById(Long id);
}
