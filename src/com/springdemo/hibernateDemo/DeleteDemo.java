package com.springdemo.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernate.entity.Instructor;
import com.springdemo.hibernate.entity.InstructorDetail;
import com.springdemo.hibernate.entity.Student;

public class DeleteDemo {

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
			
		
		    //start transaction
			session.beginTransaction();
			// get instructor by primary ky / id 
			int theId = 1;
			Instructor temInstructor = session.get(Instructor.class, theId);
			System.out.println("Found instructor : "+ temInstructor);
			//delete the instructors
			if(temInstructor!=null) {
				System.out.println("Deleting : "+ temInstructor);
				//Note will Also Delete Associate "detail object
				//because of cascadeType.All
				session.delete(temInstructor);
			}
			//commit transaction
			session.getTransaction().commit();
	
			System.out.println("Done!");
			}finally {
				factory.close();
			
			}
		}

		
	
}
