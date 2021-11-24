package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrdersStoreTest {
    private final BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/test_update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void dropTable() throws SQLException {
        pool.getConnection().prepareStatement("DROP TABLE orders").executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenUpdateOrder() {
        OrdersStore store = new OrdersStore(pool);
        Order actual = Order.of("name1", "description1");
        actual = store.save(actual);
        actual.setName("n1");
        actual.setDescription("d1");
        int i = store.update(actual);
        Order expect = store.findById(actual.getId());

        assertThat(i, is(1));
        assertThat(expect, is(actual));
    }

    @Test
    public void whenFindOrdersByName() {
        OrdersStore store = new OrdersStore(pool);
        Order actual1 = Order.of("name1", "description1");
        Order actual2 = Order.of("name1", "description2");
        Order actual3 = Order.of("name2", "description2");
        actual1 = store.save(actual1);
        actual2 = store.save(actual2);
        store.save(actual3);
        List<Order> expect = store.findByName("name1");

        assertThat(expect.get(0), is(actual1));
        assertThat(expect.get(1), is(actual2));
    }

    @Test
    public void whenFindOrderById() {
        OrdersStore store = new OrdersStore(pool);
        Order actual = Order.of("name1", "description1");
        actual = store.save(actual);
        Order expect = store.findById(1);

        assertThat(expect, is(actual));
    }
}