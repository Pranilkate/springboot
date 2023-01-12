package com.hibernate.app.main;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.app.model.College;
import com.hibernate.app.model.Employee;
import com.hibernate.app.model.Student;

public class App {
	public static void main(String[] args) {
		/*
		 * Step 1: Reach out to Persistence.xml [mydb] Step 2: Create EntityManager:
		 * this helps me to create tables using classes Step 3: Initiate transaction
		 */

		try {
//			Step-1
			EntityManagerFactory entityAManageFactory = Persistence.createEntityManagerFactory("hibernate");
//			Step-2
			EntityManager entityManager = entityAManageFactory.createEntityManager();

//			Step-3
			EntityTransaction entityTransaction = entityManager.getTransaction();
			
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("-------Hibernate Operations-------");
				System.out.println("1. Insert Employee Record");
				System.out.println("2. Display all Employees");
				System.out.println("3. Delete an Employee");
				System.out.println("4. Update Employee Record");
				System.out.println("5. Insert Student Record");
				System.out.println("6. Add College Record");
				System.out.println("7. Fetch Students by College");
				System.out.println("0. Exit");
				int input = scanner.nextInt();
				if (input == 0) {
					System.out.println("Exiting....Bye...");
					break;
				}
				switch (input) {
				case 1:
					/*Read input from user*/
					System.out.println("Insert Employee OP");
					System.out.println("Enter Employee Name: ");
					scanner.nextLine();
					String name=scanner.nextLine();
					System.out.println("Enter Email ");
					String email=scanner.next();
					System.out.println("Enter Contact ");
					String contact=scanner.next();
					/*Attach all input to Employee Object*/
					Employee employee=new Employee();//POJO:Plain Old Java Object
					employee.setName(name);
					employee.setEmail(email);
					employee.setContact(contact);
					entityTransaction.begin();
					entityManager.persist(employee);
					entityTransaction.commit();
					System.out.println("Employee added to the DB...");
					break;
				case 2:
					System.out.println("Employee Records");
					entityTransaction.begin();
					List<Employee> list=entityManager.createQuery("select e from Employee e",Employee.class).getResultList();
					list.stream().forEach(System.out::println);
					entityTransaction.commit();
					break;
				case 3:
					System.out.println("Employee Deletion");
					System.out.println("Enter employee ID to delete record");
					int id=scanner.nextInt();
					entityTransaction.begin();
					Employee emp=entityManager.find(Employee.class, id);
					if(emp==null) {
						System.out.println("Employee with ID " +id+" does not exist");
					}
					else {
						entityManager.remove(emp);
						System.out.println("Employee with ID "+id+" deleted from the DB");
					}
					entityTransaction.commit();
					break;
				case 4:
					System.out.println("Employee Updation");
					System.out.println("Enter Employee ID to update record");
					id=scanner.nextInt();
					entityTransaction.begin();
					emp=entityManager.find(Employee.class, id);
					if(emp==null) {
						System.out.println("Employee with ID " +id+" does not exist");
					}
					else {
						System.out.println("Employee Details\n"+emp);
						System.out.println("Enter new values");
						System.out.println("Enter Employee Name: ");
						scanner.nextLine();
						name=scanner.nextLine();
						System.out.println("Enter Email ");
						email=scanner.next();
						System.out.println("Enter Contact ");
						contact=scanner.next();
						emp.setName(name);
						emp.setEmail(email);
						emp.setContact(contact);
						entityManager.persist(emp);
						System.out.println("Employee record updated in DB...");
					}
					entityTransaction.commit();
					
					break;
				case 5:
					System.out.println("Enter Student Data");
					System.out.println("Enter Student Name");
					scanner.nextLine();
					name=scanner.nextLine();
					System.out.println("Enter Student email");
					email=scanner.next();
					System.out.println("Enter COllege ID that Student belongs to");
					int cid=scanner.nextInt();
					entityTransaction.begin();
					College college=entityManager.find(College.class, cid);
					if(college==null) {
						System.out.println("College ID Invalid");
						break;
					}
					else{
						Student student=new Student();
						student.setName(name);
						student.setEmail(email);
						student.setCollege(college);
						entityManager.persist(student);
						System.out.println("Student Record Added");
					}
					entityTransaction.commit();
					break;
				case 6:
					System.out.println("Enter College Data");
					System.out.println("Enter College Name");
					scanner.nextLine();
					name=scanner.nextLine();
					System.out.println("Enter College City");
					String city=scanner.next();
					College c=new College();
					c.setName(name);
					c.setCity(city);
					entityTransaction.begin();
					entityManager.persist(c);
					entityTransaction.commit();
					System.out.println("College Record Added in DB");
					break;
				case 7:
					System.out.println("Students Studying in College");
					System.out.println("Enter College ID");
					cid=scanner.nextInt();
					college=entityManager.find(College.class, cid);
					if(college==null) {
						System.out.println("Hey College ID is Invalid");
						break;
					}
					else{
						entityTransaction.begin();
						List<Student> slist=entityManager
								.createQuery("select s from Student s where s.college.id="+cid,Student.class)
						.getResultList();
						System.out.println("------------------------------------");
						slist.stream().forEach(System.out::println);
						entityTransaction.commit();
						System.out.println("---------------------------------");
					}
					break;
				default:
					System.out.println("Invalid Input");
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}