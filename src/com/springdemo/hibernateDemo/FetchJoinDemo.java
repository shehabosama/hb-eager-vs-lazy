package com.springdemo.hibernateDemo;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernate.entity.Course;
import com.springdemo.hibernate.entity.Instructor;
import com.springdemo.hibernate.entity.InstructorDetail;
import com.springdemo.hibernate.entity.Student;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
				
	
		    //start trans action
			session.beginTransaction();
	
			//option 2: hibernate with query
			//get the instructor from db
			int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId",Instructor.class);
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			//execute query and get instructor
			Instructor tempInstructor=query.getSingleResult();
			
			System.out.println("Debugging : Instructor : "+tempInstructor);
	
			
		
			//commit transaction
			session.getTransaction().commit();
			session.close();
			System.out.println("the session is now closed");
			//get courses for the instructor with out impacting with the session life cycle
			
			System.out.println( "instructor courses : " + tempInstructor.getCourses());
			System.out.println("Debugging :Done!");
			}finally {
				
				factory.close();
			
			}
		}

		
	
}
