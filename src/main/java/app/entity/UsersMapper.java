package app.entity;

import java.util.Map;

public class UsersMapper {

    public Users mapUserData(Map<String, String> data) {
        Users users = new Users();
        if (data.containsKey("id"))
            users.setId(Long.parseLong(data.get("id")));
        if (data.containsKey("name"))
            users.setName(data.get("name"));
        if (data.containsKey("email"))
            users.setEmail(data.get("email"));
        return users;
    }
}
