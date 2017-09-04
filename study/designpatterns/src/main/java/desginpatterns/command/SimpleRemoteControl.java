package desginpatterns.command;

/**
 * √Ë ˆ£∫ºÚµ•
 * @author NICK
 *
 */
public class SimpleRemoteControl {
	
	private Command command ;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void bottonWasPressed(){
		command.execute();
	}
}
