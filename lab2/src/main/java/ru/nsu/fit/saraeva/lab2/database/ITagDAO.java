package ru.nsu.fit.saraeva.lab2.database;

import ru.nsu.fit.saraeva.lab2.generated.Tag;

import java.math.BigInteger;
import java.sql.SQLException;

public interface ITagDAO extends AutoCloseable {
    void insertTag(Tag tag, BigInteger nodeId) throws SQLException;

    @Override
    default void close() throws Exception {
    }
}
