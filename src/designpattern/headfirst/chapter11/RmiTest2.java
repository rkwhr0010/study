package designpattern.headfirst.chapter11;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import designpattern.headfirst.chapter11.RmiTest.MyRemote;

public class RmiTest2 {
	
	public static void main(String[] args) {
		try {
			//마치 팩토리에서 객체를 가져오듯...
			MyRemote myRemote = (MyRemote)Naming.lookup("rmi://127.0.0.1/RemoteHello");
			System.out.println(myRemote.sayHello());
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
