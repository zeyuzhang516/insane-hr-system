package evaluate_task;

public interface EvaluateTaskOutputBoundary {
    EvaluateTaskResponseModel prepareSuccessView(EvaluateTaskResponseModel response);

    EvaluateTaskResponseModel prepareFailureView(String error);
}
