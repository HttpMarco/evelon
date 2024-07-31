# evelon

This application provides seamless object mapping between a local application and a database, along with a
straightforward synchronization process between them.

Planned databases:
scylladb, arangodb, ragonfly, redis, aeon, yml, json, couchdb, r2dbc, mongodb, cassandra, postgresql, local-cache

Dependency: https://github.com/HttpMarco/evelon/wiki


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
