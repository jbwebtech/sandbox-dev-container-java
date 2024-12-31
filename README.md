# Dev Container Sandbox with Java 21

## Overview

This repository is designed to showcase the advantages and flexibility of using Dev Containers through the lens of Java 21 language features. It provides a sandbox environment where developers can switch between a development environment for Java 21 and Java 8. This helps in catching potential issues with code written in Java 21 that may not be supported in Java 8.

## Prerequisites

- Docker
- Visual Studio Code with the Remote - Containers extension

## Getting Started

1. Clone and open the repository in Visual Studio Code:
    ```sh
    git clone https://github.com/jbwebtech/sandbox-dev-container-java.git
    cd sandbox-dev-container-java
    code .
    ```

2. When prompted, reopen the repository in the Dev Container.
    * If not automatically prompted, select **Dev Containers: Open Folder in Container** from the Command Palette.
    * Note: it may take a few minutes to pull and build the docker images before starting. Open the log to view progress if you like.

## Dev Container Configuration

The Dev Container is configured using the `devcontainer.json` file located in the `.devcontainer` directory, which should be committed to source control. The sample configuration below sets up a development environment with Java 21 installed.

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
Created Boat: Lightning
Boat[name=Lightning, horsepower=2000, lengthInches=400.0]
Created Boat: Lightning
Boat[name=Lightning, horsepower=2000, lengthInches=400.0]
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

  Note: checkout the `java-8` branch for a working example where the `Main` and `Boat` classes have been refactored to Java 8 compatibility.

## Example Code

### Java 21 Features

The `Boat` class demonstrates the use of Java 21 records, which provide a compact syntax for declaring classes that are transparent holders for shallowly immutable data.  Importantly, records are not available in Java 8.

```java
public record Boat(String name, int horsepower, double lengthInches) {
    public Boat {
        System.out.println("Created Boat: " + name);
    }
}
```

The `Main` class demonstrates the use of the `var` keyword, which allows for local variable type inference; again, a feature not available in Java 8.

```java
public class Main {
    public static void main(String[] args) {
        final var b1 = new Boat("Lucky", 800, 240.781);
        System.out.println(b1);

        final var b2 = new Boat("Drifter", 1200, 319);
        System.out.println(b2);

        final var b3 = new Boat("Lightning", 2000, 400);
        System.out.println(b3);

        final var b4 = new Boat(b3.name(), b3.horsepower(), b3.lengthInches());
        System.out.println(b4);
      }
}

```

## Conclusion

This sandbox repository provides a practical example of using Dev Containers to ensure a consistent and reproducible development environment, regardless of the local JDK installed on the developer's workstation.


## Resources

* [Developing inside a Container](https://code.visualstudio.com/docs/devcontainers/containers)
* [Development Containers](https://containers.dev/)
* [Dev Containers CLI](https://containers.dev/supporting#devcontainer-cli)
