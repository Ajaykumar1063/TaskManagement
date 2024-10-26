package org.airtribe.assignment.taskmaster.TaskManagement.repository;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Attachment;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTask(Task task);
}
