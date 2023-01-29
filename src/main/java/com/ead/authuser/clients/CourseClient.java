package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.ResponsePageDto;
import com.ead.authuser.services.UtilsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Component
public class CourseClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilsService utilsService;

    @Value("${ead.api.url.course}")
    private String requestUrlCourse;

    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        ResponsePageDto<CourseDto> body = null;
        String url = requestUrlCourse + utilsService.createUrlGetAllCoursesByUser(userId, pageable);

        log.info("Request URL: {}", url);

        try {
            var responseType = new ParameterizedTypeReference<ResponsePageDto<CourseDto>>() {
            };
            ResponseEntity<ResponsePageDto<CourseDto>> result = restTemplate
                    .exchange(url, HttpMethod.GET, null, responseType);
            body = result.getBody();
            List<CourseDto> searchResult = Optional.ofNullable(body).map(ResponsePageDto::getContent)
                    .orElse(new ArrayList<>());
            log.debug("Response Number of Elements: {}", searchResult.size());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /courses", e);
        }

        log.info("Ending request /courses userId {}", userId);

        return body;
    }

}
