import check_profile_validation.*;
import controller.CheckProfileController;
import data_access.CheckProfileDataAccess;
import data_access.CheckProfileIMDataAccess;
import data_access.CheckProfileUserIDMap;
import entity.Curr;
import presenter.CheckProfilePresenter;
import presenter.IViewModel;
import ui.ScreenBuilder;
import view_model.Table;
import view_model.ViewModel;

import javax.swing.*;

public class CheckProfileMain {
    public static void main(String[] args) {
        CheckProfileIGateway gateway = new CheckProfileIMDataAccess();
        CheckProfileOutputBoundary presenter = new CheckProfilePresenter(new ViewModel());
        CheckProfileController controller = new CheckProfileController(presenter, gateway);
        Curr.setUser(gateway.getUserByUid(CheckProfileUserIDMap.headId));
        controller.create(CheckProfileUserIDMap.headId, CheckProfileUserIDMap.headId);
        presenter.showFrame();


    }
}
