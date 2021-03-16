package erp.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.DepartmentService;
import erp.service.EmployeeService;
import erp.ui.content.DeptPanel;
import erp.ui.content.AbstractContentPanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.DepartmentTablePanel;

public class DepartmentManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnClear;
	private AbstractContentPanel<Department> pContent;
	private DepartmentTablePanel pList;
	private DepartmentService service;
	private EmployeeService service1;

	public DepartmentManager() {
		//
		service = new DepartmentService();
		service1 = new EmployeeService();
		

		initialize();
	}

	private void initialize() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = new DeptPanel();
		contentPane.add((Component) pContent);

		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);

		pList = new DepartmentTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);

	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem modifyItem = new JMenuItem("수정");
		modifyItem.addActionListener(popupMenuListener);
		popMenu.add(modifyItem);

		JMenuItem deleteItem = new JMenuItem("삭제");

		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);

		JMenuItem empListByDepartmentItem = new JMenuItem("동일부서사원보기");
		empListByDepartmentItem.addActionListener(popupMenuListener);
		popMenu.add(empListByDepartmentItem);

		return popMenu;
	}

	// inner class
	ActionListener popupMenuListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				if (e.getActionCommand().equals("삭제")) {
					Department delDepartment = pList.getItem();
					service.removeDepartment(delDepartment);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delDepartment + " 삭제 되었습니다.");
				}
				if (e.getActionCommand().equals("수정")) {
					btnAdd.setText("수정");
					((DeptPanel) pContent).getTfDeptNo().setEnabled(false);
					Department modiDepartment = pList.getItem();
					((DeptPanel) pContent).setItem(modiDepartment);
				}
				if (e.getActionCommand().equals("동일부서사원보기")) {
					Department selectDepartment = pList.getItem();
					List<Employee> list = service1.showEmployeeByDept(selectDepartment);
					if (list == null) {
						JOptionPane.showMessageDialog(null, "없습니다.");
					} else {
						JOptionPane.showMessageDialog(null, list);
					}
				}
			} catch (InvalidCheckException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	};

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				if (btnAdd.getText().equals("수정")) {
					actionPerformedBtnUp(e);
				} else {
					actionPerformedBtnAdd(e);
				}
			}
			if (e.getSource() == btnClear) {
				actionPerformedBtnClear(e);
			}
		} catch (InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnUp(ActionEvent e) {
		service.modifyDepartment(pContent.getItem());
		pList.loadData();

		btnAdd.setText("추가");
		((DeptPanel) pContent).getTfDeptNo().setText("");
		((DeptPanel) pContent).getTfDeptName().setText("");
		((DeptPanel) pContent).getTfFloor().setText("");
		((DeptPanel) pContent).getTfDeptNo().setEnabled(true);
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getItem();

		service.addDepartment(department);
		JOptionPane.showMessageDialog(null, department + " 추가되었습니다.");
		pList.loadData();
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
	}
}
