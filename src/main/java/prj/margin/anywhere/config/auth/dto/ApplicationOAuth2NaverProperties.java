package prj.margin.anywhere.config.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.naver")
@Getter @Setter
public class ApplicationOAuth2NaverProperties {
    private String clientId;
    private String clientSecret;


    @Override
    public String toString() {
        return "ApplicationOAuth2NaverProperties{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                '}';
    }
}
