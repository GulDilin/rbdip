package ru.itmo.rbdip.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public abstract class Command {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    Scanner scanner;
    String baseUrl;
    RestTemplate template = new RestTemplate();

    public Command(Scanner scanner, String url){
        this.scanner = scanner;
        baseUrl = url;
    }


    public abstract void execute();

    public abstract String getName();
}
