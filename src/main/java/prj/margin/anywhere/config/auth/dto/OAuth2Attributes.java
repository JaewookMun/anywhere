package prj.margin.anywhere.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import prj.margin.anywhere.domain.Role;
import prj.margin.anywhere.domain.User;

import java.util.Map;

@Getter
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        OAuth2Attributes oAuth2Attributes = null;
        if(registrationId.equals(ResourceServerType.GOOGLE.getRegistrationId()))
            oAuth2Attributes = ofGoogle(userNameAttributeName, attributes);
        else if(registrationId.equals(ResourceServerType.NAVER.getRegistrationId()))
            oAuth2Attributes = ofNaver("id", attributes);
        else if (registrationId.equals(ResourceServerType.KAKAO.getRegistrationId()))
            oAuth2Attributes = ofKakao("profile", attributes);

        return oAuth2Attributes;
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){

        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response=(Map<String, Object>)attributes.get("response");

        return OAuth2Attributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response=(Map<String, Object>)attributes.get("kakao_account");

        return OAuth2Attributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.GUEST)
                .build();
    }
}
