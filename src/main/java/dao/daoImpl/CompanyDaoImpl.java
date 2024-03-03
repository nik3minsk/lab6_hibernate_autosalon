package dao.daoImpl;


import entity.Person;
import sessionFactory.SessionFactoryImpl;
import dao.CompanyDao;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    public CompanyDaoImpl() {
    }

    @Override
    public boolean addCompany(Company company) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(company);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateCompany(Company company) {
        boolean isUpdated = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(company);
            transaction.commit();
            session.close();
            // Тут нужно обновление


            isUpdated = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCompany(int id) {
        boolean isDeleted = false;
        try {
//            Session session = SessionFactoryImpl.getSessionFactory().openSession();
//            Company company = session.load(Company.class, id);
//            Transaction tx = session.beginTransaction();
//            session.delete(company);
//            tx.commit();
//            session.close();

            Session session = SessionFactoryImpl.getSessionFactory().openSession();
//            Company company = session.load(Company.class, id);
            Transaction tx = session.beginTransaction();
//            session.delete(company);


//            Query query = SessionFactoryImpl.getSessionFactory().openSession().createQuery("DELETE FROM Company where company_id =: companyId");
//            query.setParameter("companyId", id);
//            int result = query.executeUpdate();

            Query query = session.createQuery("DELETE FROM Company where company_id = : id");
            query.setParameter("id", id);
            int result = query.executeUpdate();
            tx.commit();
            session.close();


            // Тут нужно удаление
//           ***************************  образец
//            Query query = session.createQuery("DELETE FROM Developer WHERE id = :developerId");
//            query.setParameter("developerId", 1);


            isDeleted = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public Company findCompanyById(int id) {
        Company company = null;
        try {
            //Тут нужен поиск
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("companyId"), id));
            company = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return company;
    }

    @Override
    public Company findCompanyByName(String name) {
        Company company = null;
        try {
            // Тут нужен поиск по имени
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("companyName"), name));
            company = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();


        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return company;
    }

    @Override
    public List<Company> showCompanies() {
        List<Company> companies = (List<Company>)SessionFactoryImpl.getSessionFactory().openSession().createQuery("From Company").list();
        return companies;
    }
}

