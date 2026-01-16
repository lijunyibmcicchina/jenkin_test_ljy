package com.ljy.fullstack.controller;

import com.ljy.fullstack.pojo.User;
import com.ljy.fullstack.pojo.http.ResponseMessage;
import com.ljy.fullstack.service.NDISServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final NDISServiceService ndisServiceService;

    @Autowired
    public UserController(NDISServiceService ndisServiceService) {
        this.ndisServiceService = ndisServiceService;
    }

    @GetMapping("/service-types/{serviceTypeId}")
    public ResponseEntity<ResponseMessage<List<User>>> getProvicerUsers(@PathVariable Long serviceTypeId) {
        List<User> users = ndisServiceService.getProviderUsersByServiceType(serviceTypeId);
        ResponseMessage<List<User>> message = ResponseMessage.success(users);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
