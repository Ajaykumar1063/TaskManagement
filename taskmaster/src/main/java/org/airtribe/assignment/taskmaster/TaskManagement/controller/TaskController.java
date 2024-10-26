package org.airtribe.assignment.taskmaster.TaskManagement.controller;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.TaskManagement.exception.TaskManagementException;
import org.airtribe.assignment.taskmaster.TaskManagement.model.TaskModel;
import org.airtribe.assignment.taskmaster.TaskManagement.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     *
     * @param taskModel
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskModel taskModel) {
        logger.info("Creating a new task with title: {}", taskModel.getTitle());
        try {
           taskService.createTask(taskModel);
            return ResponseEntity.ok("Task created successfully");
        } catch (Exception e) {
            logger.error("Error while creating task: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create task");
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<?> getUserTasks() {
        logger.info("Fetching tasks assigned to current user {}");
        try {
            List<Task> tasks = taskService.getTasksByUser();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            logger.error("Error fetching user tasks: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch tasks for user");
        }
    }

    // View All Tasks

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        logger.info("Fetching all tasks");
        try {
            List<TaskModel> tasks = taskService.getAllTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            logger.error("Error fetching all tasks: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch all tasks");
        }
    }

    // Update Task
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskModel task) {
        logger.info("Updating task with ID: {}", taskId);
        try {
            Task updatedTask = taskService.updateTask(taskId, task);
            return ResponseEntity.ok(updatedTask);
        } catch (TaskManagementException e) {
            logger.error("Task not found with ID: {}", taskId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating task: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update task");
        }
    }

    // Delete Task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        logger.info("Deleting task with ID: {}", taskId);
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (TaskManagementException e) {
            logger.error("Task not found with ID: {}", taskId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error deleting task: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete task");
        }
    }

    // Filter Tasks
    @GetMapping("/filter")
    public ResponseEntity<?> filterTasks(@RequestParam(required = false) Boolean isCompleted) {
        logger.info("Filtering tasks by completion status: {}", isCompleted);
        try {
            List<Task> tasks = taskService.filterTasksByStatus(isCompleted);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            logger.error("Error filtering tasks: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to filter tasks");
        }
    }

    // Search Tasks by Title/Description
    @GetMapping("/search")
    public ResponseEntity<?> searchTasks(@RequestParam String keyword) {
        logger.info("Searching tasks by keyword: {}", keyword);
        try {
            List<Task> tasks = taskService.searchTasks(keyword);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            logger.error("Error searching tasks: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search tasks");
        }
    }
}

