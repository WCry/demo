package demo.service;

import org.springframework.stereotype.Service;

@Service
public class DataService {
    public String sayHello(String world) {
        return "hello" + world;
    }
}
