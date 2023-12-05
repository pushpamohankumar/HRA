package serializationanddeserialization;


import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;


import pojoclassPackage.Employee;
import pojoclassPackage.Employeeobj;
import pojoclassPackage.EmployeewithArray;
import pojoclassPackage.Spouse;

public class serialization {

	@Test
	public void serialization1() throws Throwable{
	
		ObjectMapper obj=new ObjectMapper();
		Employee emp = new Employee("Heshika", 98765432, 4);
		
		//obj.writeValue(new File("./heshika.json"), emp);
		obj.writerWithDefaultPrettyPrinter()
		.writeValue(new File("./heshika.json"),emp);
	
	}
	
	@Test
	public void deserialization() throws Throwable{
		ObjectMapper obj = new ObjectMapper();
		Employee data = obj.readValue(new File("./heshika.json"), Employee.class);
		System.out.println(data.getEname());
		System.out.println(data.getPhno());
		System.out.println(data.getAge());
	
	}
	
	@Test
	public void EmpArraycomplexdatainserialization() throws Throwable {
		
		int[] phno= {123456,678960};
		
		ObjectMapper obj = new ObjectMapper();
		
		EmployeewithArray emp = new EmployeewithArray("Heshika", "Emp_Id001", phno);
		obj.writerWithDefaultPrettyPrinter()
		.writeValue(new File("./heshika1"), emp);
		
	}
	
	@Test
	public void EmpArraycomplexdatainDEserialization() throws Throwable {
		ObjectMapper obj = new ObjectMapper();
		EmployeewithArray data = obj.readValue(new File("./heshika1"),EmployeewithArray.class);
		System.out.println(data.getEname());
		System.out.println(data.getEmpid());
		System.out.println(data.getPhno()[0]);
	}
	
	@Test
	public void objinsideobjSerialization() throws Throwable {
		 
		 int [] ph= {3456,7890};
		Spouse sp = new Spouse("Mohan", 32, ph);
		int[] phno= {1234,6789};
      Employeeobj emp = new Employeeobj("Ishu", 2, phno, sp);
		ObjectMapper obj = new ObjectMapper();
		//obj.writerWithDefaultPrettyPrinter()
		obj.writeValue(new File("./heshika1"), emp);
		
		}
	
	@Test
	public void deSerialization() throws Throwable{
		 
			ObjectMapper obj = new ObjectMapper();
			Employeeobj emp = obj.readValue(new File("./heshika1"),Employeeobj.class);
			System.out.println(emp.getEname());
			System.out.println(emp.getAge());
			System.out.println(emp.getPhno()[0]);
			System.out.println(emp.getSpouse().getName());
			System.out.println(emp.getSpouse().getAge());
			//System.out.println(emp.getSpouse());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
