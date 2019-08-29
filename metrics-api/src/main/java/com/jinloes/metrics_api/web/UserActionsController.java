package com.jinloes.metrics_api.web;

import com.jinloes.metrics_api.data.UserActionsRepoistory;
import com.jinloes.metrics_api.model.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
public class UserActionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserActionsRepoistory userActionsRepoistory;

    @Autowired
    public UserActionsController(UserActionsRepoistory userActionsRepoistory) {
        this.userActionsRepoistory = userActionsRepoistory;
    }

    @PostMapping("/userActions")
    public ResponseEntity<?> log(@RequestBody UserAction action) {
        LOGGER.info("Logging user action: {}", action);
        userActionsRepoistory.save(action);
        return ResponseEntity.noContent().build();
    }
}
