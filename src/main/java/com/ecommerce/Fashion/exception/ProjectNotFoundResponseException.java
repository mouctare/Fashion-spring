package com.ecommerce.Fashion.exception;

public class ProjectNotFoundResponseException {

    private String projectNotFound;

    public ProjectNotFoundResponseException(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}

