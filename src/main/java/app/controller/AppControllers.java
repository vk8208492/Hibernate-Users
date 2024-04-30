package app.controller;

import app.services.AppServices;
import app.views.AppViews;

public class AppControllers {

    AppViews view;
    AppServices service;

    public AppControllers(AppViews view, AppServices service) {
        this.view = view;
        this.service = service;
    }

    public void runApp() {
        service.filterChoice(view.chooseOption());
    }
}
