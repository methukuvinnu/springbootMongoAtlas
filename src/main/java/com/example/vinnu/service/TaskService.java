package com.example.vinnu.service;

import com.example.vinnu.model.Task;
import com.example.vinnu.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskByTaskId(String taskId){
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTaskFindBySeverity(int severity){
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> getTaskFindByAssignee(String assignee){
        return taskRepository.findByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest){
        Task existingTask= taskRepository.findById(taskRequest.getTaskId()).get();
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        existingTask.setDescription(taskRequest.getDescription());
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        return taskId +"Task Deleted successfully from Dashboard";
    }
}
