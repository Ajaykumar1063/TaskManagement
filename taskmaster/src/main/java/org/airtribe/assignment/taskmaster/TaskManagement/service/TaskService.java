package org.airtribe.assignment.taskmaster.TaskManagement.service;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Team;
import org.airtribe.assignment.taskmaster.TaskManagement.exception.TaskManagementException;
import org.airtribe.assignment.taskmaster.TaskManagement.model.TaskModel;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TaskRepository;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TeamRepository;
import org.airtribe.assignment.taskmaster.auth.entity.User;
import org.airtribe.assignment.taskmaster.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamRepository teamRepository;

    /**
     *
     * @param task
     * @return
     */
    public String createTask(TaskModel task) throws Exception {
        logger.info("Saving a new task with title: {}", task.getTitle());
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setDueDate(task.getDueDate());
        newTask.setCreatedBy(userService.getCurrentUser());
        newTask.setAssignedTo(userService.getUserByEmail(task.getAssignedTo()));
        newTask.setCreatedAt(LocalDate.now());
        if (task.getTeamId() != null) {
            Team team = teamRepository.findById(task.getTeamId()).orElse(null);
            if (team != null) {
                newTask.setTeam(team);
            }
        }
        taskRepository.save(newTask);
        return  "Task created successfully";
    }

    /**
     *
     * @return
     */
    public List<TaskModel> getAllTasks() {
        logger.info("Retrieving all tasks from the repository");
        List<Task> tasks = taskRepository.findAll();
        List<TaskModel> taskModels = new ArrayList<>();
        for (Task task : tasks) {
            taskModels.add(new TaskModel(task.getTitle(),task.getDescription(),task.getDueDate(),task.getIsCompleted(),task.getCreatedBy().getFirstName(),task.getAssignedTo().getFirstName(),task.getTeam().getTeamId()));
        }
        return taskModels;
    }

    /**
     *
     * @return
     * @throws TaskManagementException
     */
    public List<Task> getTasksByUser() throws Exception {
        User user = userService.getCurrentUser();
        logger.info("Finding tasks assigned to user ID: {}", user.getUserId());
        return taskRepository.findByAssignedTo(user);
    }

    /**
     *
     * @param taskId
     * @param updatedTask
     * @return
     * @throws TaskManagementException
     */
    public Task updateTask(Long taskId, TaskModel updatedTask) throws TaskManagementException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskManagementException("Task not found with ID: " + taskId));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        if (updatedTask.isStatus()){
            task.setIsCompleted(true);
        }
        task.setAssignedTo(userService.getUserByEmail(updatedTask.getAssignedTo()));
        if (updatedTask.getTeamId() != null) {
            Team team = teamRepository.findById(updatedTask.getTeamId()).orElse(null);
            if (team != null) {
                task.setTeam(team);
            }
        }
        logger.info("Updating task with ID: {}", taskId);
        return taskRepository.save(task);
    }

    /**
     *
     * @param id
     * @throws TaskManagementException
     */
    public void deleteTask(Long id) throws TaskManagementException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskManagementException("Task not found with ID: " + id));
        taskRepository.delete(task);
        logger.info("Deleted task with ID: {}", id);
    }

    /**
     *
     * @param isCompleted
     * @return
     */
    public List<Task> filterTasksByStatus(Boolean isCompleted) {
        logger.info("Filtering tasks by completion status: {}", isCompleted);
        return taskRepository.findByIsCompleted(isCompleted);
    }

    /**
     *
     * @param keyword
     * @return
     */
    public List<Task> searchTasks(String keyword) {
        logger.info("Searching tasks with keyword: {}", keyword);
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
}


