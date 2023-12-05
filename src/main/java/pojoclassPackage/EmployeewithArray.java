package pojoclassPackage;

public class EmployeewithArray {

	String ename;
	String empid;
	int[] phno;
	
	public EmployeewithArray(String ename, String empid, int[] phno) {
		super();
		this.ename = ename;
		this.empid = empid;
		this.phno = phno;
	}
	
	public EmployeewithArray() {
		
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public int[] getPhno() {
		return phno;
	}

	public void setPhno(int[] phno) {
		this.phno = phno;
	}
	
	
}
