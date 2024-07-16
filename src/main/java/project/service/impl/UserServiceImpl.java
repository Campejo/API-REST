package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.Task;
import project.model.User;
import project.repository.TasksRepository;
import project.repository.UserRepository;
import project.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TasksRepository tasksRepository;

    public UserServiceImpl(UserRepository userRepository, TasksRepository tasksRepository) {
        this.userRepository = userRepository;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User createUser(User userToCreate) {
        if (userRepository.existsByName(userToCreate.getName())) {
            throw new IllegalArgumentException("This user already exists");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public Task addTaskToUser(Long userId, Task taskToCreate) {
        var userById = userRepository.findById(userId);
        if (!userById.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User user = userById.get();
        user.addTask(taskToCreate);
        userRepository.save(user);

        return taskToCreate;
    }


    @Override
    public User updateUser(Long id, User userUpdate) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        return userRepository.save(user);
    }

    @Override
    public Task updateTask(Long userId, Long taskId, Task taskDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = user.getTasks().stream().filter(t -> t.getId().equals(taskId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.getCompleted());
        return tasksRepository.save(task);
    }


    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("This ID doesn't exists");
        }
        userRepository.deleteById(id);
    }
}
