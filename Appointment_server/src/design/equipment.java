package design;

import java.util.*;

public class equipment {

	String no;
//	String type;
//	String name;
	String location;
	//Vector<String> region;
	//Vector<Integer> time;
	int firstdate;
	int firsttime;
	//Vector<String> condition;
	int maxnum;
	Vector<Vector<Integer>> num;
	equipment(String no,String location,int firstdate,int firsttime,int maxnum,Vector<Vector<Integer>> num){
		this.no=no;
		this.location=location;
		this.firstdate=firstdate;
		this.firsttime=firsttime;
		this.maxnum=maxnum;
		this.num=num;
	}
}
