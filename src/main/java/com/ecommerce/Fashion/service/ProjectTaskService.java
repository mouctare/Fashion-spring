package com.ecommerce.Fashion.service;

import com.ecommerce.Fashion.entity.Backlog;
import com.ecommerce.Fashion.entity.Project;
import com.ecommerce.Fashion.entity.ProjectTask;
import com.ecommerce.Fashion.exception.ProjectNotFoundException;
import com.ecommerce.Fashion.repository.BacklogRepository;
import com.ecommerce.Fashion.repository.ProjectRepository;
import com.ecommerce.Fashion.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){


        try {


            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);

            Integer backlogSequence = backlog.getPTSequence();

            backlogSequence++;

            backlog.setPTSequence(backlogSequence);

            projectTask.setProjectSequence(backlog.getProjectIdentifier()+"-"+backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
                projectTask.setStatus("TO_DO");
            }

            if(projectTask.getPriority()==null){
                projectTask.setPriority(3);
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project not Found");
        }

    }

    public Iterable<ProjectTask>findBacklogById(String id){
        Project project = projectRepository.findByProjectIdentifier(id);

        if(project == null){
            throw new ProjectNotFoundException("Project with ID: '"+id+"' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findProjectTaskByProjectSquence(String backlog_id , String sequence){

        //make sure are searching on the right backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog == null){
            throw  new ProjectNotFoundException("Project with ID: '"+backlog_id+"' does not exist");
        }

        //make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task: '"+sequence+"' not Found");
        }

        //make sure that the backlog/project id in the path correspons to the right project
        //assurez-vous que l'identifiant du backlog/projet dans le chemin correspond au bon projet
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task: '"+sequence+"' does not exist in project: '"+backlog_id);
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String baclog_id, String projectTask_id){
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTask_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }
    //Update project task

    //find existing project task

    //replace it with updated task

    //save update
}