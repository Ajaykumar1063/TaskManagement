package org.airtribe.assignment.taskmaster.TaskManagement.service;

import org.airtribe.assignment.taskmaster.TaskManagement.entity.Team;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.TeamMember;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TeamMemberRepository;
import org.airtribe.assignment.taskmaster.TaskManagement.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    /**
     *
     * @param team
     * @return
     */
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    /**
     *
     * @param teamMember
     * @return
     */
    public List<TeamMember> addTeamMember(List<TeamMember> teamMember) {
        return teamMemberRepository.saveAll(teamMember);
    }
}
