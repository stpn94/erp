package erp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.daoImpl.EmployeeDetailDaoImpl;
import erp.dto.Employee;
import erp.dto.EmployeeDetail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EmployeeDetailDaoTest {
	private EmployeeDetailDao dao = EmployeeDetailDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01InsertEmployeeDetail() {
		System.out.println("test01InsertEmployeeDetail");

		EmployeeDetail empDetail = new EmployeeDetail(1003, true, new Date(), "1234", getImage("NoImage.jpg"));
		int res = dao.insertEmployeeDetail(empDetail);

		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
		// /image/imgName
		File file = new File(System.getProperty("user.dir") + File.separator + "image", imgName);
		try (InputStream is = new FileInputStream(file)) {
			pic = new byte[is.available()];// 파일로 부터 읽은 이미지의 바이트길이를 배열 생성한다.
			is.read(pic);
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return pic;
	}

	@Test
	public void test02SelectEmployeeDetailByNo() {
		System.out.printf("%s()%n", "test02SelectEmployeeDetailByNo");
		Employee newEmp = new Employee(1003);
		EmployeeDetail searchEmployeeDetail = dao.selectEmployeeDetailByNo(newEmp);
		Assert.assertNotNull(searchEmployeeDetail);
		System.out.println(searchEmployeeDetail);
	}

	@Test
	public void test03UpdateEmployeeDetail() {
		System.out.printf("%s()%n", "test03UpdateEmployeeDetail");

		Calendar cal = GregorianCalendar.getInstance();
		cal.getTime();

		EmployeeDetail empDetail = new EmployeeDetail(1003, false, cal.getTime(), "1234", getImage("배.jpg"));
		int res = dao.updateEmployeeDetail(empDetail);

		Assert.assertEquals(1, res);

		System.out.println(dao.selectEmployeeDetailByNo(new Employee(1003)));
	}

//	@Test
	public void test04DeleteEmployeeDetail() {
		System.out.printf("%s()%n", "testDeleteEmployee");
		Employee employee = new Employee(1003);
		int res = dao.deleteEmployeeDetail(employee);

		Assert.assertEquals(1, res);
	}

}
