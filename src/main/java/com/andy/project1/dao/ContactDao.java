package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.ContactRowMapper;
import com.andy.project1.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDao {
    private final JdbcTemplate jdbcTemplate;
    private final ContactRowMapper contactRowMapper;
    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
    }

    public Contact getContactById(Integer id){
        String query = "SELECT * FROM Contact WHERE contact_id = ?";
        List<Contact> contacts = jdbcTemplate.query(query, contactRowMapper, id);
        return contacts.size() == 0 ? null : contacts.get(0);
    }

    public List<Contact> getAllContact(){
        String query = "SELECT * FROM Contact";
        return jdbcTemplate.query(query, contactRowMapper);
    }

    public int addContact(Contact contact){
        String query = "INSERT INTO Contact (subject, message, email, time) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                query,
                contact.getSubject(),
                contact.getMessage(),
                contact.getEmail(),
                contact.getTimestamp()
        );
    }
}
