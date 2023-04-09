package designpattern.headfirst.chapter11;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiTest {
	static interface MyRemote extends Remote{
		String sayHello() throws RemoteException;
	}

	static class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{
		private static final long serialVersionUID = 4333092922255536781L;
		protected MyRemoteImpl() throws RemoteException {}

		public String sayHello() throws RemoteException {
			return "Hello World";
		}
	}
	
	public static void main(String[] args) {
		try {
			//서버에서 RMI레지스트리에 등록
			//주소 / 등록 이름 조합으로 NS룩업
			MyRemote service = new MyRemoteImpl();
			Naming.rebind("RemoteHello", service);
		}catch (Exception e) {
			
		}
	}
}
