package be.codesolutions.osgi.consumer;

import be.codesolutions.osgi.api.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
    private ServiceReference<GreetingService> serviceReference;

    @Override
    public void start(BundleContext context) {
        // Get service reference and use the service
        serviceReference = context.getServiceReference(GreetingService.class);
        if (serviceReference != null) {
            GreetingService service = context.getService(serviceReference);
            System.out.println(service.greet("OSGi Consumer"));
        }
        System.out.println("Greeting Service Consumer started");
    }

    @Override
    public void stop(BundleContext context) {
        // Release the service reference
        if (serviceReference != null) {
            context.ungetService(serviceReference);
            serviceReference = null;
        }
        System.out.println("Greeting Service Consumer stopped");
    }
}