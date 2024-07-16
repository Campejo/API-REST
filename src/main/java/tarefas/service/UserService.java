package tarefas.service;

import tarefas.model.Task;
import tarefas.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Long id);

    User createUser(User userToCreate);

    Task addTaskToUser(Long id, Task taskToCreate);

    User updateUser(Long id, User user);

    Task updateTask(Long userId, Long taskId, Task taskDetails);

    void deleteUser(Long id);
}