package prj.margin.anywhere.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import prj.margin.anywhere.config.auth.dto.ApplicationOAuth2NaverProperties;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class ApiServiceTest {

    @Autowired
    private ApiService apiService;
    @Autowired
    private ApplicationOAuth2NaverProperties naverProperties;

    @Test
    @DisplayName("테스트")
    public void test() throws IOException {
        String keyword = "이가식당";
        apiService.revokeNaverMapSearchApi(keyword);

        // given
        // when
        // then
    }
}