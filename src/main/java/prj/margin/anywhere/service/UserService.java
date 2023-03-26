package prj.margin.anywhere.service;

import prj.margin.anywhere.domain.User;

import java.util.Optional;

public interface UserService {
    User findOne(Long id);
    Optional<User> findOneByLoginId(String loginId);
}
