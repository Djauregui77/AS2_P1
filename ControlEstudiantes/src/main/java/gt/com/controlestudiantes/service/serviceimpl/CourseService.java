package gt.com.controlestudiantes.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.controlestudiantes.dao.ICourseDAO;
import gt.com.controlestudiantes.model.Course;
import gt.com.controlestudiantes.services.ICourse;

@Service
public class CourseService implements ICourse {
	@Autowired
	private ICourseDAO courseDao;
	 @Override
	    public Course add(Course course) {
	        return courseDao.save(course);
	    }

	    @Override
	    public Course update(Course course) {
	        return courseDao.save(course);
	    }

	    @Override
	    public void delete(String id) {
	    	courseDao.deleteById(id);
	    }

	    @Override
	    public Optional<Course> find(String id) {
	        return courseDao.findById(id);
	    }

	    @Override
	    public List<Course> getAll() {
	        //return studentDAO.findAll();
	        return courseDao.getAllLimit();
	    }
}
