package ser;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * 该类必须支持序列化
 * @author zhangxuepei
 *
 */
public class Employee implements Serializable {
	int employeeId;
	String employeeName;
	String department;
	Map<String,Serializable> parmater;
	URL url;
    public Employee() throws MalformedURLException {
    	parmater=new HashMap();
    	parmater.put("url", new URL("file://D:/test"));
    	parmater.put("test", new Boolean("true"));
    }
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	public URL getUrl(URL url) {
		return this.url;
	}
	public void Changepa() {
		parmater.replace("test", new Boolean("false"));
	}
	public boolean getdsad() {
		return (boolean) parmater.get("test");
	}

}
