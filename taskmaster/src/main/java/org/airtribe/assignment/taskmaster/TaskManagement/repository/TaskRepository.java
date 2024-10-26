package org.airtribe.assignment.taskmaster.TaskManagement.repository;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User user);
    List<Task> findByIsCompleted(boolean isCompleted);

    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword, String keyword1);
}
