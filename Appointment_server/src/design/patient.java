package design;

import java.util.*;

public class patient {
	String ID;
	String name;
	int age;
	String sex;
	int flag;
	Vector<part>part =new Vector<part>();
	patient(String ID){
		this.ID=ID;
	}

}
