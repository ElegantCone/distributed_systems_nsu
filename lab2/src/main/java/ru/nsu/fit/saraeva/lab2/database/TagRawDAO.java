package ru.nsu.fit.saraeva.lab2.database;

import ru.nsu.fit.saraeva.lab2.generated.Tag;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class TagRawDAO implements ITagDAO {
    private final Statement statement;
    private static final String INSERT =
            "INSERT INTO tags (node_id, k, v) VALUES ('";

    public TagRawDAO(DBConnection connection) throws SQLException {
        statement = connection.getConnection().createStatement();
    }

    @Override
    public void insertTag(Tag tag, BigInteger nodeId) throws SQLException {
        statement.execute(INSERT + nodeId + "', '" + tag.getK() + "', '" + tag.getV() + "')");
    }
}
