package prj.margin.anywhere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.repository.UserRepository;
import prj.margin.anywhere.service.dto.UserDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Optional<User> findOneByLoginId(String loginId) {
        return Optional.ofNullable(userRepository.findOneByLoginId(loginId));
    }

    @Override
    public Long modifyUser(Long id, UserDto userDto) {
        User findOne = userRepository.findOne(id);
        findOne.setName(userDto.getName());
        findOne.setPassword(userDto.getPassword());
        findOne.setRole(userDto.getRole());
        findOne.setEmail(userDto.getEmail());

        return findOne.getId();
    }

    @Override
    public User removeUser(Long id) {
        User findOne = userRepository.findOne(id);
        findOne.setDeletedFlag(true);

        return findOne;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
