# evelon

This application provides seamless object mapping between a local application and a database, along with a
straightforward synchronization process between them.

| icon                                                                                                                                                                                    | database   | abstractLayer id  | required version | implemented |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|-------------------|------------------|-------------|
| <div align="center"> -</div>                                                                                                                                                            | cache      | LocalStorageLayer | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/de/d/dd/MySQL_logo.svg"></div>                                                        | mysql      | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://dbdb.io/media/logos/h2-logo.svg"></div>                                                                              | h2         | H2Layer           | 2.2.224          | stable      |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1200px-Postgresql_elephant.svg.png"></div> | postgresql | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://cdn.worldvectorlogo.com/logos/mariadb.svg"></div>                                                                    | mariadb    | MariaDbLayer      | 3.3.3            | stable      |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Cassandra_logo.svg/2000px-Cassandra_logo.svg.png"></div>           | cassandra  | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://www.svgrepo.com/show/331488/mongodb.svg"></div>                                                                      | mongodb    | -                 | -.-.-            | in progress |
| <div align="center"> <img  heigth="17px" width="17px" src="https://couchdb.apache.org/image/couch@2x.png"></div>                                                                        | couchdb    | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                             | json       | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                             | yml        | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                             | aeon       | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://static-00.iconduck.com/assets.00/redis-plain-wordmark-icon-512x511-8n4kzl0q.png"></div>                              | redis      | -                 | -.-.-            | in progress |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/en/3/3a/ArangoDB_Logo.png"></div>                                                     | arangodb   | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://www.scylladb.com/wp-content/uploads/scylla-headset.png"></div>                                                       | scylladb   | -                 | -.-.-            | no          |

### Snapshots:

```kotlin
repositories {
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
}
```

```xml
<repository>
    <id>maven-central-snapshot</id>
    <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
</repository>
```

Current module types: `common`, `h2`, `mariadb`
```groovy
compile "dev.httpmarco.evelon:evelon-MODULE:VERSION"
```
```kotlin
implementation("dev.httpmarco.evelon:evelon-MODULE:VERSION")
```

```xml
<dependency>
    <groupId>dev.httpmarco.evelon</groupId>
    <artifactId>evelon-MODULE</artifactId>
    <version>VERSION</version>
</dependency>
```


### Change path of credentials configuartion
To change the path, there are two options. The first option would be to set the path in an environment variable, using the keyword `evelon.credentials.path`. Another option would be to set it programmatically with:
```java
ConnectionAuthenticationPath.set("home/my-credentials");
```

### Todo query methods
- [ ] `createIfNotExists` - Create an entity if it does not exist.
- [ ] `upsert` - Create an entity if it does not exist, otherwise update it.

### Another todos
- [ ] row transformer
- [ ] implement Arrays
- [ ] sql word blacklist
- [ ] maps
- [ ] implement duplicated id check (Warning)

Blacklisted words:
- user