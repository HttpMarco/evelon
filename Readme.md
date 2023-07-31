
## Evelon (Not released)
Last update: 27.07.2023 Copyright by ByteMC-Network

### Setup

Add following two things to your pom.xml:
```xml
<repositories>
    <repository>
        <id>bytemc</id>
        <url>https://artifactory.bytemc.de/artifactory/bytemc-public/</url>
    </repository>
</repositories>
```
Maven-Dependency:
```xml
<dependency>
    <groupId>net.bytemc</groupId>
    <artifactId>evelon</artifactId>
    <version>[VERSION]</version>
</dependency>
```
Gradle-Dependency:
```groovy
compile(group: 'net.bytemc', name: 'evelon', version: '[VERSION]')
```

Ivy-Dependency:
````xml
<dependency org="net.bytemc" name="evelon" rev="[VERSION]">
    <artifact name="evelon" ext="jar"/>
</dependency>
````

Sbt-Dependency:
```groovy
libraryDependencies += "net.bytemc" % "evelon" % "[VERSION]"
````
