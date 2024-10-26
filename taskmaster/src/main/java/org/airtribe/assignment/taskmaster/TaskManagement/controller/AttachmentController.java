package org.airtribe.assignment.taskmaster.TaskManagement.controller;


import org.airtribe.assignment.taskmaster.TaskManagement.entity.Attachment;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TaskRepository;
import org.airtribe.assignment.taskmaster.TaskManagement.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private TaskRepository taskService;

    @PostMapping("/upload/{taskId}")
    public Attachment uploadAttachment(@PathVariable Long taskId, @RequestParam("file") MultipartFile file) throws IOException {
        Task task = taskService.findById(taskId).orElse(null);
        return attachmentService.saveAttachment(task, file);
    }

    @GetMapping("/task/{taskId}")
    public List<Attachment> getAttachmentsByTask(@PathVariable Long taskId) {
        Task task = taskService.findById(taskId).orElse(null);
        return attachmentService.getAttachmentsByTask(taskId);
    }
}

