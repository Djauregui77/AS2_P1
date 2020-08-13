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

import gt.com.controlestudiantes.model.Student;
import gt.com.controlestudiantes.service.serviceimpl.StudentService;

public abstract class ExportsStudents {
	public static void toJson(HttpServletResponse response,String[] headers_array,StudentService studentService){
        String filename = "estudiantes.json";

        response.setContentType("text/json");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        List<Map<String,String>> temp=new ArrayList<>();

        for (Student item:studentService.getAll()){
            Map<String,String> newMap=new HashMap<>();
            newMap.put("student_id",item.getStudent_id());
            newMap.put("name",item.getName());
            newMap.put("surname",item.getSurname());
            newMap.put("phone1",item.getPhone1());
            newMap.put("phone2",item.getPhone2());
            newMap.put("address1",item.getAddress1());
            newMap.put("address2",item.getAddress2());
            newMap.put("email",item.getEmail());
            newMap.put("birthdate",item.getBirthdate().toString());

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
    public static void toCsv(HttpServletResponse response,String[] headers_array,StudentService studentService){
        String filename = "estudiantes.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        try {

            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                    CsvPreference.STANDARD_PREFERENCE);

            csvWriter.writeHeader(headers_array);

            for (Student student : studentService.getAll()) {
                csvWriter.write(student, headers_array);
            }
            csvWriter.close();


        } catch (IOException ex) {

            System.out.println(ex);
        }
    }
    public static void toXml(HttpServletResponse response,String[] headers_array,StudentService studentService){
        String filename = "estudiantes.xml";

        response.setContentType("text/xml");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        List<Map<String,String>> temp=new ArrayList<>();

        for (Student item:studentService.getAll()){
            Map<String,String> newMap=new HashMap<>();
            newMap.put("student_id",item.getStudent_id());
            newMap.put("name",item.getName());
            newMap.put("surname",item.getSurname());
            newMap.put("phone1",item.getPhone1());
            newMap.put("phone2",item.getPhone2());
            newMap.put("address1",item.getAddress1());
            newMap.put("address2",item.getAddress2());
            newMap.put("email",item.getEmail());
            newMap.put("birthdate",item.getBirthdate().toString());

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
            //List<Student> t=studentService.getAll();
            //temp.get(0).setStudent_id(null);
            PrintWriter out=response.getWriter();

            out.write(mapper.writeValueAsString(filteredList));
            //System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
