package com.hjk.custom.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
public class RestUtils {

    public static URI buildUri(String baseUrl) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        return builder.build().toUri();
    }

    public static URI buildUri(String baseUrl, String path, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        if (StringUtils.hasText(path)) builder.path(path);
        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.build().toUri();
    }

    public static <T> void getForEntity(URI uri) {
        try {
            RestTemplate rest = new RestTemplate();
            RequestEntity<Void> responseEntity = RequestEntity.get(uri).build();
            var res = rest.exchange(responseEntity, String.class);
            System.out.println(res);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
