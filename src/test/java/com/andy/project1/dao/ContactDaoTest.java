package com.andy.project1.dao;

import com.andy.project1.dao.rowmapper.ContactRowMapper;
import com.andy.project1.domain.Contact;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ContactDaoTest {
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ContactRowMapper contactRowMapper;

    private Contact contact1;
    private Contact contact2;

    private KeyHolder keyHolder1;
    private KeyHolder keyHolder2;

    @BeforeEach
    public void setup(){
        keyHolder1 = new GeneratedKeyHolder();
        keyHolder2 = new GeneratedKeyHolder();
        String sql = "INSERT INTO Contact (subject, message, email, time) VALUES (?, ?, ?, ?)";
        contact1 = new Contact();
        contact1.setSubject("HH");
        contact1.setMessage("Message1");
        contact1.setEmail("email1@gmail.com");
        contact1.setTime(Timestamp.from(Instant.now()));
        contact2 = new Contact();
        contact2.setSubject("Sub2");
        contact2.setMessage("Message2");
        contact2.setEmail("email2@gmail.com");
        contact2.setTime(Timestamp.from(Instant.now()));
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, contact1.getSubject());
                    ps.setString(2, contact1.getMessage());
                    ps.setString(3, contact1.getEmail());
                    ps.setTimestamp(4, contact1.getTime());
                    return ps;
                }, keyHolder1
        );
        contact1.setContact_id(keyHolder1.getKey().intValue());
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, contact2.getSubject());
                    ps.setString(2, contact2.getMessage());
                    ps.setString(3, contact2.getEmail());
                    ps.setTimestamp(4, contact2.getTime());
                    return ps;
                }, keyHolder2
        );
        contact2.setContact_id(keyHolder2.getKey().intValue());
    }

    @Test
    public void getContactByIdTest(){
        Contact contact = contactDao.getContactById(contact1.getContact_id());
        assertThat(contact).isNotNull();
        assertThat(contact.getContact_id()).isEqualTo(contact1.getContact_id());
        assertThat(contact.getSubject()).isEqualTo(contact1.getSubject());
        assertThat(contact.getMessage()).isEqualTo(contact1.getMessage());
        assertThat(contact.getEmail()).isEqualTo(contact1.getEmail());
        assertThat(contact.getTime()).isEqualTo(contact1.getTime());
    }

    @Test
    public void getAllContactTest(){
        List<Contact> contacts = contactDao.getAllContact();
        assertThat(contacts).isNotNull();
        assertThat(contacts.size()).isEqualTo(2);
        assertThat(contacts).extracting(Contact::getContact_id).containsExactlyInAnyOrder(
                contact1.getContact_id(), contact2.getContact_id()
        );
        assertThat(contacts).extracting(Contact::getSubject).containsExactlyInAnyOrder(
                contact1.getSubject(), contact2.getSubject()
        );
        assertThat(contacts).extracting(Contact::getMessage).containsExactlyInAnyOrder(
                contact1.getMessage(), contact2.getMessage()
        );
        assertThat(contacts).extracting(Contact::getEmail).containsExactlyInAnyOrder(
                contact1.getEmail(), contact2.getEmail()
        );
        assertThat(contacts).extracting(Contact::getTime).containsExactlyInAnyOrder(
                contact1.getTime(), contact2.getTime()
        );
    }

    @Test
    public void addContactTest(){
        Contact contact = new Contact();
        contact.setSubject("addSub");
        contact.setMessage("addMsg");
        contact.setEmail("add@gmail.com");
        contact.setTime(Timestamp.from(Instant.now()));
        int res = contactDao.addContact(contact);
        assertThat(res).isEqualTo(1);
        List<Contact> contacts = jdbcTemplate.query("SELECT * FROM Contact", contactRowMapper);
        assertThat(contacts.size()).isEqualTo(3);
        assertThat(contacts).extracting(Contact::getSubject).containsExactlyInAnyOrder(
                contact1.getSubject(), contact2.getSubject(), contact.getSubject()
        );
        assertThat(contacts).extracting(Contact::getMessage).containsExactlyInAnyOrder(
                contact1.getMessage(), contact2.getMessage(), contact.getMessage()
        );
        assertThat(contacts).extracting(Contact::getEmail).containsExactlyInAnyOrder(
                contact1.getEmail(), contact2.getEmail(), contact.getEmail()
        );
        assertThat(contacts).extracting(Contact::getTime).containsExactlyInAnyOrder(
                contact1.getTime(), contact2.getTime(), contact.getTime()
        );
    }

    @AfterEach
    public void after(){
        String sql = "DELETE FROM Contact where contact_id = ?";
        jdbcTemplate.update(sql, keyHolder1.getKey());
        jdbcTemplate.update(sql, keyHolder2.getKey());
    }
}
