package prj.margin.anywhere.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Location {
    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    // mapx, mapy -> 네이버 지도 API
    private Integer mapx;
    private Integer mapy;

    @Override
    public String toString() {
        return "Location{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", mapx=" + mapx +
                ", mapy=" + mapy +
                '}';
    }
}
