package com.jinloes.metrics_api.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinloes.metrics_api.data.UserActionsRepoistory;
import com.jinloes.metrics_api.model.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
public class UserActionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final ObjectMapper objectMapper;
    private final UserActionsRepoistory userActionsRepoistory;

    @Autowired
    public UserActionsController(ObjectMapper objectMapper, UserActionsRepoistory userActionsRepoistory) {
        this.objectMapper = objectMapper;
        this.userActionsRepoistory = userActionsRepoistory;
    }

    @PostMapping("/userActions")
    public void log(@RequestBody UserAction action) throws JsonProcessingException {
        userActionsRepoistory.save(action);
    }
}
