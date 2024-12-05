package be.codesolutions.osgi.consumer;

import be.codesolutions.osgi.api.GreetingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    immediate = true
)
public class GreetingConsumer {
    private GreetingService greetingService;

    @Reference
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
        // Service is injected, we can use it
        System.out.println(greetingService.greet("OSGi User"));
    }
}