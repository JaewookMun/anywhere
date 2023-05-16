package prj.margin.anywhere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prj.margin.anywhere.service.ApiService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/search")
    public String search(@RequestParam(name = "keyword") String keyword) throws IOException {
        apiService.revokeNaverMapSearchApi(keyword);

        return keyword;
    }
}
