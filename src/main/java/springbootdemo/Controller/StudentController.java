package springbootdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootdemo.Repositories.StudentRepository;
import springbootdemo.models.Student;


import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepo;
    @GetMapping(path = "/students")
    public List<Student> getStudents(){
        return studentRepo.findAll();
    }
    @GetMapping(path = "/student/{studentId}")
    public Optional<Student> getStudent(@PathVariable("studentId") int studentId){
        return studentRepo.findById(studentId);
    }
    @GetMapping(path = "/student/age/{age}")
    public Optional<List<Student>> getStudentsByAge(@PathVariable("age") int age){
        return studentRepo.findByAge(age);
    }
    @PostMapping("/student")
    public Student addStudent(Student s){
        return studentRepo.save(s);
    }
}