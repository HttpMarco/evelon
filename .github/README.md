# evelon

This application provides seamless object mapping between a local application and a database, along with a
straightforward synchronization process between them.

| icon                                                                                                                                                                                    | database   | abstractLayer id  | required version | implemented |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|-------------------|------------------|-------------|
| <div align="center"> -</div>                                                                                                                                                            | cache      | LocalStorageLayer | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/de/d/dd/MySQL_logo.svg"></div>                                                        | mysql      | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://dbdb.io/media/logos/h2-logo.svg"></div>                                                                              | h2         | H2Layer           | 2.2.224          | in progress |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1200px-Postgresql_elephant.svg.png"></div> | postgresql | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://cdn.worldvectorlogo.com/logos/mariadb.svg"></div>                                                                    | mariadb    | MariaDbLayer      | 3.3.3            | in progress |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Cassandra_logo.svg/2000px-Cassandra_logo.svg.png"></div>           | cassandra  | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://www.svgrepo.com/show/331488/mongodb.svg"></div>                                                                      | mongodb    | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://couchdb.apache.org/image/couch@2x.png"></div>                                                                        | couchdb    | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                             | json       | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                             | yml        | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                             | aeon       | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://static-00.iconduck.com/assets.00/redis-plain-wordmark-icon-512x511-8n4kzl0q.png"></div>                              | redis      | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/en/3/3a/ArangoDB_Logo.png"></div>                                                     | arangodb   | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://www.scylladb.com/wp-content/uploads/scylla-headset.png"></div>                                                       | scylladb   | -                 | -.-.-            | no          |

### Snapshots:

```kotlin
repositories {
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
}
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





### Todo query methods
- [ ] `order` - Order the result by the given field.
- [ ] `min` - Find the minimum value of the given field.
- [ ] `max` - Find the maximum value of the given field.
- [ ] `sum` - Find the sum of the given field.
- [ ] `avg` - Find the average value of the given field.
- [ ] `createIfNotExists` - Create an entity if it does not exist.
- [ ] `upsert` - Create an entity if it does not exist, otherwise update it.

### Another todos
- [ ] row transformer
- [ ] implement Arrays