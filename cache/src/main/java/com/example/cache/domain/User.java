package com.example.cache.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;


}
