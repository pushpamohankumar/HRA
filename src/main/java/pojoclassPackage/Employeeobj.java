package pojoclassPackage;

public class Employeeobj {

	String ename;
	int age;
	int[] phno;
	Spouse spouse;
	
	public Employeeobj(String ename, int age, int[] phno, Spouse spouse) {
		super();
		this.ename = ename;
		this.age = age;
		this.phno = phno;
		this.spouse = spouse;
	}

	public Employeeobj() {
		super();
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int[] getPhno() {
		return phno;
	}

	public void setPhno(int[] phno) {
		this.phno = phno;
	}

	public Spouse getSpouse() {
		return spouse;
	}

	public void setSpouse(Spouse spouse) {
		this.spouse = spouse;
	}
	
	
	
	
	
}