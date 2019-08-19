package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    @Autowired
    private DateUtil dateUtil;
    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int search_id) {
        int index = Student.studentList.indexOf(new Student(search_id,""));
        if(index == -1)
            return new ResponseEntity<>(new CustomErrorType("Student Not Found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student) {
        if(Student.studentList.remove(student))
            return new ResponseEntity<>( HttpStatus.OK);
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        Student.studentList.remove(student);
        Student.studentList.add(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
