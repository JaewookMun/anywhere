package prj.margin.anywhere.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ApiDomain {
    NAVER_SEARCH_MAP("https://openapi.naver.com/v1/search/local.json"),
    GOOGLE("temp");

    private final String url;
}
