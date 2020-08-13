package gt.com.controlestudiantes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gt.com.controlestudiantes.model.Student;

public interface IStudentDAO extends JpaRepository<Student,String> {
	@Query(value="select * from t2_student limit 10", nativeQuery = true)
    public List<Student> getAllLimit();
}
