package app.utils;

import app.controller.AppControllers;
import app.services.AppServices;
import app.views.AppViews;

public class AppStarters {

    public static void startApp() {
        AppServices service = new AppServices();
        AppViews view = new AppViews();
        AppControllers controller = new AppControllers(view, service);
        controller.runApp();
    }
}
