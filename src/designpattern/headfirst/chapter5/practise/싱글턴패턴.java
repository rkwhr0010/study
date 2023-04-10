package designpattern.headfirst.chapter5.practise;

public class 싱글턴패턴 {
	public enum 싱글턴 {
		EnumSingleton;
		
		private 싱글턴() {
			System.out.println(cnt++);
		}
		int cnt = 1;
		String data;

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}
	}
	public static void main(String[] args) {
		싱글턴 d = 싱글턴.EnumSingleton;
		싱글턴 d2 = 싱글턴.EnumSingleton;
		싱글턴 d3 = 싱글턴.EnumSingleton;
		싱글턴 d4 = 싱글턴.EnumSingleton;
		
		System.out.println(d.equals(d2));
		System.out.println(d==d2);
		System.out.println(d==d3);
		
		for(싱글턴 싱글턴 : 싱글턴.values()) {
			System.out.println(싱글턴);
		}
		System.out.println(싱글턴.valueOf("EnumSingleton"));
		
	}

}
