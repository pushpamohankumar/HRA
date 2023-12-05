package pojoclassPackage;

public class Employee {
String ename;
long phno;
int age;
public Employee(String ename, long phno, int age) {
	super();
	this.ename = ename;
	this.phno = phno;
	this.age = age;
}

//empty constructor for only deserialization 
// for serialization without empty constructor also we can
public Employee() {
	
}

public String getEname() {
	return ename;
}

public void setEname(String ename) {
	this.ename = ename;
}

public long getPhno() {
	return phno;
}

public void setPhno(long phno) {
	this.phno = phno;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

}
