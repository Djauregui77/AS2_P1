package gt.com.controlestudiantes.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.controlestudiantes.dao.IStudentDAO;
import gt.com.controlestudiantes.model.Student;
import gt.com.controlestudiantes.services.IStudent;

@Service
public class StudentService implements IStudent {
	@Autowired
    private IStudentDAO studentDAO;

    @Override
    public Student add(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public void delete(String id) {
        studentDAO.deleteById(id);
    }

    @Override
    public Optional<Student> find(String id) {
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> getAll() {
        //return studentDAO.findAll();
        return studentDAO.getAllLimit();
    }
}
