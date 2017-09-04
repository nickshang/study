package desginpatterns.command;

/**
 * ¹¦ÄÜÃèÊö£º²âÊÔ
 * @author Think
 *
 */
public class Test {
	public static void main(String[] args) {
		Light light = new Light();
		Command command = new LightOnCommand(light);
		SimpleRemoteControl control = new SimpleRemoteControl();
		control.setCommand(command);
		control.bottonWasPressed();
	}
}
