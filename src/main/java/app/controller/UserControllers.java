package app.controller;

import app.service.UserServices;
import app.utils.AppStarters;
import app.view.*;

public class UserControllers {

    UserServices service = new UserServices();

    public void createUser() {
        UserCreateViews createView = new UserCreateViews();
        createView.getOutput(service.createUser(createView.getData()));
        AppStarters.startApp();
    }

    public void readUsers() {
        UserReadViews readView = new UserReadViews();
        readView.getOutput(service.readUsers());
        AppStarters.startApp();
    }

    public void updateUser() {
        UserUpdateViews updateView = new UserUpdateViews();
        updateView.getOutput(service.updateUser(updateView.getData()));
        AppStarters.startApp();
    }

    public void deleteUser() {
        UserDeleteViews deleteView = new UserDeleteViews();
        deleteView.getOutput(service.deleteContact(deleteView.getData()));
        AppStarters.startApp();
    }

    public void readUserById() {
        UserReadByIdViews readByIdView = new UserReadByIdViews();
        readByIdView.getOutput(service.readContactById(readByIdView.getData()));
        AppStarters.startApp();
    }
}
