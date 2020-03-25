package design;

import java.util.Vector;

public class part {
	String Name;
	String condition;
	int priority;
	Vector<equipment> equip=new Vector<equipment>();
	
	part(String str,String condition,int priority){
		this.Name=str;
		this.condition=condition;
		this.priority=priority;
	}
	
}
