package gt.com.controlestudiantes.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import gt.com.controlestudiantes.model.Course;
import gt.com.controlestudiantes.service.serviceimpl.CourseService;

public abstract class ExportsCourses {
	public static void toJson(HttpServletResponse response,String[] headers_array,CourseService courseService){
        String filename = "cursos.json";

        response.setContentType("text/json");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        List<Map<String,String>> temp=new ArrayList<>();

        for (Course item:courseService.getAll()){
            Map<String,String> newMap=new HashMap<>();
            newMap.put("student_id",item.getId_course());
            newMap.put("name",item.getName());
            newMap.put("surname",item.getDescription());
            temp.add(newMap);
        }

        List<Map<String,String>> filteredList=new ArrayList<>();
        for (Map<String,String> item:
                temp) {
            //System.out.println(item);
            Map<String,String> filteredMap=new HashMap<>();
            for (String key:item.keySet()){
                System.out.println(Arrays.asList(headers_array).contains(key));
                if(Arrays.asList(headers_array).contains(key)){
                    filteredMap.put(key,item.get(key));

                }
            }
            filteredList.add(filteredMap);
        }

        String jsonString=new Gson().toJson(filteredList);


        try {
            PrintWriter out=response.getWriter();
            out.write(jsonString);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void toCsv(HttpServletResponse response,String[] headers_array,CourseService courseService){
        String filename = "cursos.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        try {

            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                    CsvPreference.STANDARD_PREFERENCE);

            csvWriter.writeHeader(headers_array);

            for (Course course : courseService.getAll()) {
                csvWriter.write(course, headers_array);
            }
            csvWriter.close();


        } catch (IOException ex) {

            System.out.println(ex);
        }
    }
    public static void toXml(HttpServletResponse response,String[] headers_array,CourseService courseService){
        String filename = "cursos.xml";

        response.setContentType("text/xml");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        List<Map<String,String>> temp=new ArrayList<>();

        for (Course item:courseService.getAll()){
            Map<String,String> newMap=new HashMap<>();
            newMap.put("student_id",item.getId_course());
            newMap.put("name",item.getName());
            newMap.put("surname",item.getDescription());
            temp.add(newMap);
        }

        List<Map<String,String>> filteredList=new ArrayList<>();
        for (Map<String,String> item:
                temp) {
            //System.out.println(item);
            Map<String,String> filteredMap=new HashMap<>();
            for (String key:item.keySet()){
                System.out.println(Arrays.asList(headers_array).contains(key));
                if(Arrays.asList(headers_array).contains(key)){
                    filteredMap.put(key,item.get(key));

                }
            }
            filteredList.add(filteredMap);
        }

        XmlMapper mapper = new XmlMapper();

        try {
            PrintWriter out=response.getWriter();
            out.write(mapper.writeValueAsString(filteredList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
