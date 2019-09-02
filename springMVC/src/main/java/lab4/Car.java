package lab4;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

//<bean id="car" class="lab4.Car" p:model="소나타"/>
@Component
public class Car {
	String model;
	int price;
	
	public Car() {
		
	}
	
	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
	}

	
	
	public String getModel() {
		return model;
	}

	//@Required //모델은 반드시 세팅해야 한다는 의미
	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "자동차 정보 Car [model=" + model + ", price=" + price + "]";
	}
}
