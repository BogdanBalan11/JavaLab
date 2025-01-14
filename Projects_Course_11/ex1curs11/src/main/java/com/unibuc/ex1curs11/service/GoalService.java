package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.dto.GoalDTO;
import com.unibuc.ex1curs11.model.Goal;
import com.unibuc.ex1curs11.model.User;
import com.unibuc.ex1curs11.repository.GoalRepository;
import com.unibuc.ex1curs11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    public GoalDTO createGoal(Long userId, GoalDTO goalDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = new Goal();
        goal.setGoalName(goalDTO.getGoalName());
        goal.setTargetAmount(goalDTO.getTargetAmount());
        goal.setSavedAmount(goalDTO.getSavedAmount());
        goal.setDeadline(goalDTO.getDeadline());
        goal.setUser(user);

        goalRepository.save(goal);
        return convertToDTO(goal);
    }

    public List<GoalDTO> getUserGoals(Long userId) {
        return goalRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GoalDTO convertToDTO(Goal goal) {
        GoalDTO dto = new GoalDTO();
        dto.setId(goal.getId());
        dto.setGoalName(goal.getGoalName());
        dto.setTargetAmount(goal.getTargetAmount());
        dto.setSavedAmount(goal.getSavedAmount());
        dto.setDeadline(goal.getDeadline());
        dto.setUserId(goal.getUser().getId());
        return dto;
    }
}
