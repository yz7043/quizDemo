package com.andy.project1.service.contact;

import com.andy.project1.dao.ContactDao;
import com.andy.project1.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactDao contactDao;

    @Autowired
    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public boolean addContact(Contact contact){
        int res = contactDao.addContact(contact);
        return res > 0;
    }

    public Contact getContactById(Integer id){
        return contactDao.getContactById(id);
    }
}
