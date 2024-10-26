package org.airtribe.assignment.taskmaster.TaskManagement.service;


import org.airtribe.assignment.taskmaster.TaskManagement.entity.Attachment;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.AttachmentRepository;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class AttachmentService {

    @Autowired
   private TaskRepository taskRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment saveAttachment(Task task, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        // Create and save attachment record
        Attachment attachment = new Attachment();
        attachment.setTask(task);
        attachment.setFileName(fileName);
        attachment.setContent(Arrays.toString(file.getBytes()));
        attachment.setFileType(file.getContentType());
        return attachmentRepository.save(attachment);
    }

    public List<Attachment> getAttachmentsByTask(long taskId) {
        Task task = taskRepository.findById(taskId).get();
        return attachmentRepository.findByTask(task);
    }


}

