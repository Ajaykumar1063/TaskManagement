package org.airtribe.assignment.taskmaster.TaskManagement.controller;


import org.airtribe.assignment.taskmaster.TaskManagement.entity.Team;
import org.airtribe.assignment.taskmaster.TaskManagement.entity.TeamMember;
import org.airtribe.assignment.taskmaster.TaskManagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PostMapping("/add-members")
    public List<TeamMember> addTeamMember(@RequestBody List<TeamMember> teamMember) {
        return teamService.addTeamMember(teamMember);
    }
}

