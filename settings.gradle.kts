rootProject.name = "evelon"

include("evelon-common")
include("evelon-demo")

include("evelon-sql-parent")
include("evelon-sql-h2")
include("evelon-sql-mariadb")
include("evelon-sql-mysql")
include("evelon-sql-timescaledb")
include("evelon-sql-postgresql")

include("evelon-document-parent")
include("evelon-document-redis")
