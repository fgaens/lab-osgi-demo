package be.codesolutions.osgi.consumer;

import be.codesolutions.osgi.api.GreetingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

@Component(
        immediate = true
)
public class GreetingConsumer {
    private volatile GreetingService greetingService;

    @Reference(
            target = "(language=en)",
            policy = ReferencePolicy.DYNAMIC
    )
    public void setGreetingService(GreetingService service) {
        this.greetingService = service;
        System.out.println("New greeting service bound!");
        System.out.println(service.greet("OSGi User"));
    }

    public void unsetGreetingService(GreetingService service) {
        if (this.greetingService == service) {
            this.greetingService = null;
            System.out.println("Greeting service unbound!");
        }
    }
}