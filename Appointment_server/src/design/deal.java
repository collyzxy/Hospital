package design;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class deal {
	Vector<equipment> equips=new Vector<equipment>();//读数据库存入设备信息
	Vector<part>  regions=new Vector<part>();
	//String result;
	Vector<String> part_result=new Vector<String>();
	int fd;
	int ft;
	patient pa;
	
	Object dx;
	java.sql.Connection con;
	java.sql.Statement sql;
	ResultSet rs;
	
//	String url = "jdbc:sqlserver://localhost:1433;databaseName=Hospital";
//    String user="olly";
//    String password="199811";
    
	void getepartinfo(){
		//读数据库part信息
		try{ Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");}
		catch(ClassNotFoundException ee){System.out.println(""+ee);}
		try{
	     con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=Hospital");
		 sql=con.createStatement();
		 dx=new Object[1];
		 rs=sql.executeQuery("select * from regioninfo");
		 boolean min=rs.next();
		 while(min){
			 String s=rs.getString(1).trim();
			 String condition=rs.getString(3).trim();
			 int priority=rs.getInt(4);
			 part p=new part(s,condition,priority);
			 String str=rs.getString(2);
             String []equip=str.split(" ");
             part_create(equip,p.equip);
             regions.addElement(p);
             min=rs.next();
		 }
            }catch(SQLException ee){ 
     	        System.out.println(ee);
     	       }
	}
	
	int getepatientinfo(String ID,patient p) {
		try{ Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");}
		catch(ClassNotFoundException ee){System.out.println(""+ee);}
		try{
	     con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=Hospital");
		 sql=con.createStatement();
		 dx=new Object[1];
		 rs=sql.executeQuery("select * from patientinfo where ID='"+ID+"'");
		 boolean min=rs.next();
		 if(!min)
		 {
			 return -1;
		 }
		 while(min){
			 p.ID=rs.getString(1);
			 p.name=rs.getString(2);
			 p.age=rs.getInt(3);
			 int i=rs.getInt(4);
			 if(i==1)
				 p.sex="男";
			 else
				 p.sex="女";

			 p.flag=rs.getInt(5);
             min=rs.next();
		 }
            }catch(SQLException ee){ 
     	        System.out.println(ee);
     	       }
		return 0;
	}
	
	public part findpart(String str,Vector<part>parts) {
		part q = null;
		for(int i=0;i<parts.size();i++)
		{
			if(str.equals(parts.elementAt(i).Name))
			{		
				q=parts.elementAt(i);
				break;
			}
		}
		return q;
	}
	
	public void part_create(String []equipname,Vector<equipment> part_equip)
	{
		for(int i=0;i<equipname.length;i++)
		{
			equipment e=findequip(equipname[i],equips);
			part_equip.add(e);
		}
	}
	
	public void createpart(String []str,Vector<part>part)
	{
		for(int i=1;i<str.length;i++)
		{
			part q=findpart(str[i],regions);
			part.addElement(q);
		}
	}
	
	public equipment findequip(String str,Vector<equipment>equip) {
		equipment q = null;
		for(int i=0;i<equip.size();i++)
		{
			if(str.equals(equip.elementAt(i).no))
			{
				q=equip.elementAt(i);
				break;
			}
		}
		return q;
	}
	
	public equipment findfirst(Vector<equipment>equip)
	{
		equipment q = equip.firstElement();
		for(int i=1;i<equip.size();i++)
		{
			if(q.firstdate>equip.elementAt(i).firstdate)
				q=equip.elementAt(i);
			else if(q.firstdate==equip.elementAt(i).firstdate && q.firsttime>equip.elementAt(i).firsttime)
				q=equip.elementAt(i);
			else
				continue;
		}
		return q;
	}
	
	public equipment findfirst2(Vector<equipment>equip)
	{
		int j=0;
		equipment q=null;
		for(j=0;j<equip.size();j++)
		{
			q = equip.elementAt(j);
			if((q.maxnum-q.num.elementAt(q.firstdate).elementAt(q.firsttime))>1)
				break;
		}
		q = equip.elementAt(j);
		for(int i=j+1;i<equip.size();i++)
		{
			if(q.firstdate>equip.elementAt(i).firstdate)
			{
				if((equip.elementAt(i).maxnum-equip.elementAt(i).num.elementAt(equip.elementAt(i).firstdate).elementAt(equip.elementAt(i).firsttime))>1)
					q=equip.elementAt(i);
				else 
					continue;                                                                                                                                                                                                                                                            
			}
			else if(q.firstdate==equip.elementAt(i).firstdate && q.firsttime>equip.elementAt(i).firsttime)
			{
				if((equip.elementAt(i).maxnum-equip.elementAt(i).num.elementAt(equip.elementAt(i).firstdate).elementAt(equip.elementAt(i).firsttime))>1)
					q=equip.elementAt(i);
				else 
					continue; 
			}
			else
				continue;
		}
		return q;
	}
	
	public void timeset(equipment e)
	{
		int i=e.num.elementAt(e.firstdate).elementAt(e.firsttime);
		if(e.maxnum==i)
		{
			if(e.firsttime==1)
			{
				e.firstdate++;
				e.firsttime=0;
				if(e.num.size()<e.firstdate+1)
				{
					Vector<Integer> number=new Vector<Integer>();
					number.addElement(1);
					number.addElement(0);
					e.num.addElement(number);
					return;
				}
			}
			for(int j=e.firstdate;j<e.num.size();j++)
			{
				if(e.num.elementAt(j).elementAt(0)<e.maxnum)
				{
					e.firstdate=j;
					e.firsttime=0;
					break;
				}
				else if(e.num.elementAt(j).elementAt(1)<e.maxnum)
				{
					e.firstdate=j;
					e.firsttime=1;
					break;
				}
			}
		}
	}
	
	public equipment findtime(equipment e1,Vector<equipment> e)
	{
		equipment e2=null;
		int time=9999;
		for(int i=0;i<e.size();i++)
		{
			int time1=calculateTime(e1,e.elementAt(i));
			if(e.elementAt(i).firstdate>e1.firstdate && time>time1 )
			{
				time=time1;
				e2=e.elementAt(i);
			}
			else if(e.elementAt(i).firstdate==e1.firstdate && time>time1) 
			{	
				if(e.elementAt(i).firsttime>e1.firsttime)
				{
					time=time1;
					e2=e.elementAt(i);
				}
				else if(e.elementAt(i).firsttime==e1.firsttime)
				{
					if(e.elementAt(i).num.elementAt(e.elementAt(i).firstdate).elementAt(e.elementAt(i).firsttime)>e1.num.elementAt(e1.firstdate).elementAt(e1.firsttime))
					{
						time=time1;
						e2=e.elementAt(i);
					}
					else
					{
						e.elementAt(i).firsttime++;
						e2=e.elementAt(i);
					}
				}
			}
		}
		return e2;
	}
	
	public void timereset(Vector<part> part2)
	{
		for(int i=0;i<part2.size();i++)
		{
			Vector<equipment> p=part2.elementAt(i).equip;
			L1:for(int j=0;j<p.size();j++)
			{
				for(int k=0;k<p.elementAt(j).num.size();k++)
				{
					if(p.elementAt(j).num.elementAt(j).elementAt(0)<p.elementAt(j).maxnum)
					{
						p.elementAt(j).firstdate=k;
						p.elementAt(j).firsttime=0;
						continue L1;
					}
					else if(p.elementAt(j).num.elementAt(j).elementAt(1)<p.elementAt(j).maxnum)
					{
						p.elementAt(j).firstdate=k;
						p.elementAt(j).firsttime=1;
						continue L1;
					}
				}
			}
		}
	}
	
	public int calculateTime(equipment e1,equipment e2)
	{
		int minutes=0;
		int date=e1.firstdate-e2.firstdate;
		int hour=e1.firsttime-e2.firsttime;
		int person=e1.num.elementAt(e1.firstdate).elementAt(e1.firsttime)-e2.num.elementAt(e2.firstdate).elementAt(e2.firsttime);
		minutes=Math.abs(date)*1440+Math.abs(hour)*720+Math.abs(person);
		return minutes;
	}
	
	void update(equipment equip)
	{
		if(fd<equip.firstdate)
		{
			fd=equip.firstdate;
			ft=equip.firsttime;
		}
		if(fd==equip.firstdate && ft<equip.firsttime)
			ft=equip.firsttime;
	}
	
	public void yuyue(Vector<part> parts,patient p,Vector<String> part_result)
	{
		if(parts.size()==1)//查一项
		{
			equipment e=findfirst(parts.get(0).equip);
			int i=e.num.elementAt(e.firstdate).elementAt(e.firsttime);	
			e.num.elementAt(e.firstdate).set(e.firsttime,i+1);
			part_result.addElement(parts.get(0).Name+","+e.no+","+e.firstdate+","+e.firsttime+","+e.location+","+parts.get(0).condition+","+i);
			timeset(e);
		}
		if(parts.size()==2)//查两项
		{
			part part1=parts.get(0);
			part part2=parts.get(1);
			//连数据库
			if(parts.get(0).priority==parts.get(1).priority)//无冲突
			{
				int count=0;
				Vector<equipment> sameequip=new Vector<equipment>();
				for(int i=0;i<part1.equip.size();i++)
				{
					for(int j=0;j<part2.equip.size();j++)
					{
						if(part1.equip.get(i).no==part2.equip.get(j).no)
						{
							sameequip.add(part1.equip.get(i));
							count++;
						}
					}
				}
				if(count==0)
				{
					int time=9999;	
					int e1=0;
					int e2=0;
					for(int i=0;i<part1.equip.size();i++)
					{
						for(int j=0;j<part2.equip.size();j++)
						{
							int time1=calculateTime(part1.equip.get(i),part2.equip.get(j));
							if(time>time1)
							{
								e1=i;e2=j;
								time=time1;
							}
						}
					}
					//选定的设备分别为 equip1[e1],equip2[e2]
					equipment equip1=part1.equip.elementAt(e1);
					int i=equip1.num.elementAt(equip1.firstdate).elementAt(equip1.firsttime);
					equip1.num.elementAt(equip1.firstdate).setElementAt(i+1,equip1.firsttime);
					part_result.addElement(part1.Name+","+equip1.no+","+equip1.firstdate+","+equip1.firsttime+","+equip1.location+","+part1.condition+","+i);
					update(equip1);
					timeset(equip1);
					
					equipment equip2=part2.equip.elementAt(e2);
					int j=equip2.num.elementAt(equip2.firstdate).elementAt(equip2.firsttime);
					equip2.num.elementAt(equip2.firstdate).set(equip2.firsttime,j+1);
					part_result.addElement(part2.Name+","+equip2.no+","+equip2.firstdate+","+equip2.firsttime+","+equip2.location+","+part2.condition+","+j);
					update(equip2);
					timeset(equip2);
				}
				else
				{
					//找到时间最早的设备
					equipment fe=findfirst2(sameequip);
					int i=fe.num.elementAt(fe.firstdate).elementAt(fe.firsttime);
					fe.num.elementAt(fe.firstdate).set(fe.firsttime,i+2);
					part_result.addElement(part1.Name+","+fe.no+","+fe.firstdate+","+fe.firsttime+","+fe.location+","+part1.condition+","+i);
					part_result.addElement(part2.Name+","+fe.no+","+fe.firstdate+","+fe.firsttime+","+fe.location+","+part2.condition+","+i);
					update(fe);
					timeset(fe);
				}
			}
			else if(part1.priority>part2.priority)
			{
				equipment equip1=findfirst(part1.equip);
				int i=equip1.num.elementAt(equip1.firstdate).elementAt(equip1.firsttime);
				equip1.num.elementAt(equip1.firstdate).set(equip1.firsttime,i+1);
				part_result.addElement(part1.Name+","+equip1.no+","+equip1.firstdate+","+equip1.firsttime+","+equip1.location+","+part1.condition+","+i);
				update(equip1);
				timeset(equip1);
				
				equipment equip2=findtime(equip1,part2.equip);
				i=equip2.num.elementAt(equip2.firstdate).elementAt(equip2.firsttime);
				equip2.num.elementAt(equip2.firstdate).set(equip2.firsttime,i+1);
				part_result.addElement(part2.Name+","+equip2.no+","+equip2.firstdate+","+equip2.firsttime+","+equip2.location+","+part2.condition+","+i);
				update(equip2);
				timeset(equip2);
			}
			else
			{
				equipment equip1=findfirst(part2.equip);
				int i=equip1.num.elementAt(equip1.firstdate).elementAt(equip1.firsttime);
				equip1.num.elementAt(equip1.firstdate).set(equip1.firsttime,i+1);
				part_result.addElement(part2.Name+","+equip1.no+","+equip1.firstdate+","+equip1.firsttime+","+equip1.location+","+part2.condition+","+i);
				update(equip1);
				timeset(equip1);
				
				equipment equip2=findtime(equip1,part1.equip);
				i=equip2.num.elementAt(equip2.firstdate).elementAt(equip2.firsttime);
				equip2.num.elementAt(equip2.firstdate).set(equip2.firsttime,i+1);
				part_result.addElement(part1.Name+","+equip2.no+","+equip2.firstdate+","+equip2.firsttime+","+equip2.location+","+part1.condition+","+i);
				update(equip2);
				timeset(equip2);
			}
		}
	}
	
	deal(String data,Vector<equipment> equips){//客户端传入的数据
		this.equips=equips;
		getepartinfo();
		String datas[]=data.split(" ");
		pa=new patient(datas[0]);
		Vector<part> part=new Vector<part>();
		createpart(datas,part);
		pa.part=part;
		getepatientinfo(datas[0],pa);
		if(pa.flag==0)
		{
			part_result.add("Error");
			return;
		}
		//在客户端上传之前先检查是否为空  警 
		if(datas.length>3)//三项及以上
		{
			Vector<part> part1=new Vector<part>();
			Vector<part> part2=new Vector<part>();
			for(int i=0;i<part.size();i++)
			{
				if(part.get(i).priority==1)
					part1.add(part.get(i));
				else
					part2.add(part.get(i));
			}
			if(part1.size()>2)
			{
				Vector<part> part3=new Vector<part>();
				while(part1.size()>2)
				{
					part3.add(part1.elementAt(0));
					part3.add(part1.elementAt(1));
					part1.remove(0);
					part1.remove(0);
					yuyue(part3,pa,part_result);
					part3.removeAllElements();
				}
				yuyue(part1,pa,part_result);
			}
			else
				yuyue(part1,pa,part_result);
			if(part2.size()>2)
			{
				for(int i=0;i<part2.size();i++)
				{
					for(int j=0;j<part2.elementAt(i).equip.size();j++)
					{
						if(fd>part2.elementAt(i).equip.elementAt(j).firstdate)
						{
							part2.elementAt(i).equip.elementAt(j).firstdate=fd;
							part2.elementAt(i).equip.elementAt(j).firsttime=ft;
						}
						if(fd==part2.elementAt(i).equip.elementAt(j).firstdate)
						{
							if(ft>part2.elementAt(i).equip.elementAt(j).firsttime)
								part2.elementAt(i).equip.elementAt(j).firsttime=ft;
							else if(ft==part2.elementAt(i).equip.elementAt(j).firsttime)
							{
								if(ft==0)
									part2.elementAt(i).equip.elementAt(j).firsttime=1;
								else
								{
									part2.elementAt(i).equip.elementAt(j).firstdate++;
									part2.elementAt(i).equip.elementAt(j).firsttime=0;
									if(part2.elementAt(i).equip.elementAt(j).num.size()<part2.elementAt(i).equip.elementAt(j).firstdate+1)
									{
										Vector<Integer> number=new Vector<Integer>();
										number.addElement(0);
										number.addElement(0);
										part2.elementAt(i).equip.elementAt(j).num.addElement(number);
									}
								}
							}
						}
					}
				}
				Vector<part> part3=new Vector<part>();
				while(part2.size()>2)
				{
					part3.add(part2.elementAt(0));
					part3.add(part2.elementAt(1));
					part2.remove(0);
					part2.remove(0);
					yuyue(part3,pa,part_result);
					part3.removeAllElements();
				}
				yuyue(part2,pa,part_result);
			}
			else
				yuyue(part2,pa,part_result);
		}
		else 
			yuyue(part,pa,part_result);
		timereset(part);
	}
	
	Vector<String> get_result(){
		return part_result;
	}
}
