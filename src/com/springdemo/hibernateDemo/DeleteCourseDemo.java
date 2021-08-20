package com.springdemo.hibernateDemo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernate.entity.Course;
import com.springdemo.hibernate.entity.Instructor;
import com.springdemo.hibernate.entity.InstructorDetail;
import com.springdemo.hibernate.entity.Student;

public class DeleteCourseDemo {

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
	
			//get the course from db
			int theId = 10;
			Course tempCourse = session.get(Course.class,theId);
			
			//delete course
			session.delete(tempCourse);
		
			
			
		
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			}finally {
				session.close();
				factory.close();
			
			}
		}

		
	
}
