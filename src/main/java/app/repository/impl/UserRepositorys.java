package app.repository.impl;

import app.entity.Users;
import app.repository.AppRepositorys;
import app.utils.Constant;
import app.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
public class UserRepositorys implements AppRepositorys<Users> {

    @Override
    public String create(Users user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();


            String hql = "INSERT INTO User (name, email) " +
                    "VALUES (:name, :email)";

            MutationQuery query = session.createMutationQuery(hql);

            query.setParameter("name", user.getName());
            query.setParameter("email", user.getEmail());

            query.executeUpdate();

            transaction.commit();


            return Constant.DATA_INSERT_MSG;
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
    public Optional<List<Users>> read() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction;

            transaction = session.beginTransaction();

            List<Users> list =
                    session.createQuery("FROM User", Users.class)
                            .list();

            transaction.commit();

            return Optional.of(list);
        } catch (Exception e) {

            return Optional.empty();
        }
    }

    @Override
    public String update(Users user) {

        if (readById(user.getId()).isEmpty()) {
            return Constant.DATA_ABSENT_MSG;
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {

                transaction = session.beginTransaction();

                String hql = "UPDATE User SET name = :name, email = :email WHERE id = :id";

                MutationQuery query = session.createMutationQuery(hql);

                query.setParameter("name", user.getName());
                query.setParameter("email", user.getEmail());
                query.setParameter("id", user.getId());

                query.executeUpdate();

                transaction.commit();

                return Constant.DATA_UPDATE_MSG;
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
            return Constant.DATA_ABSENT_MSG;
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {

                transaction = session.beginTransaction();

                String hql = "DELETE FROM User WHERE id = :id";

                MutationQuery query = session.createMutationQuery(hql);

                query.setParameter("id", id);

                query.executeUpdate();

                transaction.commit();

                return Constant.DATA_DELETE_MSG;
            } catch (Exception e) {
                if (transaction != null) {

                    transaction.rollback();
                }

                return e.getMessage();
            }
        }
    }

    @Override
    public Optional<Users> readById(Long id) {
        Transaction transaction = null;
        Optional<Users> optional;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            String hql = " FROM User c WHERE c.id = :id";

            Query<Users> query = session.createQuery(hql, Users.class);
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


    private boolean isEntityWithSuchIdExists(Users user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            user = session.get(Users.class, user.getId());
            if ( user != null) {
                Query<Users> query = session.createQuery("FROM User", Users.class);
                query.setMaxResults(1);
                query.getResultList();
            }
            return user != null;
        }
    }
}
