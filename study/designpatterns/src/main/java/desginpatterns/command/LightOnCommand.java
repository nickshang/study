package desginpatterns.command;

/**
 * �����������ƴ�����
 * @author Think
 *
 */
public class LightOnCommand implements Command {

	private Light light;
	
	public LightOnCommand(Light light){
		this.light = light;
	}
	
	/**
	 * �򿪵�����
	 */
	@Override
	public void execute() {
		light.on();
	}

}
