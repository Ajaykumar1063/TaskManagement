package org.airtribe.assignment.taskmaster.TaskManagement.repository;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
