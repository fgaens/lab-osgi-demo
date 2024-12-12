package be.codesolutions.osgi.provider;

import be.codesolutions.osgi.api.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<GreetingService> registration;

    @Override
    public void start(BundleContext context) {
        GreetingService service = new GreetingServiceImpl();
        registration = context.registerService(
                GreetingService.class,
                service,
                null
        );
        System.out.println("Greeting Service Provider started");
    }

    @Override
    public void stop(BundleContext context) {
        if (registration != null) {
            registration.unregister();
            registration = null;
        }
        System.out.println("Greeting Service Provider stopped");
    }
}