package erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmployeeDetail {
	public EmployeeDetail() {
	}
	public EmployeeDetail(int empNo, boolean gender, Date hireDate, String pass, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hireDate = hireDate;
		this.pass = pass;
		this.pic = pic;
	}
	private int empNo;
	private boolean gender;
	private Date hireDate;
	private String pass;
	private byte[] pic;
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	@Override
	public String toString() {
		return String.format("EmployeeDetail [empNo=%s, gender=%s, hireDate=%s, pass=%s, pic=%s]", empNo, gender,
				hireDate, pass, Arrays.toString(pic));
	}

	
}
