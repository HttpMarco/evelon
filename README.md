# Evelon

**Latest stable-version:** 1.5.0<br>
**Latest snapshot-version:** 1.5.1-SNAPSHOT<br>
**All available versions:** 
<a href="https://artifactory.bytemc.de/ui/native/bytemc-public/net/bytemc/evelon/">Click </a>

> **Warning**
>  
> You need following repository `public-bytemc`

## Repository

Maven
```xml
<repository>
    <id>public-bytemc</id>
    <url>https://artifactory.bytemc.de/artifactory/public-bytemc</url>
</repository>
```

Gradle (Kotlin DSL)
```kotlin
maven {
    url = uri("https://artifactory.bytemc.de/artifactory/bytemc-public/")
}
```

## Dependency
Maven
```xml
<dependency>
    <groupId>net.bytemc</groupId>
    <artifactId>evelon</artifactId>
    <version>VERSION</version>
</dependency>
```
Gradle (Kotlin DSL)
```kotlin
implementation("net.bytemc:evelon:VERSION")
```


## Usage
To learn how to use Evelon to easily interact with your database check our <a href="https://github.com/ByteMCNetzwerk/evelon/wiki">wiki</a>.
