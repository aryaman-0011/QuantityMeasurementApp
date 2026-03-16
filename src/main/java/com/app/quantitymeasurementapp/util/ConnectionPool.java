package com.app.quantitymeasurementapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {

	private static final Queue<Connection> pool = new ArrayDeque<>();

	static {

		try {

			int size = Integer.parseInt(ApplicationConfig.getProperty("db.pool.size"));

			for (int i = 0; i < size; i++) {

				Connection conn = DriverManager.getConnection(ApplicationConfig.getProperty("db.url"),
						ApplicationConfig.getProperty("db.username"), ApplicationConfig.getProperty("db.password"));

				pool.add(conn);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized Connection getConnection() {

		if (pool.isEmpty()) {
			throw new RuntimeException("No DB connections available");
		}

		return pool.poll();
	}

	public static synchronized void release(Connection conn) {

		if (conn != null) {
			pool.offer(conn);
		}
	}
}
