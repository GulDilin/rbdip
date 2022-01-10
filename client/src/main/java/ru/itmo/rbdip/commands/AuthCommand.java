package ru.itmo.rbdip.commands;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Base64;

import static org.springframework.http.HttpStatus.OK;

public class AuthCommand extends Command {

    String prefix;
    boolean isReg;

    public AuthCommand(String url, boolean isReg) {
        super(url);
        this.prefix = isReg ? "auth/registration" : "auth/login";
        this.isReg = isReg;
    }

    @Override
    public void execute() {
        ResponseEntity<String> response = null;
        do {
            System.out.print("login: ");
            String login = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();
            String authHeader = "Basic " + new String(Base64.getEncoder().encode(String.format("%s:%s", login, password).getBytes()));
            headers.set("Authorization", authHeader);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            try {
                response = template.postForEntity(baseUrl + prefix, entity, String.class);
                if (response.getStatusCode() == OK)
                    Command.authHeader = authHeader;
            } catch (HttpClientErrorException e) {
                System.out.println(new String(e.getResponseBodyAsByteArray()));
            }
        } while (response != null && response.getStatusCode() != OK);
    }

    @Override
    public String getName() {
        return isReg ? "Registration" : "Log in";
    }
}
