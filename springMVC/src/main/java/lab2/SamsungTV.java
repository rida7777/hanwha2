package lab2;

public class SamsungTV implements TV {

	public SamsungTV() {
		System.out.println("SamsungTV생성자");
		
	}
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName() + "전원을 켠다.");
	}
	
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName() + "전원을 끈다.");
	}
}
