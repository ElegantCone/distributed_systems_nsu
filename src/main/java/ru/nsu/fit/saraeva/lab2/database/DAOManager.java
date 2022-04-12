package ru.nsu.fit.saraeva.lab2.database;

import lombok.Getter;

import java.io.IOException;
import java.sql.SQLException;

@Getter
public class DAOManager {
    public enum DAOType {
        Raw,
        Prepared,
        Batch
    }

    public static DAOManager manager;

    private final INodeDAO node;
    private final ITagDAO tag;
    private final DBConnection connection;

    public DAOManager(DAOType type) throws SQLException, IOException {
        manager = this;
        connection = new DBConnection();

        switch (type) {
            case Raw:
                node = new NodeRawDAO(connection);
                tag = new TagRawDAO(connection);
                break;
            case Prepared:
                node = new NodePreparedDAO(connection);
                tag = new TagPreparedDAO(connection);
                break;
            case Batch:
                node = new NodeBatchDAO(connection);
                tag = new TagBatchDAO(connection);
                break;
            default:
                node = null;
                tag = null;
        }
    }

    public void close() throws Exception {
        node.close();
        tag.close();
        connection.getConnection().commit();
        connection.getConnection().close();
    }
}
