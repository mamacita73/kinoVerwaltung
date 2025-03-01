package com.kino.controller;

import com.kino.command.Command;
import com.kino.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/saal")
public class SaalCommandController {

    @Autowired
    private CommandFactory commandFactory;

    @PostMapping("/anlegen")
    @CrossOrigin
    public ResponseEntity<?> handleSaalCommand(@RequestBody Map<String, Object> body) throws Exception {
        String commandType = (String) body.get("command");
        Command<?> cmd = commandFactory.createCommand(commandType, body);
        cmd.execute(); // ruft supplier.get() intern auf

        // Jetzt nachträglich das Ergebnis abfragen
        Object result = cmd.getResult();
        return ResponseEntity.ok(result);
    }
}