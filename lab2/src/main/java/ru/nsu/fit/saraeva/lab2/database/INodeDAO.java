package ru.nsu.fit.saraeva.lab2.database;

import ru.nsu.fit.saraeva.lab2.generated.Node;

import java.sql.SQLException;

public interface INodeDAO extends AutoCloseable {
    void insertNode(Node node) throws SQLException;

    @Override
    default void close() throws Exception {
    }
}
