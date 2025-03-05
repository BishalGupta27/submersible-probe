package com.ocean.submersibleProbe.controller;

import com.ocean.submersibleProbe.model.*;
import com.ocean.submersibleProbe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbeController {
    @Autowired
    private ProbeMovement probeMovement;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeProbe(@RequestBody InitializationRequest request) {
    	try {
    	probeMovement.initialize(request.getProbe(), request.getGrid());
        return ResponseEntity.ok("Probe initialized.");
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error:--" + e.getMessage());
    	}
    }

    @PostMapping("/commands")
    public ResponseEntity<Probe> processCommands(@RequestBody CommandRequest request) {
        Probe processedProbe = probeMovement.processCommands(request.getCommands());
        return ResponseEntity.ok(processedProbe);
    }

    @GetMapping("/visited")
    public ResponseEntity<VisitedCoordinates> getVisitedCoordinates() {
        VisitedCoordinates visitedCoordinates = probeMovement.getVisitedCoordinates();
        return ResponseEntity.ok(visitedCoordinates);
    }

    @GetMapping("/current")
    public ResponseEntity<Probe> getCurrentProbeState() {
        return ResponseEntity.ok(probeMovement.processCommands(java.util.Collections.emptyList()));
    }
}
