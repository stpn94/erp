package erp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;
import erp.service.EmployeeService;
import erp.ui.content.EmployeePanel;

public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSet;
	private EmployeePanel pEmpItem;
	private JButton btnCencel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TestFrame() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		EmployeeService service =new EmployeeService();
		pEmpItem = new EmployeePanel();
		pEmpItem.setService(service);
		
		contentPane.add(pEmpItem);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnAdd = new JButton("추가");
		panel.add(btnAdd);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(this);
		panel.add(btnSet);
		
		btnCencel = new JButton("취소");
		btnCencel.addActionListener(this);
		panel.add(btnCencel);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCencel) {
			actionPerformedBtnCencel(e);
		}
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
		Employee emp = new Employee (1003,"조민희",new Title(3),new Employee(4377),3000000,new Department(2));
		pEmpItem.setItem(emp);
	}
	protected void actionPerformedBtnCencel(ActionEvent e) {
		pEmpItem.clearTf();
	}
}
