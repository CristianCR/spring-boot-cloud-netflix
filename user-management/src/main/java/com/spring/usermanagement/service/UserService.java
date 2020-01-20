package com.spring.usermanagement.service;

import com.spring.usermanagement.model.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> list(int page, int size);

    User create(User user);

    void delete(int id);

    boolean exist(int id);

}
