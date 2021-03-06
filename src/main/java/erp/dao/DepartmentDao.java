package erp.dao;

import java.util.List;

import erp.dto.Department;

/*
 * Data Access Object
 * C(insert)
 * R(select, select where)
 * U(update)
 * D(delete)
 */
public interface DepartmentDao {
	List<Department> selectDepartmentByAll();

	Department selectDepartmentByNo(Department department);

	int insertDepartment(Department department);

	int updateDepartment(Department department);

	int deleteDepartment(int deptNo);
}
