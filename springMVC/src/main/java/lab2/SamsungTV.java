package lab2;

public class SamsungTV implements TV {

	public SamsungTV() {
		System.out.println("SamsungTV������");
		
	}
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName() + "������ �Ҵ�.");
	}
	
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName() + "������ ����.");
	}
}
