package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            if (cn.getMetaData().getTables(
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
            try (Statement st = cn.createStatement()) {
                st.execute(reader.lines().collect(Collectors.joining()));
            }
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement(
                "INSERT INTO items (name, created) VALUES (?, ?)")) {
            st.setString(1, item.getName());
            st.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            try (PreparedStatement st = cn.prepareStatement(
                    "UPDATE ITEMS set name = ?, created = ? where id = ?")) {
                st.setString(1, item.getName());
                st.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
                st.setInt(3, index);
                st.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return rsl;
    }

    private int indexOf(int id) {
        int rsl = -1;
        try (Statement st = cn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT " + "id" + " FROM " + "items");
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

    @Override
    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            try (PreparedStatement st = cn.prepareStatement(
                    "DELETE FROM items where id = ?")) {
                st.setInt(1, id);
                st.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM items");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                LocalDateTime time = resultSet.getTimestamp(3).toLocalDateTime();
                rsl.add(new Item(id, name, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}