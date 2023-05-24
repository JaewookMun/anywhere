package prj.margin.anywhere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import prj.margin.anywhere.config.auth.dto.ApplicationOAuth2NaverProperties;
import prj.margin.anywhere.config.auth.dto.ResourceServerType;
import prj.margin.anywhere.http.ApiDomain;
import prj.margin.anywhere.http.HttpConnection;
import prj.margin.anywhere.service.dto.NaverApiParamDto;
import prj.margin.anywhere.service.dto.NaverResponseDto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final HttpConnection httpConnection;
    private final ApplicationOAuth2NaverProperties oAuth2Properties;

    public void revokeNaverMapSearchApi(String keyword) throws IOException {
        // todo: revoke NAVER api for map search and deal with response

        Map<String, String> headers = getHeaders(ResourceServerType.NAVER.getRegistrationId());

        String queryString = NaverApiParamDto.builder()
                .query(encodeKeyword(keyword))
//                .query("%EC%A3%BC%EC%8B%9D")
                .display(5) // 5개 까지가 최대
                .start(1)
                .sort("random")
                .build()
                .createQueryString();

        httpConnection.sendRequest(ApiDomain.NAVER_SEARCH_MAP.getUrl(), RequestMethod.GET, headers, queryString, NaverResponseDto.class);
    }

    private String encodeKeyword(String keyword) throws UnsupportedEncodingException {
        String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
        return encodedKeyword;
    }

    /**
     *
     * @param registrationId - for OAuth2
     * @return
     */
    private Map<String, String> getHeaders(String registrationId) {
        Map<String, String> headers = new HashMap<>();
        if(registrationId.equals(ResourceServerType.NAVER.getRegistrationId())) {
            headers.put("X-Naver-Client-Id", oAuth2Properties.getClientId());
            headers.put("X-Naver-Client-Secret", oAuth2Properties.getClientSecret());
            headers.put("Accept", "*/*");
        }

        return headers;
    }

}
