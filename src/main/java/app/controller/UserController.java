package app.controller;

import app.service.UserService;
import app.utils.AppStarter;
import app.view.*;

public class UserController {

    UserService service = new UserService();

    public void createUser() {
        UserCreateView createView = new UserCreateView();
        createView.getOutput(service.createUser(createView.getData()));
        AppStarter.startApp();
    }

    public void readUsers() {
        UserReadView readView = new UserReadView();
        readView.getOutput(service.readUsers());
        AppStarter.startApp();
    }

    public void updateUser() {
        UserUpdateView updateView = new UserUpdateView();
        updateView.getOutput(service.updateUser(updateView.getData()));
        AppStarter.startApp();
    }

    public void deleteUser() {
        UserDeleteView deleteView = new UserDeleteView();
        deleteView.getOutput(service.deleteContact(deleteView.getData()));
        AppStarter.startApp();
    }

    public void readUserById() {
        UserReadByIdView readByIdView = new UserReadByIdView();
        readByIdView.getOutput(service.readContactById(readByIdView.getData()));
        AppStarter.startApp();
    }
}
