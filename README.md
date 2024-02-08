# Guide to Publishing to Maven Central via the Sonatype Central Portal with GitHub Actions

This guide provides a comprehensive walkthrough for publishing a Java library to Maven Central, utilizing the Sonatype Central Portal for submission and GitHub Actions to automate the process. Please note: Starting from February 1st, 2024, the process for registering and publishing artifacts to Maven Central requires the use of the Sonatype Central Portal. Developers who have previously relied on older OSSRH (OSS Repository Hosting) methods for publishing are encouraged to transition to this updated process. For details on transitioning and support, refer to the [Sonatype documentation](https://central.sonatype.org/register/legacy/).

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=teamlead_java-maven-sonatype-starter&metric=coverage)](https://sonarcloud.io/summary/new_code?id=teamlead_java-maven-sonatype-starter) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=teamlead_java-maven-sonatype-starter&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=teamlead_java-maven-sonatype-starter) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=teamlead_java-maven-sonatype-starter&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=teamlead_java-maven-sonatype-starter)

## Step 1: Create Sonatype Account

Create an account through [Sonatype Central](https://central.sonatype.com/) using an email and password. 

If you sign in using your **existing** GitHub account, the namespace for your account will be automatically validated.

## Step 2: Namespace Configuration and Domain Validation

Add and validate [your namespace](https://central.sonatype.com/publishing/namespaces) corresponding to your domain, e.g., `pro.teamlead` for `teamlead.pro`. Additionally, validate the namespace `io.github.YOUR_GITHUB_NAME` by creating a test repository on GitHub. For GitHub users, namespace validation [is automatic](https://central.sonatype.org/register/central-portal/#for-code-hosting-services-with-personal-groupid) upon account creation.

## Step 3: GPG Key Generation and Distribution

Following the [Sonatype GPG requirements](https://central.sonatype.org/publish/requirements/gpg/):

- 3.1 Generate a GPG key (`gpg --full-generate-key`) e.g RSA 4096 / No expire.
- 3.2 Extract the key YOUR_GPG_KEY_ID (`gpg --list-signatures --keyid-format 0xshort`)
- 3.3 Distribute it (`gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_GPG_KEY_ID`)
- 3.4 Export the private key (`gpg --armor --export-secret-key <key-id> > privkey.asc`)

## Step 4: Maven Project Configuration

Configure your `pom.xml` with necessary plugins for publishing to Sonatype. See the project's [pom.xml](https://github.com/teamlead/java-maven-sonatype-starter/blob/master/pom.xml) for an example configuration.

Required plugins:

- central-publishing-maven-plugin
- maven-source-plugin
- maven-javadoc-plugin
- maven-gpg-plugin

## Step 5: GitHub Secrets for Automation

Visit "Actions secrets and variables" page in Github UI (`your_repo/settings/secrets/actions`).

Add secrets to your GitHub repository for automated publishing:

- `NEXUS_USERNAME` and `NEXUS_PASSWORD`: Generated [from your Sonatype account](https://central.sonatype.com/account) User Token.
- `GPG_PRIVATE_KEY`: The content of `privkey.asc`.
- `GPG_PASSPHRASE`: Your GPG key passphrase.


## Step 6: Automating Publication with GitHub Actions

Create a GitHub Action workflow to automate the publishing process. See [.github/workflows/sonatype-publish.yml](https://github.com/teamlead/java-maven-sonatype-starter/blob/master/.github/workflows/sonatype-publish.yml) for an example.

## Step 7: Publishing Your Library

To publish your library, create a new release through the GitHub UI, which will trigger the automated process. Click "Create a new release" or visit (<your_repo>/releases/new).

To publish manually, add the following to your `~/.m2/settings.xml`:

```xml
<server>
    <id>central</id>
    
    <!--Sonatype account User Token Data -->
    <username>xxx</username> 
    <password>yyy</password>
    
</server>
...
<profiles>
  <profile>
    <id>gpg-key1</id>
    <properties>
        <gpg.keyname>$YOUR_GPG_KEY_ID</gpg.keyname>
        <gpg.passphrase>$YOUR_GPG_SECRET</gpg.passphrase>
    </properties>
  </profile>
</profiles>
```

Then execute: 

`mvn clean deploy -Pgpg-key1 -PsonatypeDeploy`.

Publishing can take 5-10 minutes. Your library will now be available on Maven Central for use in projects worldwide.

## Step 8: Using the Published Library

Once published, the library can be included as a dependency in Maven projects:

```xml
<dependency>
  <groupId>pro.teamlead</groupId>
  <artifactId>java-maven-sonatype-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

Ensure you replace placeholders like `<your_repo>`, `$YOUR_GPG_...` with your GitHub repository URL and specific GPG and Sonatype credentials in the provided XML snippets.


## Contributing

Please feel free to fork this repository, make changes, and submit pull requests. Your contributions are welcome!

## License

This project is licensed under the MIT License - see the LICENSE file for details.
