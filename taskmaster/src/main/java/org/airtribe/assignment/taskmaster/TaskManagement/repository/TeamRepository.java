package org.airtribe.assignment.taskmaster.TaskManagement.repository;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
