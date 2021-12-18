package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        validateEmailIsNotTakenOrThrowException(student.getEmail());
        return studentRepository.save(student);
    }

    public String deleteStudent(Long studentId) {
        findStudentOrThrowException(studentId);
        studentRepository.deleteById(studentId);
        return "student with id " + studentId + " has been deleted.";
    }

    @Transactional
    public Student updateStudent(Long studentId, String name, String email) {
        Student student = findStudentOrThrowException(studentId);
        if (name != null
                && name.length() > 0
                && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null
                && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) {
            validateEmailIsNotTakenOrThrowException(email);
            student.setEmail(email);
        }
        return student;
    }

    private void validateEmailIsNotTakenOrThrowException(String email){
        Optional<Student> existingStudentWithEmail = studentRepository
                .findStudentByEmail(email);
        if (existingStudentWithEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
    }

    private Student findStudentOrThrowException(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
    }
}
