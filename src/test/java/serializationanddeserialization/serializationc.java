package serializationanddeserialization;

import java.io.File;

import static  io.restassured.RestAssured.*;
import io.restassured.mapper.ObjectMapper;

import pojoclassPackage.pojocolor;

public class serializationc {
	

	//ObjectMapper ob=new ObjectMapper();
	pojocolor p=new pojocolor("Heshika", "Blue");
	
	//ob.writeValue(new File("./sample.json"),p);
	
}
