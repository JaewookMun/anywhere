package prj.margin.anywhere.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import prj.margin.anywhere.config.auth.dto.ApplicationOAuth2NaverProperties;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    private ApiServiceImpl apiService;
    @Autowired
    private ApplicationOAuth2NaverProperties naverProperties;

    @Test
    public void test() throws IOException {
        apiService.revokeNaverMapSearchApi();

        // given
        // when
        // then
    }
}