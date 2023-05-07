package prj.margin.anywhere.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NaverApiParamDto {
    /**
     * 필수
     */
    private String query;

    /**
     * 한 번에 표시할 검색 결과 개수(기본값: 1, 최댓값: 5)
     */
    private Integer display;

    /**
     * 검색 시작 위치(기본값: 1, 최댓값: 1)
     */
    private Integer start;

    /**
     * 검색 결과 정렬 방법
     * - random: 정확도순으로 내림차순 정렬(기본값)
     * - comment: 업체 및 기관에 대한 카페, 블로그의 리뷰 개수순으로 내림차순 정렬
     */
    private String sort;

    @Builder
    public NaverApiParamDto(String query, Integer display, Integer start, String sort) {
        this.query = query;
        this.display = display;
        this.start = start;
        this.sort = sort;
    }

    public String createQueryString() {
        StringBuilder sb = new StringBuilder();
        sb.append("?").append("query").append("=").append(this.query)
                .append("&").append("display").append("=").append(this.display)
                .append("&").append("start").append("=").append(this.start)
                .append("&").append("sort").append("=").append(this.sort);

        return sb.toString();
    }
}
