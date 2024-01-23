package com.hjk.custom.service;

import com.hjk.custom.config.QueryDslModel;
import com.hjk.custom.entity.QUser;
import com.hjk.custom.entity.User;
import com.hjk.custom.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService extends QueryDslModel {

    private final UserRepository userRepository;

    private final JPAQueryFactory query;

    public User save(String name) {
        var data = User.builder().name(name).build();
        return userRepository.save(data);
    }

    public User getUser(String name) {
        return userRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUser(Long id) {
        var builder = new BooleanBuilder();
        builder.and(user.id.eq(id));
        return query.select(user)
                .from(user)
                .where(builder)
                .fetch();
    }
}
