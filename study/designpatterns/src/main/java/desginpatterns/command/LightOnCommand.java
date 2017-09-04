package desginpatterns.command;

/**
 * 功能描述：灯打开命令
 * @author Think
 *
 */
public class LightOnCommand implements Command {

	private Light light;
	
	public LightOnCommand(Light light){
		this.light = light;
	}
	
	/**
	 * 打开灯命令
	 */
	@Override
	public void execute() {
		light.on();
	}

}
