package lab1;

public class LgTV implements TV {

	public void powerOn() {
		System.out.println(this.getClass().getSimpleName() + "������ �Ҵ�.**");
	}
	
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName() + "������ ����.**");
	}
}
