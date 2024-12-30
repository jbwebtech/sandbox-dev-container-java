# Dev Container Sandbox with Java 21

## Overview

This repository is designed to showcase the advantages and flexability of using Dev Containers through the lens of Java 21 language features. It provides a sandbox environment where developers can switch between a development environment for Java 21 and Java 8. This helps in catching potential issues with code written in Java 21 that may not be supported in Java 8.

## Prerequisites

- Docker
- Visual Studio Code with the Remote - Containers extension

## Getting Started

1. Clone the repository:
    ```sh
    git clone https://github.com/jbwebtech/sandbox-dev-container-java.git
    cd sandbox-dev-container-java
    ```

2. Open the repository in Visual Studio Code:
    ```sh
    code .
    ```

3. When prompted, reopen the repository in the Dev Container.
    * Note: this may take a few minutes to pull and build all docker images before starting. Open the log to view progress if you like.

## Dev Container Configuration

The Dev Container is configured using the `devcontainer.json` file located in the `.devcontainer` directory. This configuration sets up a development environment with Java 21 installed.

```jsonc
{
    "name": "Java Dev Container Sandbox",
    "image": "mcr.microsoft.com/devcontainers/java:1-21-bookworm",
    "features": {
        "ghcr.io/devcontainers/features/java:1": {
            "version": "21"
        }
    }
}
```

Once VS Code reopens in the container IDE, open a terminal and enter `java -version` to verify the current Java version installed in the environment.  

Next, Run or Debug the code to verify the terminal output:

```
Created Boat: Lucky
Boat[name=Lucky, horsepower=800, lengthInches=240.781]
Created Boat: Drifter
Boat[name=Drifter, horsepower=1200, lengthInches=319.0]
Equal reference b1 = b2? true
Created Boat: Lightning
Created Boat: Lightning
Equal value b1 & b2? true
Equal value b3 & b4? true
```

## Switching Java Versions

To switch the development environment from Java 21 to Java 8, modify the `devcontainer.json` file to use a different base image and Java version:

```jsonc
"image": "mcr.microsoft.com/devcontainers/java:8-bookworm"
"features": {
  "ghcr.io/devcontainers/features/java:1": {
    "version": "8"
  }
}
```

Rebuild the Dev Container after making this change.  If not automatically prompted, select **Dev Containers: Rebuild Container** from the Command Palette.

Reverify the Java version installed in the environment by entering `java --version` in the terminal.  Now, with Java 8 installed, the project fails to compile due to (at least) the `record` keyword in `Boat.java` and the `var` keyword in `Main.java`.

The compilation errors highlight the fact Java 8 is installed in the development environment, thus providing a reproducible development environment that can be shared across teams, CI/CD pipelines, and production systems through source control.

## Example Code

### Java 21 Features

The `Boat` class demonstrates the use of Java 21 records, which provide a compact syntax for declaring classes that are transparent holders for shallowly immutable data.  Importantly, records are not available in Java 8.

```java
import java.util.Objects;

public record Boat(String name, int horsepower, double lengthInches) {
    public Boat {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (horsepower <= 0 || lengthInches <= 0) {
            throw new IllegalArgumentException("Horsepower and Length must be greater than 0");
        }

        System.out.println("Created Boat: " + name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, horsepower, lengthInches);
    }

    @Override
    public final boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Boat that = (Boat) o;

        if (this == that) {
            return true;
        }

        return (Objects.equals(this.name, that.name)
                && this.horsepower == that.horsepower
                && Double.compare(this.lengthInches, that.lengthInches) == 0);
    }
}
```

The `Main` class demonstrates the use of the `var` keyword, which allows for local variable type inference; again, a feature not available in Java 8.

```java
public class Main {
    public static void main(String[] args) {
        var boat = new Boat("Lucky", 800, 240.781);
        System.out.println(boat);
        boat = new Boat("Drifter", 1200, 319);
        System.out.println(boat);

        final var b1 = boat;
        final var b2 = boat;

        System.out.println("Equal reference b1 = b2? " + (b1 == b2));

        Boat b3 = new Boat("Lightning", 2000, 400);
        Boat b4 = new Boat(b3.name(), b3.horsepower(), b3.lengthInches());

        System.out.println("Equal value b1 & b2? " + (b1.equals(b2)));
        System.out.println("Equal value b3 & b4? " + (b3.equals(b4)));
    }
}
```

## Conclusion

This sandbox repository provides a practical example of using Dev Containers to ensure a consistent and reproducable development environment, regardless of the local JDK installed on the developer's workstation.
