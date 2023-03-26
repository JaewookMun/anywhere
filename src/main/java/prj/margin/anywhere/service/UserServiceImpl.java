package prj.margin.anywhere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public Optional<User> findOneByLoginId(String loginId) {
        return Optional.ofNullable(userRepository.findOneByLoginId(loginId));
    }
}
