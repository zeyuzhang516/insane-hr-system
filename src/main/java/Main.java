import controller.LoginController;
import data_access.LoginDataAccess;
import login.LoginDSGateway;
import login.LoginInputBoundary;
import login.LoginInteractor;
import presenter.LoginPresenter;
import presenter.LoginResponseFormatter;
import ui.LoginPromptScreen;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {


        JFrame mainFrame = new JFrame("Insane HR System");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        mainFrame.add(screens);

        LoginPresenter loginPresenter = new LoginResponseFormatter();
        LoginDataAccess loginDataAccess = new LoginDataAccess();
        LoginInputBoundary interactor = new LoginInteractor(loginDataAccess, loginPresenter);
        LoginController loginController = new LoginController(interactor);

        // TODO intialize data access

        // TODO pass data access to login interactor

        // TODO initialize feature interactors, controllers

        // Build the GUI, plugging in the parts
        JPanel loginScreen = new LoginPromptScreen(loginController, screens, cardLayout);
        screens.add(loginScreen, "welcome");
        cardLayout.show(screens, "login");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
