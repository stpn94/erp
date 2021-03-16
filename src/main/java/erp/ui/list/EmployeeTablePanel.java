package erp.ui.list;

import javax.swing.SwingConstants;

import erp.dto.Employee;
import erp.service.EmployeeService;

public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {

	private EmployeeService service = new EmployeeService() ;
	
	public EmployeeTablePanel() {
	}
	
	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);
		setTableCellAlign(SwingConstants.RIGHT,4);
		// 컬럼별 너비 조정
		setTableCellWidth(100, 250,100,100,100,100);
	}

	@Override
	public Object[] toArray(Employee e) {
		return new Object[] { e.getEmpNo(), e.getEmpName(),String.format("%s(%d)", e.getTitle().gettName(),e.getTitle().gettNo()),
				String.format("%s(%d)", e.getManager().getEmpName(),e.getManager().getEmpNo()),
				String.format("%d", e.getSalary()),
				String.format("%s(%d)", e.getDept().getDeptName(),e.getDept().getDeptNo())};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "사원번호","사원명","직책 ","직속상사","급여","부서" };
	}

	@Override
	public void initList() {
		list = service.showEmployees();
	}

	public void setService(EmployeeService service) {
		this.service = service;
	}

}
