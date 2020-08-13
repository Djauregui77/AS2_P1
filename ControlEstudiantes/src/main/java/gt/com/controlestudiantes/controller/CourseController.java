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

import gt.com.controlestudiantes.model.Course;
import gt.com.controlestudiantes.service.serviceimpl.CourseService;
import gt.com.controlestudiantes.utils.ExportsCourses;

@Controller
@RequestMapping("/cursos")
public class CourseController {
	@Autowired
    CourseService courseService;

    @GetMapping("")
    public String index(Model model){
        /*retornar estudiantes*/
        model.addAttribute("courses",courseService.getAll());

        return "course";
    }

    @PostMapping("/nuevo")
    public String addStudent(@RequestBody Course course, Model model){
    	courseService.add(course);

        return "your_Action";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteStudent(@RequestParam String id){
    	courseService.delete(id);
        return "your_Action";
    }

    @PutMapping("/{id}")
    public String updateStudent(@RequestParam String id, @RequestBody Course course){
    	courseService.update(course);

        return "your_Action";
    }

    @PostMapping(value = "/exportar")
    public void getExport(@RequestParam String headers,@RequestParam String type, HttpServletResponse response){
        String[] headers_array=headers.split(",");

        switch (type){
            case "json":
                ExportsCourses.toJson(response,headers_array,courseService);
                break;
            case "csv":
            	ExportsCourses.toCsv(response,headers_array,courseService);
                break;
            case "xml":
            	ExportsCourses.toXml(response,headers_array,courseService);
                break;
        }
    }
}
