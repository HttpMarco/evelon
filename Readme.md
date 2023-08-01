
## Evelon (Not released)
Last update: 27.07.2023 Copyright by ByteMC-Network

**Find version:** 
<a href="https://artifactory.bytemc.de/ui/repos/tree/General/bytemc-public/net/bytemc/evelon">Here </a>

<a href="https://github.com/bytemc/Aeon"><img src="https://img.shields.io/github/issues/bytemcnetzwerk/evelon?color=10c298" alt="Issues Badge"/></a>
<a href="https://github.com/ByteMCNetzwerk/evelon"><img src="https://img.shields.io/github/stars/bytemcNetzwerk/Evelon?color=10c298" alt="Stars Badge"/></a>
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


Release-Notes:
- Implement count query function