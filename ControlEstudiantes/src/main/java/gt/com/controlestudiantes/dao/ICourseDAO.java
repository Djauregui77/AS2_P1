package gt.com.controlestudiantes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gt.com.controlestudiantes.model.Course;

public interface ICourseDAO extends JpaRepository<Course,String> {
	@Query(value="select * from t2_course limit 10", nativeQuery = true)
    public List<Course> getAllLimit();
}
