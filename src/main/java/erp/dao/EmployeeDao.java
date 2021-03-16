package erp.dao;

import java.util.List;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

// Data Access Object
public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();

	Employee selectEmployeeByNo(Employee employee);

	int insertEmployee(Employee employee); // 객체

	int updateEmployee(Employee employee);

	int deleteEmployee(int EmployeeNo); // 기본타입

	List<Employee> selectEmployeeBydeptNo(Department department);

	List<Employee> selectEmployeeByTitleNo(Title title);

}
