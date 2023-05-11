package prj.margin.anywhere.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class NaverResponseDto {

    private LocalDateTime lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<Location> items;

    @Override
    public String toString() {
        return "NaverResponseDto{" +
                "lastBuildDate=" + lastBuildDate +
                ", total=" + total +
                ", start=" + start +
                ", display=" + display +
                ", items=" + items +
                '}';
    }
}
