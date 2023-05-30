package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class SqlTracker implements Store {

    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.connection = cn;
    }

    private void init() {
        try (InputStream in = new FileInputStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            if (connection.getMetaData().getTables(
                    null, null, "items", new String[]{"TABLE"}) == null) {
                createTable();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void createTable() throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./db/001_ddl_create_items_table.sql"))) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(reader.lines().collect(Collectors.joining()));
            }
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st = connection.prepareStatement(
                "INSERT INTO items (name, created) VALUES (?, ?)")) {
            st.setString(1, item.getName());
            st.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        if (indexOf(id) != -1) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ITEMS set name = ?, created = ? where id = ?")) {
                statement.setString(1, item.getName());
                statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
                statement.setInt(3, id);
                rsl = statement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        if (indexOf(id) != -1) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM items where id = ?")) {
                statement.setInt(1, id);
                rsl = statement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rsl.add(new Item(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getTimestamp(3).toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        return findAll().stream().filter(s -> key.equals(s.getName())).collect(Collectors.toList());
    }

    @Override
    public Item findById(int id) {
        return findAll().stream().filter(s -> id == s.getId()).findFirst().orElse(null);
    }

    private int indexOf(int id) {
        int rsl = -1;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT " + "id" + " FROM " + "items");
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    rsl = id;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}