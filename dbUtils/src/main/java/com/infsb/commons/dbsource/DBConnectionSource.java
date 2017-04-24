package com.infsb.commons.dbsource;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXB;

import com.infsb.commons.utils.DataSourcesConfigurations;
import com.infsb.commons.utils.DatasourceItems;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DBConnectionSource {

	private static DBConnectionSource dbConnectionSource;
	private Map<String, BoneCP> connectionPoolMap = new HashMap<String, BoneCP>();
	private BoneCP connectionPool;

	private DBConnectionSource() throws IOException, SQLException, PropertyVetoException {
		try {
			// load the database driver (make sure this is in your classpath!)
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		DataSourcesConfigurations dbconfigs = JAXB.unmarshal(new File("conf/datasources/datasource.xml"),
				DataSourcesConfigurations.class);

		if (dbconfigs != null) {
			for (DatasourceItems item : dbconfigs.items)
				// System.out.println(item.source_name + ": " + item.host_ip);

				if (item.type.equalsIgnoreCase("MYSQL")) {

					try {
						// setup the connection pool using BoneCP Configuration
						BoneCPConfig config = new BoneCPConfig();
						// jdbc url specific to your database, eg
						// jdbc:mysql://127.0.0.1/yourdb
						config.setPoolName(item.source_name);
						config.setJdbcUrl("jdbc:mysql://" + item.host_ip + "/" + item.db_name);
						config.setUsername(item.username);
						config.setPassword(item.password);
						config.setMinConnectionsPerPartition(Integer.parseInt(item.min_conn));
						config.setMaxConnectionsPerPartition(Integer.parseInt(item.max_conn));
						config.setPartitionCount(Integer.parseInt(item.partition_count));
						// setup the connection pool
						connectionPool = new BoneCP(config);
						connectionPoolMap.put(item.source_name, connectionPool);
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
		}
	}

	public static DBConnectionSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (dbConnectionSource == null) {
			dbConnectionSource = new DBConnectionSource();
			return dbConnectionSource;
		} else {
			return dbConnectionSource;
		}
	}

	public Connection getConnection(String source) throws SQLException {
		return this.connectionPoolMap.get(source).getConnection();

	}

}
