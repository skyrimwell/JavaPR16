package PR15.Application.service;

import PR15.Application.model.Phone;
import PR15.Application.model.Manufacture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {
    @Autowired
    private final SessionFactory sessionFactory;

    private Session session;

    public PhoneService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    public void addPhone(Phone phone) {
        session.beginTransaction();
        session.saveOrUpdate(phone);
        session.getTransaction().commit();
    }

    public void addManufacture(UUID id, Manufacture manufacture) {
        session.beginTransaction();

        Phone t = session.load(Phone.class, id);
        t.addManufacture(manufacture);
        session.saveOrUpdate(t);

        session.getTransaction().commit();
    }

    public void removeManufacture(UUID id, Manufacture manufacture) {
        session.beginTransaction();

        Phone t = session.load(Phone.class, id);
        t.removeManufacture(manufacture);
        session.saveOrUpdate(t);

        session.getTransaction().commit();
    }

    public List<Phone> getPhones() {
        return session.createQuery("select u from Phone u", Phone.class).list();
    }

    public Phone getPhone(UUID id) {
        return session.createQuery("select u from User u where u.id = p.id = '" + id + "'", Phone.class).getSingleResult();
    }

    public void deletePhone(UUID id) {
        session.beginTransaction();

        Phone t = session.load(Phone.class, id);
        session.delete(t);

        session.getTransaction().commit();
    }
}
