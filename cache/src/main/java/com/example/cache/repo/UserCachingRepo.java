package com.example.cache.repo;

import com.example.cache.domain.User;
import com.example.cache.domain.Users;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Repository
@CacheConfig(cacheNames = "users")
public class UserCachingRepo {

    private final Map<Long, User> store=new LinkedHashMap<>();

    @Cacheable(key="'all'")
    public Users findAllUsers(){
        List<User> findAllUsers = store.values().stream().collect(Collectors.toList());
        log.info("Find All Users : {}",findAllUsers);
        return new Users(findAllUsers);
    }

    @Cacheable(key = "#userSeq", unless = "#result == null")
    public User findById(Long userSeq) {
        User user = store.get(userSeq);
        log.info("Repository find {}", user);
        return user;
    }

    @CachePut(key = "#user.userSeq")
    @CacheEvict(key = "'all'")
    public User save(User user) {
        Long newId = calculateId();
        user.setUserSeq(newId);

        log.info("Repository save {}", user);

        store.put(user.getUserSeq(), user);
        return user;
    }

    private Long calculateId() {
        if (store.isEmpty()) {
            return 1L;
        }

        int lastIndex = store.size() - 1;
        return (Long) store.keySet().toArray()[lastIndex] + 1;
    }

    @CachePut(key = "#user.userSeq")
    @CacheEvict(key = "'all'")
    public User update(User user) {
        log.info("Repository update {}", user);
        store.put(user.getUserSeq(), user);
        return user;
    }

    @Caching(evict = {
            @CacheEvict(key = "'all'"),
            @CacheEvict(key = "#user.userSeq")
    })
    public void delete(User user) {
        log.info("Repository delete {}", user);
        store.remove(user.getUserSeq());
    }
}
