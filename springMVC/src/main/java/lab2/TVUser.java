package lab2;



import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		//ApplicationContext 사용되기 전에 빈을 미리 로딩 --> 생성자들이 미리 만들어짐!
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lab2.xml");
		
		/* BeanFactory 빈을 사용할 때 생성 : 모바일 환경에서 사용 (메로리를 효율적으로 사용할 수 있음)
		  Resource resource =  new ClassPathResource("lab2.xml"); 
		  BeanFactory factory = new XmlBeanFactory(resource); 
		*/
		
		//TV tv = (TV) context.getBean("ltv");
		TV tv = context.getBean("ltv", TV.class);
		tv.powerOn();
		tv.powerOff();
	}

}
