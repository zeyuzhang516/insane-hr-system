package entity;

public interface Project extends Organization{
    boolean addTask(Task task);

    boolean removeTask(Task task);

}
