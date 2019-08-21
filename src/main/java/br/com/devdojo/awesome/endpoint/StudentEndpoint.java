package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long pk_id) {
        Student student = studentDAO.findOne(pk_id);
        verifyIfStudentExists(pk_id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable("name") String name) {
        if (studentDAO.findByName(name) == null)
            throw new ResourceNotFoundException("Student not found for name: " + name);
        return new ResponseEntity<>(studentDAO.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    @Transactional()
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{pk_id}")
    public ResponseEntity<?> delete(@PathVariable Long pk_id) {
        verifyIfStudentExists(pk_id);
        studentDAO.delete(pk_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExists(student.getPk_id());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long pk_id) {
        if (studentDAO.findOne(pk_id) == null)
            throw new ResourceNotFoundException("Student not found for ID: " + pk_id);
    }
}
