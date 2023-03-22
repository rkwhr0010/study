package designpattern.etc.callback;

import java.util.Optional;

public abstract class Task {
	final void executeWith(CallBack callBack) {
		execute();
		//콜백함수
		Optional.ofNullable(callBack).ifPresent(CallBack::call);
	}
	public abstract void execute();
}
