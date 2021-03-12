package erp.database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class JdbcConnTest {

	@Test //3
	public void testGetConnection() {
		System.out.printf("%s()%n","testGetConnection");
		Connection con = JdbcConn.getConnection();
		System.out.println("con >"+con);
		Assert.assertNotNull(con);
		System.out.println();
	}

}
