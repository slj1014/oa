package oa.utils;


import org.hibernate.cfg.Configuration;
import org.junit.Test;
/**
 * ������
 * @author Administrator
 *
 */
public class CreateTable {
	@Test
public void crateTeble(){
	Configuration configuration=new Configuration().configure("/hibernate.cfg.xml");//���������ļ�
	configuration.buildSessionFactory();
}
}
