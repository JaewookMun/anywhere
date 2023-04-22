package prj.margin.anywhere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    @Override
    public void revokeNaverMapSearchApi() {
        // todo: revoke NAVER api for map search

    }
}
