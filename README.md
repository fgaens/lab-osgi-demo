# OSGi Dynamic Services Demo

This repository demonstrates OSGi's dynamic service capabilities through a practical example. It shows how to create, deploy, and update services at runtime without application restarts.

## Project Structure

```
.
├── api/                 # Service interfaces
├── provider/           # Service implementations
├── consumer/          # Service consumers
├── integration-tests/ # Automated tests
└── docs/             # Additional documentation
```

## Key Features

- Dynamic service registration and discovery
- Hot deployment of new service implementations
- Service ranking and filtering
- Integration with Maven and Apache Karaf
- Comprehensive test suite
- Documentation with practical examples

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Apache Karaf 4.4.0 or higher

## Quick Start

1. Clone the repository:
   ```bash
   git clone https://github.com/fgaens/osgi-dynamic-services.git
   cd osgi-dynamic-services
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start Karaf:
   ```bash
   ${KARAF_HOME}/bin/karaf
   ```

4. Install bundles:
   ```bash
   karaf> feature:install scr
   karaf> bundle:install -s file:api/target/api-1.0-SNAPSHOT.jar
   karaf> bundle:install -s file:provider/target/provider-1.0-SNAPSHOT.jar
   karaf> bundle:install -s file:consumer/target/consumer-1.0-SNAPSHOT.jar
   ```

## Development Guide

### Creating a New Service

1. Define the interface in `api`:
   ```java
   public interface MyService {
       String doSomething();
   }
   ```

2. Implement in `provider`:
   ```java
   @Component(service = MyService.class)
   public class MyServiceImpl implements MyService {
       public String doSomething() {
           return "Done!";
       }
   }
   ```

3. Consume in `consumer`:
   ```java
   @Component
   public class MyServiceConsumer {
       @Reference
       private MyService service;
       
       // Use service
   }
   ```

### Running Tests

```bash
# Unit tests
mvn test

# Integration tests
mvn verify -P integration-tests
```

## Advanced Topics

### Service Properties

```java
@Component(
    service = GreetingService.class,
    property = {
        "language=en",
        "region=US"
    }
)
```

### Service References

```java
@Reference(
    target = "(language=en)",
    policy = ReferencePolicy.DYNAMIC
)
```

### Bundle Management

```bash
# Update a bundle
karaf> bundle:update <id> file:path/to/new/bundle.jar

# List bundles
karaf> bundle:list

# Start/stop bundle
karaf> bundle:start <id>
karaf> bundle:stop <id>
```

## Troubleshooting

### Common Issues

1. **Bundle not starting**
    - Check bundle state: `bundle:list`
    - View logs: `log:tail`
    - Verify dependencies: `bundle:diag <id>`

2. **Service not found**
    - Check service registry: `service:list`
    - Verify component state: `scr:list`
    - Check service properties: `service:list <service interface>`

### Debug Mode

Enable remote debugging in Karaf:

```bash
# Edit etc/custom.properties
org.osgi.framework.bootdelegation=sun.*,com.sun.*
```

Start Karaf with debugging enabled:
```bash
bin/karaf debug
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## License

Apache License 2.0

## References

- [OSGi Alliance](https://www.osgi.org/)
- [Apache Karaf](https://karaf.apache.org/)
- [OSGi Specification](https://docs.osgi.org/specification/)