package designpattern.headfirst.chapter14;

/*
 * 구현과 더불어 추상화 부분까지 변경해야 한다면 사용
 */
public class Bridge {
	//실제 추상 클래스도 변경 가능성이 높다. 
	static abstract class RemoteControl {
		//실제 구현은 구성 관계로 위임한다.
		TV tv;
		//이건 번외로 그냥 심플팩토리임
		TVFactory tvFactory;
		
		RemoteControl(TVFactory tvFactory) {
			this.tvFactory = tvFactory;
		}
		public void on() {
			//구현이 위임된 것을 알 수 있다.
			this.tv.on();
		}
		public void off() {
			this.tv.off();
		}
		public void setChannel(int channel) {
			tv.tuneChannel(channel);
		}
		public int getChannel() {
			return tv.getChannel();
		}
		public void setTV(String type) {
			try {
				tv = tvFactory.getTV(type);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	//이렇게 메서드가 추가되거나 삭제 수정 될 수 있다.
	static class GenericRemote extends RemoteControl {
		public GenericRemote(TVFactory tvFactory) {
			super(tvFactory);
		}
		
		//이 과정에서 이렇게 구현과 추상 클래스 계층 메서드 이름이 다르기도 하다.
		public void nextChannel() {
			int channel = this.getChannel();
			this.setChannel(channel+1);
		}
		public void prevChannel() {
			int channel = this.getChannel();
			this.setChannel(channel-1);
		}
	}
	static class SpecialRemote extends RemoteControl {
		public SpecialRemote(TVFactory tvFactory) {
			super(tvFactory);
		}
		public void up() {
			int channel = this.getChannel();
			this.setChannel(channel+1);
		}
		public void down() {
			int channel = this.getChannel();
			this.setChannel(channel-1);
		}
	}
	
	static abstract class TV {
		public abstract void on();
		public abstract void off();
		public abstract void tuneChannel(int channel);
		public abstract int getChannel(); 
	}
	static class LG extends TV {
		int channel = 1;
		public void on() {
			System.out.println("Turning on the LG TV");
		}
		public void off() {
			System.out.println("Turning off the LG TV");
		}
		public void tuneChannel(int channel) {
			this.channel = channel;
			System.out.println("Set the LG TV Channel to " + this.channel);
		}
		public int getChannel() {
			return channel;
		}
	}
	
	static class Sony extends TV {
		int station = 0;
		public void on() {
			System.out.println("Turning on the Sony TV");
		}
		public void off() {
			System.out.println("Turning off the Sony TV");
		}
		public void tuneChannel(int channel) {
			this.station = channel;
			System.out.println("Set the Sony TV station to " + this.station);
		}
		public int getChannel() {
			return station;
		}
	}
	
	static class TVFactory {
		public TV getTV(String type) throws Exception {
			if (type.equals("LG")) {
				return new LG();
			} else if (type.equals("Sony")) {
				return new Sony();
			} else {
				throw new Exception("Invalid TV Type");
			}
		}
	}
	
	public static void main(String[] args) {
		TVFactory tvFactory = new TVFactory();
		SpecialRemote remoteSony = new SpecialRemote(tvFactory);
		System.out.println("TV에 연결했습니다.");
		remoteSony.setTV("Sony");
		remoteSony.on();
		remoteSony.up();
		remoteSony.down();
		remoteSony.off();
		
		GenericRemote remoteLG = new GenericRemote(tvFactory);
		System.out.println("TV에 연결했습니다");
		remoteLG.setTV("LG");
		remoteLG.on();
		remoteLG.nextChannel();
		remoteLG.prevChannel();
		remoteLG.off();
	}
	
}
