package erp.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;
import erp.ui.content.AbstractContentPanel;
import erp.ui.list.EmployeeTablePanel;

public class EmployeeManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private AbstractContentPanel<Employee> pContent;
	private JButton btnAdd;
	private JButton btnCencel;
	private EmployeeTablePanel pList;
	private EmployeeService service;

	
	public EmployeeManager() {
		service = new EmployeeService();
		initialize();
	}
	private void initialize() {
		setTitle("employeeManager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new EmployeePanel();
		((EmployeePanel) pContent).setService(service);
		contentPane.add((Component) pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCencel = new JButton("취소");
		btnCencel.addActionListener(this);
		pBtns.add(btnCencel);
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCencel) {
			actionPerformedBtnCencel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee employee = pContent.getItem();
		
		service.insertEmployee(pContent.getItem());
		JOptionPane.showMessageDialog(null, employee + " 추가되었습니다.");
		pList.loadData();
	}
	protected void actionPerformedBtnCencel(ActionEvent e) {
		pContent.clearTf();
	}
}
