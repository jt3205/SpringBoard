package jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {
	static final String driver = "com.mysql.cj.jdbc.Driver";
	private final static String ID = "Y20112";
	private final static String PASSWORD = "1234";
	static final String URL = "jdbc:mysql://gondr.iptime.org:3306/" + ID + "?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Seoul";

	@Override
	public void init() throws ServletException {
		System.out.println("���� ��������!");
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}

	private void initConnectionPool() {
		try {
			// ConnectionFactory ��ü�� ���� ������ ������ �غ� �Ѵ�.
			ConnectionFactory cFactory = new DriverManagerConnectionFactory(URL, ID, PASSWORD);
			// ������ Pool�� ���� �������� PoolableConnectionFactory ����
			PoolableConnectionFactory pcFactory = new PoolableConnectionFactory(cFactory, null);
			// ������ �ùٸ��� �׽�Ʈ�� ������ SELECT 1�� �����ص�
			pcFactory.setValidationQuery("SELECT 1");
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			// ���� �˻��ֱ� 5������ ����
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			// idle�����϶� �˻� ����
			poolConfig.setTestWhileIdle(true);
			// ������ �ּҰ���
			poolConfig.setMinIdle(4);
			// ������ �ִ밳��
			poolConfig.setMaxTotal(50);

			// ���� �������� pcFactory�� ���� Ǯ�� ������.
			GenericObjectPool<PoolableConnection> connPool = new GenericObjectPool<>(pcFactory, poolConfig);
			// ������� Ǯ�� pcFactory�� ��������.
			pcFactory.setPool(connPool);
			// �ش� Ǯ�� �������� Ǯ ����̹��� ����� �ű⿡ �̸��� Ǯ�� ��������. ������ gondr �̶� �̸��� DBǮ�� ���� ����
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver pDriver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			pDriver.registerPool("OrOl", connPool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
