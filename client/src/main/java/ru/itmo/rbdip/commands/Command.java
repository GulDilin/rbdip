package ru.itmo.rbdip.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Scanner;

public abstract class Command {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    static Scanner scanner = new Scanner(System.in);
    String baseUrl;
    RestTemplate template = new RestTemplate();
    public static String authHeader = null;
    HttpHeaders headers = new HttpHeaders();

    public Command(String url) {
        baseUrl = url;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }


    public abstract void execute();

    public abstract String getName();
}
