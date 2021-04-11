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
public class ManufactureService {
    @Autowired
    private final SessionFactory sessionFactory;

    private Session session;

    public ManufactureService(SessionFactory sessionFactory) {
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

    public void addManufacture(Manufacture manufacture) {
        session.beginTransaction();
        session.saveOrUpdate(manufacture);
        session.getTransaction().commit();
    }

    public List<Manufacture> getManufactures() {
        return session.createQuery("select u from Manufacture u", Manufacture.class).list();
    }

    public Manufacture getManufacture(UUID id) {
        return session.createQuery("select p from Manufacture u where u.id = p.id = '" + id + "'", Manufacture.class).getSingleResult();
    }

    public void deleteManufactures(Manufacture manufacture) {
        session.beginTransaction();

        List<Manufacture> query = session.createQuery("select p from Manufacture p where p.id = '" + manufacture.getId() + "'", Manufacture.class).list();
        for (Manufacture p : query) {
            session.delete(p);
        }

        session.getTransaction().commit();
    }

    public void deleteManufacture(UUID id) {
        session.beginTransaction();

        Manufacture t = session.load(Manufacture.class, id);
        session.delete(t);

        session.getTransaction().commit();
    }
}
