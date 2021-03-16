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
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();

	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public List<Employee> showEmployeeByDept(Department dept){
		return dao.selectEmployeeBydeptNo(dept);
	}
	
	public List<Employee> showEmployees(){
		return dao.selectEmployeeByAll();
	}
	
	public void insertEmployee(Employee employee){
		dao.insertEmployee(employee);
	}
	
////////////////////////////////////////////////////////////////////////
	public List<Employee> showEmpList(Title title) {
		return dao.selectEmployeeByTitleNo(title);
	}

	
	

}
