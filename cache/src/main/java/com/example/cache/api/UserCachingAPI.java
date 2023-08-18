package com.example.cache.api;

import com.example.cache.domain.User;
import com.example.cache.repo.UserCachingRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserCachingAPI {

    private final UserCachingRepo userCachingRepo;

    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUserinfo(){
        return new ResponseEntity<>(userCachingRepo.findAllUsers(),
                                    HttpStatus.OK);
    }

    @GetMapping("/user/{seq}")
    public ResponseEntity<?> getInfoBySeq(@PathVariable("seq")Long seq){
        return new ResponseEntity<>(userCachingRepo.findById(seq)
                                ,HttpStatus.OK);
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> saveNewUser(@RequestBody User user){
        return new ResponseEntity<>(userCachingRepo.save(user)
                                ,HttpStatus.OK);
    }

    @PutMapping("/user/modify")
    public ResponseEntity<?> modifyUserInfo(@RequestBody User user){
        return new ResponseEntity<>(userCachingRepo.update(user)
                                ,HttpStatus.OK);
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestBody User user){
        userCachingRepo.delete(user);
    }
}
