package presenter;

import check_profile_validation.CheckProfileResponseModel;
import check_profile_validation.FileType;
import entity.RelativeRelation;

import java.util.LinkedList;
import java.util.List;

public class ControllerFactory {
    public ControllerFactory() {
    }


    Controllers[] getUseCases(CheckProfileResponseModel responseModel) {
        List<Controllers> controllers = new LinkedList<>();

        for (Controllers controller : Controllers.values()) {
            if (functionIsInNeed(controller, responseModel)) {
                controllers.add(controller);
            }
        }
        return controllers.toArray(new Controllers[0]);
    }


    public Controllers[] getAllUseCases() {
        return new Controllers[0];
    }
    private boolean functionIsInNeed(Controllers controllers, CheckProfileResponseModel responseModel) {
        RelativeRelation relation = responseModel.getRelation();
        switch (controllers) {
            case CREATE_TASK: return relation == RelativeRelation.IS_P_M_OF;
            case LEAVE_REQUEST: return relation == RelativeRelation.IS_EMPLOYEE_SELF;
            case CREATE_PROJECT:return relation == RelativeRelation.IS_DPT_HEAD_SELF;
            case ENROLL_EMPLOYEE: return relation == RelativeRelation.IS_DPT_HEAD_SELF;
            case SALARY_CALCULATOR: return relation == RelativeRelation.IS_EMPLOYEE_SELF || relation == RelativeRelation.IS_PM_SELF;
            case COMPLETE_PROJECT: return relation == RelativeRelation.IS_HEAD_OF;
            case COMPLETE_TASK: return relation == RelativeRelation.IS_MEMBER_OF;
            case EVALUATE_TASK: return relation == RelativeRelation.IS_HEAD_OF && responseModel.getFileType() == FileType.EVALUATION_TASK_FILE;
            case APPROVE_LEAVE_TASK: return relation == RelativeRelation.IS_MEMBER_OF && responseModel.getFileType() == FileType.LEAVE_REQUEST_TASK_FILE;

        }
        return false;
    }
}
