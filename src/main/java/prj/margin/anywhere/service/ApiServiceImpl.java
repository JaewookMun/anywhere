package prj.margin.anywhere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import prj.margin.anywhere.config.auth.dto.ResourceServerType;
import prj.margin.anywhere.http.ApiDomain;
import prj.margin.anywhere.http.HttpConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final HttpConnection httpConnection;

    @Override
    public void revokeNaverMapSearchApi() throws IOException {
        // todo: revoke NAVER api for map search and deal with response
        Map<String, String> headers = getHeaders(ResourceServerType.NAVER.getRegistrationId());

        // todo: make queryString
        String queryString = "";
        httpConnection.sendRequest(ApiDomain.NAVER_SEARCH_MAP.getUrl(), RequestMethod.GET, headers, queryString);
    }

    /**
     *
     * @param registrationId - for OAuth2
     * @return
     */
    private Map<String, String> getHeaders(String registrationId) {
        Map<String, String> headers = new HashMap<>();

        // todo: implement how to read client-id, client-secret by registrationId

        return headers;
    }

}
