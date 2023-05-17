package prj.margin.anywhere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.repository.UserRepository;
import prj.margin.anywhere.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(User user) {
        return userRepository.save(user);
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public Optional<User> findOneByLoginId(String loginId) {
        return Optional.ofNullable(userRepository.findOneByLoginId(loginId));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Long modifyUser(Long id, UserDto userDto) {
        User findOne = userRepository.findOne(id);
        findOne.setName(userDto.getName());
        findOne.setPassword(userDto.getPassword());
        findOne.setRole(userDto.getRole());
        findOne.setEmail(userDto.getEmail());

        return findOne.getId();
    }

    public User removeUser(Long id) {
        User findOne = userRepository.findOne(id);
        findOne.setDeletedFlag(true);

        return findOne;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
