package springbootdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping(path = "/delete/student/{studid}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studid") int id){
        studentRepo.deleteById(id);
        return ResponseEntity.ok("Student Deleted " + id);
    }

    @PostMapping(path = "/update/student/{studid}")
    public ResponseEntity<String> updateStudent(@PathVariable("studid") int id, @RequestBody Student stDTO) {
        Optional<Student> student = studentRepo.findById(id);
        if(student.isEmpty())
            return ResponseEntity.notFound().build();

        Student updateStudent = student.get();

        if(stDTO.getFirstName() != null)
            updateStudent.setFirstName(stDTO.getFirstName());
        if(stDTO.getLastName() != null)
            updateStudent.setLastName(stDTO.getLastName());
        if(stDTO.getAge() != 0)
            updateStudent.setAge(stDTO.getAge());

        studentRepo.save(updateStudent);
        return ResponseEntity.ok("Updated Student " + id);
    }
}