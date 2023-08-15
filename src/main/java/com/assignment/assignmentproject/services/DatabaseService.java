package com.assignment.assignmentproject.services;

public interface DatabaseService {
    public void executeStatements(String sqlStatements);

    void insertData(String sqlStatements);

    void createTable(String sqlStatements);

}
