# OSGi Service Example

This project demonstrates a basic OSGi service implementation using Declarative Services. It consists of a service API, a service provider, and a service consumer.

## What is OSGi?

OSGi (Open Services Gateway initiative) is a Java framework for developing and deploying modular software programs and libraries. It introduces a component system for Java that allows applications to be dynamically composed of many different reusable components.

### Key Concepts

- **Bundles**: The OSGi components that are deployed
- **Services**: The objects that are shared between bundles
- **Life Cycle**: API to install, start, stop, update, and uninstall bundles
- **Modules**: The system that defines how a bundle can import and export code
- **Security**: The system that handles the security aspects

## OSGi vs Microservices

| Aspect | OSGi | Microservices |
|--------|------|---------------|
| **Granularity** | Fine-grained modules within a single JVM | Independent services, each potentially running in its own JVM |
| **Communication** | In-memory method calls | Network-based (HTTP, message queues) |
| **Deployment** | Hot deployment within container | Independent deployment of services |
| **State** | Shared JVM memory | Each service has its own state |
| **Scalability** | Vertical (within JVM limits) | Horizontal (can deploy multiple instances) |
| **Isolation** | Class loader isolation | Process/container isolation |
| **Development** | Modular monolith | Distributed system |
| **Performance** | Very fast (in-memory) | Network overhead |
| **Complexity** | Learning curve for OSGi concepts | Learning curve for distributed systems |

## Project Structure

- `api`: Defines the service interface
- `provider`: Implements the service interface
- `consumer`: Consumes the service

## Running the Application

### Prerequisites

1. Download Apache Karaf from [https://karaf.apache.org/download.html](https://karaf.apache.org/download.html)
2. Extract the archive to your desired location

### Starting Karaf

1. Navigate to the Karaf directory
2. Run the start script:
   ```bash
   # For Unix/Linux/Mac
   bin/karaf
   ```

### Stopping Karaf
1. Inside Karaf console:
    ```bash
    # Full command
    shutdown
   
    # Alternative command
    system:shutdown
    ```

### Installing the Bundles

1. First, install the Declarative Services feature:
   ```bash
   feature:install scr
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Install the bundles in Karaf (adjust paths according to your setup):
   ```bash
   bundle:install -s file:/path/to/api/target/api-1.0-SNAPSHOT.jar
   bundle:install -s file:/path/to/provider/target/provider-1.0-SNAPSHOT.jar
   bundle:install -s file:/path/to/consumer/target/consumer-1.0-SNAPSHOT.jar
   ```

### Useful Karaf Commands

- List installed bundles:
  ```bash
  bundle:list
  ```

- Check service components:
  ```bash
  scr:list
  ```

- View logs:
  ```bash
  log:tail
  ```

- Stop a bundle:
  ```bash
  bundle:stop <bundle-id>
  ```

- Start a bundle:
  ```bash
  bundle:start <bundle-id>
  ```

## Troubleshooting

If bundles fail to start, check:
1. Bundle status using `bundle:list`
2. Service component status using `scr:list`
3. Logs using `log:tail`
4. Dependencies using `bundle:diag <bundle-id>`

## References

- [OSGi Alliance](https://www.osgi.org/)
- [Apache Karaf](https://karaf.apache.org/)
- [OSGi Specification](https://docs.osgi.org/specification/)