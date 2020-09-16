package com.api.desarrollo.controller;

import com.api.desarrollo.entitys.User;
import com.api.desarrollo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<User>> getUsers(){
        List<User> user = userRepository.findAll();
        return ResponseEntity.ok(user);
    }

    @RequestMapping(
            value = "{userId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable Integer userId){
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<User> CreateUser(@RequestBody User user){
        User user1 = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = "application/json")
    public ResponseEntity<User> UpdateUser(@RequestBody User user){
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()){
            User userUpdate = userOptional.get();
            userUpdate.setNombre(user.getNombre());
            userUpdate.setApellido(user.getApellido());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setImagen(user.getImagen());
            userRepository.save(userUpdate);
            return ResponseEntity.ok(userUpdate);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            value = "{userId}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity<Void> DeleteUser(@PathVariable Integer userId){
        userRepository.deleteById(userId);
        return ResponseEntity.ok(null);
    }

}
