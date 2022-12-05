package complete_task;

import entity.*;

import java.util.Objects;
import java.util.UUID;

public class CompleteTaskInteractor implements CompleteTaskInputBoundary{
    private final CompleteTaskDsGateway completeGateway;
    private final CompleteTaskOutputBoundary completeOutputBoundary;

    public CompleteTaskInteractor(CompleteTaskDsGateway completeGateway,
                                  CompleteTaskOutputBoundary completeOutputBoundary) {
        this.completeGateway = completeGateway;
        this.completeOutputBoundary = completeOutputBoundary;
    }

    @Override
    public CompleteTaskResponseModel create(CompleteTaskRequestModel requestModel) {
        Task task = requestModel.getTask();
        UUID oid = task.getOid();
        User user = requestModel.getUser();

        if (Objects.equals(requestModel.getTask().getState(), "CLOSED")) {
            return completeOutputBoundary.prepareFailureView("Task already closed!");
        }

        task.setState("CLOSED");

        CompleteTaskResponseModel completeResponseModel = new CompleteTaskResponseModel(oid,
                "Submitted successfully!");
        return completeOutputBoundary.prepareSuccessView(completeResponseModel);
    }
}
