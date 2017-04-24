package com.infsb.commons.utils;

public final class Parameters {

	public static String JDBC_URL;
	public static String DB_NAME;
	public static String MIN_CONN;
	public static String MAX_CONN;
	public static String NO_PARTITIONS;
	public static String DB_USERNAME;
	public static String DB_PASSWORD;

	Parameters(String URL,String dbname ,String minconn,String maxconn,String partitions,String usrname,String pword){
		
		Parameters.JDBC_URL=URL;
		Parameters.DB_NAME=dbname;
		Parameters.MIN_CONN=minconn;
		Parameters.MAX_CONN=maxconn;
		Parameters.NO_PARTITIONS=partitions;
		Parameters.DB_USERNAME=usrname;
		Parameters.DB_PASSWORD=pword;
		
	}
}
