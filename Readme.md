
## Evelon
Last update: 14.07.2023

- avg
- sum
- clear
- sorted

Todo types:
- vector
- date
- maps
- collections
- array (transformer -> list)
- Files

Features todo:

- better database connection configuration
- map functions for convert results to a fine object
- listeners (onCreate, onDelete, onUpdate)
- time value cache
- changetracking list
- changetracking map

Interface support? Superclass support?

### Table exists analysis

- Check if new primaries are created and proof if they remove old primaries [ ]
- Check names and all types of existing rows [ ]
- Check sub tables from maps or list elements [ ]
- Check if values can be null or not. Remove or add these options in a row. [ ]
- Check the table rename option (show if the Entity annotation is present and override the table name) [ ]
- if new rows are created, they use default parameters and add this to current columns
- check if all columns have the right type
- if enumeration has changed, check if the new values are in the database and add them if not

### Filters
- `Match` Check if the database set contains with the current filter value. 
- `NoneMatch` Check if the database set does not contain with the current filter value.
- `Min` Check if the number is equal or higher than the given filter number. (Only for numbers)
- `Max` Check if the number is equal or lower than the given filter number. (Only for numbers)
- `Between` Check if the number is between the given filter numbers. (Only for numbers)

### Supported types

1. Default java parameters


### Storages

A storage is a place where you can save your data. Evelon has two types of storages. Database and local storage. Storages represent a current layer of of saving data. Local Storage is very fast, but not save permanently the data. The database layer is some lower, but this saves the data. 

**LocalStorage** - The local storage saves only the data in runtime and not in a database. But you can use all query methode like in the database storage. 

**DatabaseSorage** - The database storage saves the data in a database. You can use all query methode like in the local storage. You can use sync or async methods. 
Also you can  use the default sql schema with primary keys and foreign keys. Evelon can create the database for you and add automatically the tables and primary keys. Also evelon accept the default normal-form of sql. 