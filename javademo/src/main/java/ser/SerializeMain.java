package ser;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeMain {
	public static void main(String[] args) throws IOException {
		Employee emp = new Employee();
		emp.setEmployeeId(101);
		emp.setEmployeeName("Arpit");
		emp.setDepartment("CS");
		emp.Changepa();
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		byteArrayOutputStream = new ByteArrayOutputStream();
		objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(emp);
		objectOutputStream.flush();
		byte[] bytes = byteArrayOutputStream.toByteArray();

		try {
			//文件的输出流
			FileOutputStream fileOut = new FileOutputStream("employee.xml");
			//写文件 这个一定不能够有  不然开头会写入一些乱码
			//ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			XStream xStream=new XStream(new StaxDriver());

			xStream.toXML(emp,fileOut);
			//序列化成为二进制
			//outStream.writeObject(emp);
			//outStream.close();
			fileOut.close();
			de();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public static void de() {
		Employee emp = null;
		try {
			FileInputStream fileIn = new FileInputStream("employee.xml");
			XStream xstream =new XStream(new StaxDriver());
			emp = (Employee)xstream.fromXML(fileIn);
			//ObjectInputStream in = new ObjectInputStream(fileIn);
//			emp = (Employee) in.readObject();
			//in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
//		} catch (ClassNotFoundException c) {
//			System.out.println("Employee class not found");
//			c.printStackTrace();
//			return;
      	}
		System.out.println("Deserialized Employee...");
		System.out.println("Emp id: " + emp.getEmployeeId());
		System.out.println("Name: " + emp.getEmployeeName());
		System.out.println("Department: " + emp.getDepartment());
		System.out.println("Department: " + emp.getdsad());

	}
}
