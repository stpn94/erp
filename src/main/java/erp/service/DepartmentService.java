package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.daoImpl.DepartmentDaoImpl;
import erp.dto.Department;

public class DepartmentService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();

	public List<Department> showDeptList() {
		return dao.selectDepartmentByAll();
	}

	public void addDepartment(Department department) {
		dao.insertDepartment(department);
	}

	public void removeDepartment(Department department) {
		dao.deleteDepartment(department.getDeptNo());
	}

	public void modifyDepartment(Department department) {
		dao.updateDepartment(department);
	}
}