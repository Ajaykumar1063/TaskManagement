package org.airtribe.assignment.taskmaster.TaskManagement.service;


import org.airtribe.assignment.taskmaster.TaskManagement.entity.Comment;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.CommentsRepository;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentsRepository commentRepository;
    @Autowired
    private TaskRepository taskRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        return commentRepository.findByTask(task);
    }
}

