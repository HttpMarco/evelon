# evelon

This application provides seamless object mapping between a local application and a database, along with a
straightforward synchronization process between them.

| icon                                                                                                                                                                             | database   | layer id          | required version | implemented |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|-------------------|------------------|-------------|
| <div align="center"> -</div>                                                                                                                                                     | cache      | LocalStorageLayer | -.-.-            | yes         |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/de/d/dd/MySQL_logo.svg"></div>                                                                                          | mysql      | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://dbdb.io/media/logos/h2-logo.svg"></div>                                                                       | h2         | -                 | -.-.-            | in progress |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1200px-Postgresql_elephant.svg.png"></div> | postgresql | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://cdn.worldvectorlogo.com/logos/mariadb.svg"></div>                                                             | mariadb    | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Cassandra_logo.svg/2000px-Cassandra_logo.svg.png"></div>    | cassandra  | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://www.svgrepo.com/show/331488/mongodb.svg"></div>                                                               | mongodb    | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://couchdb.apache.org/image/couch@2x.png"></div>                                                                 | couchdb    | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                      | json       | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                      | yml        | -                 | -.-.-            | no          |
| <div align="center">-</div>                                                                                                                                                      | aeon       | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://app.safebase.io/api/share/d978186a-cce0-4606-93ac-7a62cbb00cea/logo_og.png"></div>                            | redis      | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://upload.wikimedia.org/wikipedia/en/3/3a/ArangoDB_Logo.png"></div>                                              | arangodb   | -                 | -.-.-            | no          |
| <div align="center"> <img  heigth="17px" width="17px" src="https://www.scylladb.com/wp-content/uploads/scylla-headset.png"></div>                                                | scylladb   | -                 | -.-.-            | no          |

### Analyze tools:

- [ ] check created/remove fields
- [ ] enum list update
- [ ] primaries & foreign keys check
- [ ] nullable value check 