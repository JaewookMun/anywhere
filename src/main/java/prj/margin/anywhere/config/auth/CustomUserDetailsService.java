package prj.margin.anywhere.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.loadUserByUsername");

        Optional<User> findOne = userService.findOneByLoginId(loginId);
        User user = findOne.orElseThrow(() -> new UsernameNotFoundException("There is no that User"));

        System.out.println(LocalDate.now() + ": " + user);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLoginId())
                .password(user.getPassword())
                .roles(user.getUserRole().toString())
                .build();

    }
}
