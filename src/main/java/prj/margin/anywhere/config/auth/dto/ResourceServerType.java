package prj.margin.anywhere.config.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceServerType {
    GOOGLE("google"), NAVER("naver"), KAKAO("kakao");

    private final String registrationId;

}
