package com.spring.usermanagement.controller;

import com.spring.usermanagement.model.User;
import com.spring.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> list(@RequestParam("page") @Min(0) int page, @RequestParam("size") @Min(1) int size){
        return ResponseEntity.ok(userService.list(page, size));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody @Valid User user){
        if (userService.exist(id)) {
            user.setId(id);
            return ResponseEntity.ok(userService.create(user));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        if (userService.exist(id)) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
