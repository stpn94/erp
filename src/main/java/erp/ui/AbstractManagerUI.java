package erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp.dto.Title;
import erp.ui.content.AbstractContentPanel;
import erp.ui.content.TitlePanel;
import erp.ui.exception.InvalidCheckException;
import erp.ui.exception.SqlConstraintException;
import erp.ui.list.AbstractCustomTablePanel;
import erp.ui.list.TitleTablePanel;

public abstract class AbstractManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd; //접근가능해야하니까 protected
	private JButton btnClear;

	protected AbstractContentPanel<Title> pContent; //접근가능해야하니까 protected
	protected AbstractCustomTablePanel<Title> pList; //접근가능해야하니까 protected

//	private TitleService service; 삭제 //자식에서 구현

	public AbstractManagerUI() {
		setService();// service 연결
		initialize();
		tableLoadData();// component load data
	}

	

	private void initialize() {
//		setTitle("직책 관리"); main에서 수행 (삭제)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = createContentPanel();
		contentPane.add(pContent);

		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);

		pList = createTablePanel();

		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this); // this로 변경
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this); // this로 변경
		popMenu.add(deleteItem);

		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(this); // this로 변경
		popMenu.add(empListByTitleItem);

		return popMenu;
	}
	// actionPerformed 안에 If문 합치고 하나 지움

//	ActionListener popupMenuListner = new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			try {
//				
//				}
//				
//			}catch (NotSelectedException | SqlConstraintException e2) {
//				JOptionPane.showMessageDialog(null, e2.getMessage());
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//
//		
//	};

	public void actionPerformed(ActionEvent e) {
		
		// Try문 전체로 뒤집어쓰기
		try {
			if (e.getSource() instanceof JMenuItem) { // <- 추가
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete(); // 메서드로 빼기
				}

				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate(); // 메서드로 빼기
				}

				if (e.getActionCommand().contentEquals("동일 직책 사원 보기")) {
					/*
					 * 1. EmployeeDao -> selectEmployeeByTitle() 추가 2. EmployeeDaoImpl ->
					 * selectEmployeeByTitle() 구현 3. EmployeeDaoTest -> Test하기 4. TitleService ->
					 * EmployeeDaoImpl field 추가 및 메서드 추가 5. 아래 기능 추가 6. 예외찾아서 추가하기 (신규 직책 추가 시
					 * NullPointException)
					 */
					actionPerformedMenuGubun(); // 메서드로 빼기
				}
			} else { //밑에꺼는 else로 묶어주기
				if (e.getSource() == btnClear) {
					actionPerformedBtnClear(e);
				}
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected abstract void setService(); // 자식에서 구현

	protected abstract void tableLoadData(); // 추상메서드로 빼기

	protected abstract TitleTablePanel createTablePanel(); // 추상메서드로 빼기

	protected abstract TitlePanel createContentPanel(); // 추상메서드로 빼기
	// 메서드들 밖으로 빼기
	protected abstract void actionPerformedMenuGubun();// 추상메서드로 빼기
		
	protected abstract void actionPerformedMenuUpdate(); // 추상메서드로 빼기

	protected abstract void actionPerformedMenuDelete();// 추상메서드로 빼기

	protected abstract void actionPerformedBtnUpdate(ActionEvent e);// 추상메서드로 빼기

	protected abstract void actionPerformedBtnAdd(ActionEvent e);// 추상메서드로 빼기
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
