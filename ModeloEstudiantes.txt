package gt.com.controlestudiantes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t2_student")
public class Student {
	  @Id
	    private String student_id;
	    @Column(name = "surname",nullable = false)
	    private String surname;
	    @Column(name = "name",nullable = false)
	    private String name;
	    @Column(name = "phone2",nullable = false)
	    private String phone2;
	    @Column(name = "address2",nullable = false)
	    private String address2;
	    @Column(name = "phone1",nullable = false)
	    private String phone1;
	    @Column(name = "address1",nullable = false)
	    private String address1;
	    @Column(name = "email",nullable = false)
	    private String email;

	    @Column(name = "birthdate",nullable = false)
	    private Date birthdate;

	    public String getStudent_id() {
	        return student_id;
	    }

	    public void setStudent_id(String student_id) {
	        this.student_id = student_id;
	    }


	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getSurname() {
	        return surname;
	    }

	    public void setSurname(String surname) {
	        this.surname = surname;
	    }

	    public Date getBirthdate() {
	        return birthdate;
	    }

	    public void setBirthdate(Date birthdate) {
	        this.birthdate = birthdate;
	    }

	    public String getPhone2() {
	        return phone2;
	    }

	    public void setPhone2(String phone2) {
	        this.phone2 = phone2;
	    }

	    public String getAddress2() {
	        return address2;
	    }

	    public void setAddress2(String address2) {
	        this.address2 = address2;
	    }

	    public String getPhone1() {
	        return phone1;
	    }

	    public void setPhone1(String phone1) {
	        this.phone1 = phone1;
	    }

	    public String getAddress1() {
	        return address1;
	    }

	    public void setAddrees1(String address1) {
	        this.address1 = address1;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
}
