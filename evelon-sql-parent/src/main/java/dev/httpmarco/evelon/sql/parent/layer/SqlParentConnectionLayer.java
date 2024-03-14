package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.builder.BuildProcess;
import dev.httpmarco.evelon.common.credentials.Credentials;
import dev.httpmarco.evelon.common.filtering.LayerFilterHandler;
import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;
import dev.httpmarco.evelon.common.query.Query;
import dev.httpmarco.evelon.common.query.SortedOrder;
import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.query.response.UpdateResponse;
import dev.httpmarco.evelon.common.repository.InitializeRepository;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.sql.parent.builder.SqlQueryBuilder;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.filtering.SqlFilterHandler;
import dev.httpmarco.evelon.sql.parent.model.SqlModel;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Accessors(fluent = true)
public abstract class SqlParentConnectionLayer implements ConnectableEvelonLayer<Object, Credentials, Connection>, InitializeRepository {

    private final String id;
    private boolean active = false;
    private final HikariConnection connection;

    private final SqlModel model = new SqlModel();
    private final SqlFilterHandler filterHandler = new SqlFilterHandler();

    public SqlParentConnectionLayer(String id, ProtocolDriver<? extends Credentials> driver) {
        this.id = id;
        this.connection = new HikariConnection(driver);
    }

    @Override
    public void initialize() {
        this.active = true;
        this.connection.connect(Evelon.instance().credentialsService().credentials(this));
    }

    @Override
    public void close() {
        this.connection.close();
    }

    @Override
    public <T> void initializeRepository(Repository<T> repository) {
        var builder = SqlQueryBuilder.emptyInstance(repository.name(), model, BuildProcess.INITIALIZE, connection);
        repository.clazz().stageOf(model).asSubStage().initialize(repository, repository.name(), this.model, null, repository.clazz().asObjectClass(), builder);
        builder.update().close();
    }

    @Override
    public UpdateResponse create(Query<Object> query, Object value) {
        var response = new UpdateResponse();
        var builder = SqlQueryBuilder.emptyInstance(query.repository().name(), model, BuildProcess.CREATION, connection);

        var stage = query.repository().clazz().stageOf(model).asSubStage();
        stage.create(value, query.repository().name(), this.model, null, query.repository().clazz().asObjectClass(), builder);

        response.append(builder.update());
        return response.close();
    }

    @Override
    public void createIfNotExists(Query<Object> query, Object value) {
        //todo
    }

    @Override
    public UpdateResponse deleteAll(Query<Object> query) {
        var response = new UpdateResponse();
        var builder = newBuilder(query, BuildProcess.DELETION);
        response.append(builder.update().close());
        return response.close();
    }

    @Override
    public void delete(Query<Object> query, Object value) {
        //todo
    }

    @Override
    public void update(Query<Object> query, Object value) {
        //todo
    }

    @Override
    public void updateIf(Query<Object> query, Object value, Predicate<Object> predicate) {
        //todo
    }

    @Override
    public void upsert(Query<Object> query, Object value) {
        //todo
    }

    @Override
    public List<Object> findAll(Query<Object> query) {
        //todo
        return null;
    }

    @Override
    public List<Object> findAll(Query<Object> query, int limit) {
        //todo
        return null;
    }

    @Override
    public QueryResponse<Object> findFirst(Query<Object> query) {
        var response = new QueryResponse<>();
        var builder = newBuilder(query, BuildProcess.FINDING);
        var stage = query.repository().clazz().stageOf(model).asSubStage();
        return response.result(stage.construct(model, query.repository().clazz(), builder));
    }

    @Override
    public QueryResponse<Boolean> exists(Query<Object> query) {
        var builder = newBuilder(query, BuildProcess.EXISTS);
        return builder.query(ResultSet::next, false).close();
    }

    @Override
    public long count(Query<Object> query) {
        //todo
        return 0;
    }

    @Override
    public long sum(Query<Object> query, String id) {
        //todo
        return 0;
    }

    @Override
    public double avg(Query<Object> query, String id) {
        //todo
        return 0;
    }

    @Override
    public List<Object> order(Query<Object> query, String id, int limit, SortedOrder order) {
        //todo
        return null;
    }

    @Override
    public <E> List<E> collect(Query<Object> query, String id, Class<E> clazz) {
        //todo
        return null;
    }

    @Override
    public <E> List<E> collect(Query<Object> query, String id, int limit, Class<E> clazz) {
        //todo
        return null;
    }

    @Override
    public <E> E collectSingle(Query<Object> query, String id, Class<E> clazz) {
        //todo
        return null;
    }

    @Override
    public Object max(Query<Object> query, String id) {
        //todo
        return null;
    }

    @Override
    public Object min(Query<Object> query, String id) {
        //todo
        return null;
    }

    private SqlQueryBuilder newBuilder(Query<?> query, BuildProcess type) {
        return SqlQueryBuilder.emptyInstance(query.repository().name(), model, type, connection);
    }
}
