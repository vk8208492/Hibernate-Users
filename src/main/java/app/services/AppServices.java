package app.services;

import app.controller.UserControllers;
import app.exceptions.OptionExceptions;
import app.utils.AppStarters;
import app.utils.Constant;
import app.views.AppViews;

public class AppServices {

    UserControllers controller = new UserControllers();

    public void filterChoice(int option) {
        switch (option) {
            case 1 -> controller.createUser();
            case 2 -> controller.readUsers();
            case 3 -> controller.updateUser();
            case 4 -> controller.deleteUser();
            case 5 -> controller.readUserById();
            case 0 -> new AppViews().getOutput(Integer.toString(option));
            default -> {
                try {
                    throw new OptionExceptions(Constant.INCORRECT_OPTION_MSG);
                } catch (OptionExceptions e) {
                    new AppViews().getOutput(e.getMessage());
                    AppStarters.startApp();
                }
            }
        }
    }
}
