package lab2;



import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		//ApplicationContext ���Ǳ� ���� ���� �̸� �ε� --> �����ڵ��� �̸� �������!
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lab2.xml");
		
		/* BeanFactory ���� ����� �� ���� : ����� ȯ�濡�� ��� (�޷θ��� ȿ�������� ����� �� ����)
		  Resource resource =  new ClassPathResource("lab2.xml"); 
		  BeanFactory factory = new XmlBeanFactory(resource); 
		*/
		
		//TV tv = (TV) context.getBean("ltv");
		TV tv = context.getBean("ltv", TV.class);
		tv.powerOn();
		tv.powerOff();
	}

}
