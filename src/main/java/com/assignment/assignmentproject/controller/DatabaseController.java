package com.assignment.assignmentproject.controller;

import com.assignment.assignmentproject.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseController {
    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/execute")
    public ResponseEntity<String> executeStatement(@RequestBody String sqlStatement) {
        try {
            databaseService.executeStatements(sqlStatement);
            return ResponseEntity.ok("Statement executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
