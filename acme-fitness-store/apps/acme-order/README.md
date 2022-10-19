# ACME Order Service

## Getting Started

To run the acme order service, do the following:

Start the mongodb container:

```bash
    docker compose up -d
```

Start the application:

```bash
    dotnet run
```

Verify the health of the application:

```bash
    open localhost:5000/actuator/health
```