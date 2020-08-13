package gt.com.controlestudiantes.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gt.com.controlestudiantes.model.Student;
import gt.com.controlestudiantes.service.serviceimpl.StudentService;
import gt.com.controlestudiantes.utils.ExportsStudents;

@Controller
@RequestMapping("/estudiantes")
public class StudentController {
	@Autowired
    StudentService studentService;

    @GetMapping("")
    public String index(Model model){
        /*retornar estudiantes*/
        model.addAttribute("students",studentService.getAll());

        return "student";
    }

    @PostMapping("/nuevo")
    public String addStudent(@RequestBody Student student, Model model){
        studentService.add(student);

        return "your_Action";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteStudent(@RequestParam String id){
        studentService.delete(id);
        return "your_Action";
    }

    @PutMapping("/{id}")
    public String updateStudent(@RequestParam String id, @RequestBody Student student){
        studentService.update(student);

        return "your_Action";
    }

    @PostMapping(value = "/exportar")
    public void getExport(@RequestParam String headers,@RequestParam String type, HttpServletResponse response){
        String[] headers_array=headers.split(",");

        switch (type){
            case "json":
                ExportsStudents.toJson(response,headers_array,studentService);
                break;
            case "csv":
            	ExportsStudents.toCsv(response,headers_array,studentService);
                break;
            case "xml":
            	ExportsStudents.toXml(response,headers_array,studentService);
                break;
        }
    }
}
