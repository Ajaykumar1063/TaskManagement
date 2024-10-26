package org.airtribe.assignment.taskmaster.TaskManagement.repository;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Comment;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
}
