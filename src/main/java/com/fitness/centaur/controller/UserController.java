package com.fitness.centaur.controller;

import com.fitness.centaur.dto.UserDTO;
import com.fitness.centaur.entity.User;
import com.fitness.centaur.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> editUser(@PathVariable("idUser") Long idUser,
                                      @RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.editUser(idUser, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable("idUser") Long idUser) {
        userService.deleteUser(idUser);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.ACCEPTED);
    }
}
