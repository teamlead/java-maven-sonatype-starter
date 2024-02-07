# Java Maven Sonatype Starter

This repository serves as a demonstration project for testing the publishing of Java Maven artifacts to the Sonatype OSSRH (Open Source Software Repository Hosting) using GitHub Actions. It includes a basic Java component, a JUnit test case, and is configured to generate code coverage reports with JaCoCo.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK 17 or newer
- Maven 3.6.0 or newer

### Installing

To get a development environment running, clone the repository to your local machine and install the dependencies using Maven.

```bash
git clone https://github.com/teamlead/java-maven-sonatype-starter.git
cd java-maven-sonatype-starter
mvn install
```

### Running the tests

To run the unit tests and generate a code coverage report:

```bash
mvn test
```

### Building the project

To compile the project and package it into a JAR file:

```bash
mvn package
```

## Publishing to Sonatype

The project is configured with GitHub Actions to automatically publish the packaged JAR to Sonatype OSSRH on every release. The workflow is defined in `.github/workflows/sonatype-publish.yml`. Ensure you have set up the required secrets (`NEXUS_USERNAME` and `NEXUS_PASSWORD`) in your GitHub repository settings for the action to successfully authenticate with Sonatype.

## Contributing

Please feel free to fork this repository, make changes, and submit pull requests. Your contributions are welcome!

## License

This project is licensed under the MIT License - see the LICENSE file for details.
