package org.airtribe.assignment.taskmaster.TaskManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskManagementException extends RuntimeException {
    public TaskManagementException(String message) {
        super(message);
    }
}
