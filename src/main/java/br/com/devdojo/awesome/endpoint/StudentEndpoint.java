package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    @Autowired
    private DateUtil dateUtil;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, path="/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int search_id) {
        for(int i = 0;i<Student.studentList.size();i++){
            System.out.println(i+" / "+Student.studentList.get(i).getName()+" / "+Student.studentList.get(i).getPk_id());
        }
        int index = Student.studentList.indexOf(new Student(search_id,""));
        if(index == -1)
            return new ResponseEntity<>(new CustomErrorType("Student Not Found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
    }
}
