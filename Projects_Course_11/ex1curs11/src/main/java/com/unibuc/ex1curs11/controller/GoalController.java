package com.unibuc.ex1curs11.controller;

import com.unibuc.ex1curs11.dto.GoalDTO;
import com.unibuc.ex1curs11.service.GoalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping("/{userId}")
    @ApiOperation("Create a new financial goal for a user")
    public ResponseEntity<GoalDTO> createGoal(@PathVariable Long userId, @RequestBody GoalDTO goalDTO) {
        GoalDTO created = goalService.createGoal(userId, goalDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{userId}")
    @ApiOperation("Get all goals for a specific user")
    public ResponseEntity<List<GoalDTO>> getUserGoals(@PathVariable Long userId) {
        List<GoalDTO> goals = goalService.getUserGoals(userId);
        return ResponseEntity.ok(goals);
    }
}
