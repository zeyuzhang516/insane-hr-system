package check_profile_validation;

import controller.CheckProfileController;
import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presenter.CheckProfilePresenter;
import presenter.IViewModel;
import ui.ScreenBuilder;
import view_model.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

class CheckProfileInteractorTest {


    @BeforeEach
    void setUp() {
        Curr.setUser(new CommonUser(10));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkUserProfile() {
        CheckProfileIGateway gateway = new CheckProfileTestDataAccess();

        IViewModel viewModel = new ViewModel();
        CheckProfileOutputBoundary presenter = new CheckProfilePresenter(viewModel);
        System.out.println(viewModel.toString());
        CheckProfileInputBoundary interactor = new CheckProfileInteractor(gateway,presenter);
        CheckProfileController controller = new CheckProfileController(interactor);
        controller.create(10,10);
        System.out.println(viewModel.toString());

    }

    @Test
    void checkVisualLevel() {
        CheckProfileIGateway gateway = new CheckProfileTestDataAccess();

        IViewModel viewModel = new ViewModel();
        CheckProfileOutputBoundary presenter = new CheckProfilePresenter(viewModel);
        CheckProfileInputBoundary interactor = new CheckProfileInteractor(gateway,presenter);
        CheckProfileController controller = new CheckProfileController(interactor);
        CheckProfileResponseModel responseModel = interactor.checkUserProfile(new CheckUserFileRequestModel(0,1));
        System.out.println(responseModel);
        assertEquals(responseModel.getVisualLevel(), VisualLevel.ONLY_FACE);
        assertEquals(responseModel.getRelation(), RelativeRelation.NO_RELATION);
    }

    @Test
    void checkVisualLevel2() {
        CheckProfileIGateway gateway = new CheckProfileTestDataAccess();

        IViewModel viewModel = new ViewModel();
        CheckProfileOutputBoundary presenter = new CheckProfilePresenter(viewModel);
        CheckProfileInputBoundary interactor = new CheckProfileInteractor(gateway,presenter);
        CheckProfileController controller = new CheckProfileController(interactor);
        CheckProfileResponseModel responseModel = interactor.checkUserProfile(new CheckUserFileRequestModel(0,0));
        System.out.println(responseModel);
        assertEquals(responseModel.getVisualLevel(), VisualLevel.ONLY_FACE);
        assertEquals(responseModel.getRelation(), RelativeRelation.IS_EMPLOYEE_SELF);
    }
    @Test
    void checkOrgProfile() {
    }

    @Test
    void setTables() {
    }

    @Test
    void getOrgnizations() {
    }

    @Test
    void getUncommonOrgnizations() {
    }
}