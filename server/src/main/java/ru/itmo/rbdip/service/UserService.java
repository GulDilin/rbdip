package ru.itmo.rbdip.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.itmo.rbdip.exceptions.InvalidAuthorizationHeader;
import ru.itmo.rbdip.exceptions.InvalidUserCredentials;
import ru.itmo.rbdip.exceptions.UserAlreadyExists;
import ru.itmo.rbdip.exceptions.UserNotFoundException;
import ru.itmo.rbdip.repository.UserRepository;
import ru.itmo.rbdip.repository.entity.User;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class UserService {

    UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    private String hashPassword(String password) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        return DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
    }

    @SneakyThrows
    public User getUserDataFromHeader(String header) {
        header = header.trim().replace("Basic ", "");
        try {
            String decodedString = new String(Base64.getDecoder().decode(header));
            String[] credentials = decodedString.trim().split(":", 2);
            String password = hashPassword(credentials[1]);
            return new User(0L, credentials[0], password);
        } catch (Exception e) {
            throw new InvalidAuthorizationHeader();
        }
    }

    @SneakyThrows
    public User authorizeUser(User userData) {
        if (userData.getUsername().trim().isEmpty() ||
                userData.getPassword().trim().isEmpty()) {
            throw new InvalidUserCredentials();
        }
        User user = repository.findUserByUsername(userData.getUsername());
        if (user == null)
            throw new UserNotFoundException();
        if (!user.getPassword().equals(userData.getPassword())) {
            throw new InvalidUserCredentials();
        }
        return user;
    }

    @SneakyThrows
    private void registerUser(User user) {
        if (user.getUsername().trim().isEmpty() ||
                user.getPassword().trim().isEmpty()) {
            throw new InvalidUserCredentials();
        }

        if (repository.findUserByUsername(user.getUsername()) != null)
            throw new UserAlreadyExists();
        repository.save(user);
    }

    public User authorizeUserByHeader(String header) {
        return authorizeUser(getUserDataFromHeader(header));
    }

    public void registerUserByHeader(String header) {
        registerUser(getUserDataFromHeader(header));
    }

}
