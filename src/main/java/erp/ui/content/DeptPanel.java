package erp.ui.content;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import erp.dto.Department;
import erp.ui.exception.InvalidCheckException;

public class DeptPanel extends AbstractContentPanel<Department> {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

	public DeptPanel() {
		initialize();
	}
	
	public JTextField getTfDeptNo() {
		return tfDeptNo;
	}

	public void setTfDeptNo(JTextField tfDeptNo) {
		this.tfDeptNo = tfDeptNo;
	}

	public JTextField getTfDeptName() {
		return tfDeptName;
	}

	public void setTfDeptName(JTextField tfDeptName) {
		this.tfDeptName = tfDeptName;
	}

	public JTextField getTfFloor() {
		return tfFloor;
	}

	public void setTfFloor(JTextField tfFloor) {
		this.tfFloor = tfFloor;
	}
	
	private void initialize() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uBD80\uC11C\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblDeptNo = new JLabel("부서번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);
		
		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		add(tfDeptName);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		add(tfFloor);
	}
	
	
	
	@Override
	public void clearTf() {
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");
	}

	@Override
	public void setItem(Department item) {
//		tfDeptNo.setText(String.valueOf(department.getDeptNo()));
		tfDeptNo.setText(item.getDeptNo()+"");
		tfDeptName.setText(item.getDeptName()+"");
		tfFloor.setText(item.getFloor()+"");
		
	}

	@Override
	public Department getItem() {
		int deptNo =Integer.parseInt(tfDeptNo.getText().trim());
		String deptName = tfDeptName.getText().trim();
		int floor =Integer.parseInt(tfFloor.getText().trim());
		
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public void validCheck() {
		if (tfDeptNo.getText().contentEquals("") || tfDeptName.getText().equals("") || tfFloor.getText().equals("")) {
			throw new InvalidCheckException();
		}
		
	}
	
}
