package be.codesolutions.osgi.provider;

import be.codesolutions.osgi.api.FormalGreetingService;
import be.codesolutions.osgi.api.GreetingService;
import org.osgi.service.component.annotations.Component;

@Component(
        service = {GreetingService.class, FormalGreetingService.class},
        property = {
                "service.ranking:Integer=100",
                "language=en",
                "formal=true"
        })
public class PremiumGreetingService implements FormalGreetingService {
    @Override
    public String greet(String name) {
        return "Greetings, " + name + ". Welcome to our premium OSGi service.";
    }

    @Override
    public String getFormality() {
        return "formal";
    }
}