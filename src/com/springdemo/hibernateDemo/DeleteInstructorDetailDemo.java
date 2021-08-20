package com.springdemo.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernate.entity.Instructor;
import com.springdemo.hibernate.entity.InstructorDetail;
import com.springdemo.hibernate.entity.Student;

public class DeleteInstructorDetailDemo {

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
			
			
			
		    //start trans action
			session.beginTransaction();
			//get the instructor detail object
			int theId =5;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			//print the instructor detail
			System.out.println("tempInstructorDetail : " + tempInstructorDetail);
			// print the associated instructor 
			System.out.println("the associated instructor : "+ tempInstructorDetail.getInstructor());
			 
			//now let's instructor detail 
			System.out.println("Deleting InstructorDetail : " + tempInstructorDetail);
		
			// remove the associated object reference (fori key in the instructor table)
			// break bi-direction
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
		
			//commit transaction
			session.getTransaction().commit();  
			
			System.out.println("Done!");
			}catch(Exception ex){
				ex.printStackTrace();
			}finally {
				session.close(); 
				factory.close();
			
			}
		}

		
	
}
