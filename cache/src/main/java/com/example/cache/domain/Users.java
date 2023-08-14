package com.example.cache.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class Users {
    private List<User> users=new ArrayList<>();

    public Users(List<User> users) {
        this.users = users;
    }
}
