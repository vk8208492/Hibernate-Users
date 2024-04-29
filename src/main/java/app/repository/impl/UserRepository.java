package app.repository.impl;

import app.entity.User;
import app.repository.AppRepository;
import app.utils.Constants;
import app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
public class UserRepository implements AppRepository {

    @Override
    public String create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();


            String hql = "INSERT INTO User (name, email) " +
                    "VALUES (:name, :email)";

            MutationQuery query = session.createMutationQuery(hql);

            query.setParameter("name", user.getName());
            query.setParameter("email", user.getEmail());

            query.executeUpdate();

            transaction.commit();


            return Constants.DATA_INSERT_MSG;
        } catch (Exception e) {
            if (transaction != null) {
                // Відкочення поточної транзакції ресурсу
                transaction.rollback();
            }
            // Повернення повідомлення про помилку роботи з БД
            return e.getMessage();
        }
    }

    @Override
    public Optional<List<User>> read() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;

            transaction = session.beginTransaction();

            List<User> list =
                    session.createQuery("FROM User", User.class)
                            .list();

            transaction.commit();

            return Optional.of(list);
        } catch (Exception e) {

            return Optional.empty();
        }
    }

    @Override
    public String update(User user) {

        if (readById(user.getId()).isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                transaction = session.beginTransaction();

                String hql = "UPDATE User SET name = :name, email = :email WHERE id = :id";

                MutationQuery query = session.createMutationQuery(hql);

                query.setParameter("name", user.getName());
                query.setParameter("email", user.getEmail());
                query.setParameter("id", user.getId());

                query.executeUpdate();

                transaction.commit();

                return Constants.DATA_UPDATE_MSG;
            } catch (Exception e) {
                if (transaction != null) {

                    transaction.rollback();
                }

                return e.getMessage();
            }
        }
    }

    @Override
    public String delete(Long id) {

        if (readById(id).isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                transaction = session.beginTransaction();

                String hql = "DELETE FROM User WHERE id = :id";

                MutationQuery query = session.createMutationQuery(hql);

                query.setParameter("id", id);

                query.executeUpdate();

                transaction.commit();

                return Constants.DATA_DELETE_MSG;
            } catch (Exception e) {
                if (transaction != null) {

                    transaction.rollback();
                }

                return e.getMessage();
            }
        }
    }

    @Override
    public Optional<User> readById(Long id) {
        Transaction transaction = null;
        Optional<User> optional;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = " FROM User c WHERE c.id = :id";

            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("id", id);

            optional = query.uniqueResultOptional();

            transaction.commit();

            return optional;
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();
            }

            return Optional.empty();
        }
    }


    private boolean isEntityWithSuchIdExists(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            user = session.get(User.class, user.getId());
            if (user != null) {
                Query<User> query = session.createQuery("FROM User", User.class);
                query.setMaxResults(1);
                query.getResultList();
            }
            return user != null;
        }
    }
}
