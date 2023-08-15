package com.assignment.assignmentproject.serviceImpl;

import com.assignment.assignmentproject.respositories.RedisRepositoryDAO;
import com.assignment.assignmentproject.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    private static final String TABLE_METADATA_FILE = "table_metadata.txt";
    private static final String TABLE_DATA_FILE = "table_data.txt";

    @Autowired
    RedisRepositoryDAO repositoryDAO;
    @Override
    public void executeStatements(String sqlStatements) {

        if (sqlStatements.startsWith("CREATE TABLE")) {
            createTable(sqlStatements);
        } else if (sqlStatements.startsWith("INSERT INTO")) {
            insertData(sqlStatements);
        } else {
            throw new IllegalArgumentException("Invalid SQL statement.");
        }
        repositoryDAO.saveStatement(sqlStatements);
    }

    @Override
    public void insertData(String sqlStatements) {

        String[] parts = sqlStatements.split("VALUES");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid INSERT INTO statement.");
        }

        String tableName = parts[0].replace("INSERT INTO", "").trim();
        String valuesPart = parts[1].replace("(", "").replace(")", "").trim();
        String[] values = valuesPart.split(",");

        insertData(tableName);

    }

    @Override
    public void createTable(String sqlStatements) {
        String[] parts = sqlStatements.split("\\(");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid CREATE TABLE statement.");
        }

        String tableName = parts[0].replace("CREATE TABLE", "").trim();
        String columnPart = parts[1].replace(")", "").trim();
        String[] columnDefinitions = columnPart.split(",");

    }
}
