package org.airtribe.assignment.taskmaster.TaskManagement.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.airtribe.assignment.taskmaster.auth.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {

    private String title;

    private String description;

    private LocalDate dueDate;

    private boolean status;

    private String createdBy;

    private String assignedTo;

    private Long teamId;

}
