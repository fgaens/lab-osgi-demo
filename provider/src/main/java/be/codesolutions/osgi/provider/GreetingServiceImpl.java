package be.codesolutions.osgi.provider;

import be.codesolutions.osgi.api.GreetingService;
import org.osgi.service.component.annotations.Component;

@Component(
        service = GreetingService.class,
        immediate = true)
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hello, " + name + "! Welcome to OSGi!";
    }
}