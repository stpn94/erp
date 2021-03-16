package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.daoImpl.DepartmentDaoImpl;
import erp.daoImpl.EmployeeDaoImpl;
import erp.daoImpl.TitleDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
	
	public List<Department> showDeptList(){
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public List<Employee> showEmployeeByDept(Department dept){
		return employeeDao.selectEmployeeByDept(dept);
	}
	
	public List<Employee> showEmployees(){
		return employeeDao.selectEmployeeByAll();
	}
	
	public void removeEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee);
	}
	
	public void modifyEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}
	
	public void addEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
}
