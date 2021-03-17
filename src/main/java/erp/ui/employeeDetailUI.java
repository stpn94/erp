package erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Employee;
import erp.dto.EmployeeDetail;
import erp.service.EmployeeDetailService;
import erp.ui.content.EmployeeDetailPanel;

public class employeeDetailUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtns;
	private EmployeeDetailPanel pItem;
	private JButton btnAdd;
	private JButton btnCencel;
	private EmployeeDetailService service;
	private JButton btnDel;
	private JButton btnUpdate;

	public employeeDetailUI(boolean isBtns, EmployeeDetailService service) {
		this.service = service;
		initialize(isBtns);
	}

	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);

		pBtns = new JPanel();
		contentPane.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCencel = new JButton();
		btnCencel.addActionListener(this);
		pBtns.add(btnCencel);

		if (isBtns) {
			btnAdd.setText("추가");
			btnCencel.setText("취소");
		} else {

			btnAdd.setText("삭제");
			btnCencel.setText("수정");
		}
	}

	public void setEmpNo(Employee empNo) {
		pItem.setTfEmpno(empNo);

	}

	public void setDetailItems(EmployeeDetail empDetail) {
//		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("삭제")) {
			actionPerformedBtnDel(e);
		}
		if (e.getActionCommand().contentEquals("취소")) {
			actionPerformedBtnCencel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail updateEmpDetail = pItem.getItem();
		service.modifyEmployeeDetail(updateEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "수정 완료");
		dispose();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		EmployeeDetail newEmpDetail = pItem.getItem();
		service.addEmployeeDetail(newEmpDetail);
		JOptionPane.showMessageDialog(null, "추가 완료");
		pItem.clearTf();
		dispose();
	}

	protected void actionPerformedBtnCencel(ActionEvent e) {
		pItem.clearTf();
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
		
	}

	protected void actionPerformedBtnDel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo()));
		JOptionPane.showMessageDialog(null, "삭제완료");
		pItem.clearTf();
	}
}
