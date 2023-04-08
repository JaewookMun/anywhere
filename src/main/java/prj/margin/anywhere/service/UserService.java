package prj.margin.anywhere.service;

import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findOne(Long id);
    Optional<User> findOneByLoginId(String loginId);

    Long modifyUser(Long id, UserDto userDto);

    User removeUser(Long id);

    List<User> findAllUsers();
}
