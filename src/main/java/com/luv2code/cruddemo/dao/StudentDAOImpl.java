package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired  // optional when only one constructor is present
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement the save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // JPQL query
//        TypedQuery<Student> theQuery = entityManager.createQuery(
//                "SELECT s FROM Student s ORDER BY s.lastName", Student.class);
        // hibernate hql
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student ORDER BY lastName DESC", Student.class);  // ASC is default

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query with parameter
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);

        // set query parameter
        theQuery.setParameter("theData", theLastName);

        // return query results
        return theQuery.getResultList();
    }
}
