package br.com.msuser.controller;

import br.com.msuser.domain.UserEntity;
import br.com.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/member")
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity input) {
        UserEntity result = userService.registerUser(input);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
