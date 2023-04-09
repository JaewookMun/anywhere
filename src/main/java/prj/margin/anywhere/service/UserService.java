package prj.margin.anywhere.service;

import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Long save(User user);
    User findOne(Long id);
    Optional<User> findOneByLoginId(String loginId);

    Optional<User> findByEmail(String email);

    Long modifyUser(Long id, UserDto userDto);

    User removeUser(Long id);

    List<User> findAllUsers();
}
