package com.springdemo.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernate.entity.Instructor;
import com.springdemo.hibernate.entity.InstructorDetail;
import com.springdemo.hibernate.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create the objects
			Instructor tempInstructor = new Instructor("shehab" , "osama" , "shehab@gmail.com");
			InstructorDetail temInstructorDetail = new InstructorDetail("http://www.google.com" , "shehab osama");
			//associate the objects
			tempInstructor.setInstructorDetail(temInstructorDetail);
		    //start trans action
			session.beginTransaction();
			
			//save the instructor
			// Note : this will Also save the details object because cascadeType.ALL
			System.out.println("Saving Instructor"+ tempInstructor);
			session.save(tempInstructor);
			//commit transaction
			session.getTransaction().commit();
			session.close();
			System.out.println("Done!");
			}finally {
				factory.close();
			
			}
		}

		
	
}
