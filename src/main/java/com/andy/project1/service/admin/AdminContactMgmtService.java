package com.andy.project1.service.admin;

import com.andy.project1.dao.ContactDao;
import com.andy.project1.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminContactMgmtService {
    private final ContactDao contactDao;

    @Autowired
    public AdminContactMgmtService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public List<Contact> getAllContacts(){
        return contactDao.getAllContact();
    }
}
