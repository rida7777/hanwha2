package lab2;

public class LgTV implements TV {

	public LgTV() {
		System.out.println("LgTV생성자");
	}
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName() + "전원을 켠다.**");
	}
	
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName() + "전원을 끈다.**");
	}
}
