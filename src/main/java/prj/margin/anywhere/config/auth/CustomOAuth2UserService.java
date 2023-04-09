package prj.margin.anywhere.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import prj.margin.anywhere.config.auth.dto.OAuth2Attributes;
import prj.margin.anywhere.domain.User;
import prj.margin.anywhere.service.UserService;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;

    @Override
    // OAuth2UserRequest -> OAuth2UserService가 UserInfo Endpoint 로 사용자 정보를 얻기위해
    // 요청을 보낼 때 사용하는 정보
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // todo: attributes 를 가져오고 OAuth2User를 반환하는 기능 구현

        return null;
    }

    private User saveOrUpdate(OAuth2Attributes attributes) {
        User findOne = userService.findByEmail(attributes.getEmail())
                .map(user -> user.update(attributes.getName()))
                .orElse(attributes.toEntity());

        userService.save(findOne);
        return findOne;
    }

}
