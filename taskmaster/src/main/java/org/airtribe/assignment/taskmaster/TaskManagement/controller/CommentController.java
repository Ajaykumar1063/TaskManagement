package org.airtribe.assignment.taskmaster.TaskManagement.controller;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Comment;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.TaskManagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/task/{taskId}")
    public List<Comment> getCommentsForTask(@PathVariable long taskId) {
        return commentService.getCommentsForTask(taskId);
    }
}

