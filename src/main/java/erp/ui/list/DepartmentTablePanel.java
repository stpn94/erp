package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Department;
import erp.service.DepartmentService;

public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {

	private DepartmentService service;

	public DepartmentTablePanel() {
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		// 컬럼별 너비 조정
		setTableCellWidth(100, 250, 100);
	}

	@Override
	public Object[] toArray(Department t) {
		return new Object[] { t.getDeptNo(), t.getDeptName(), t.getFloor() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "부서번호", "부서명", "위치" };
	}

	@Override
	public void initList() {
		list = service.showDeptList();
	}

	public void setService(DepartmentService service) {
		this.service = service;
	}

}