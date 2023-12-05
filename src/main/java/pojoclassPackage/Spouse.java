package pojoclassPackage;

public class Spouse {

	String name;
	int age;
	int[] ph;
	
	
	public Spouse(String name, int age, int[] phno) {
		super();
		this.name = name;
		this.age = age;
		this.ph = ph;
	}
	
	public Spouse() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int[] getPhno() {
		return ph;
	}

	public void setPhno(int[] phno) {
		this.ph = ph;
	}
	
}
