package com.hjk.test;

import com.hjk.strings.StringUtils;
import io.doov.core.dsl.DOOV;
import org.springframework.data.jpa.repository.query.QueryUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;

public class TestOne {

    private static final ArrayList<Integer> list = new ArrayList<>();


    public static void main(String... args) {

    }



    public static void httpTest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create("https://apttax.co.kr/bp.jsp"))
                .header("Content-Type", "text/plain; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString("some body text")).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }


    public void listTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer[] arr = list.stream().filter((val) -> {
            return val > 3;
        }).toArray(Integer[]::new);
        StringUtils.print(arr);
    }


}
