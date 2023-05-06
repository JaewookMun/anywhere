package prj.margin.anywhere.http;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import prj.margin.anywhere.config.auth.dto.ApplicationOAuth2NaverProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class HttpConnectionTest {
    @Autowired
    private HttpConnection httpConnection;
    @Autowired
    private ApplicationOAuth2NaverProperties oAuth2NaverProperties;

    @Test
    public void connectionTest() throws IOException {
        // String apiUrl, RequestMethod requestMethod, Map<String, String> headers, String queryStringForParams
        //> GET /v1/search/local.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=random HTTP/1.1
        //> Host: openapi.naver.com
        //> User-Agent: curl/7.49.1
        //> Accept: */*
        //> X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 클라이언트 아이디 값}
        //> X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 클라이언트 시크릿 값}

        // when

        // given
        String url = ApiDomain.NAVER_SEARCH_MAP.getUrl();
        RequestMethod requestMethod = RequestMethod.GET;
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Naver-Client-Id", oAuth2NaverProperties.getClientId());
        headers.put("X-Naver-Client-Secret", oAuth2NaverProperties.getClientSecret());
        headers.put("Accept", "*/*");
        String queryString = "query=서울";

        // then
        httpConnection.sendRequest(url, requestMethod, headers, queryString);


    }
}