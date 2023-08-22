package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.UserDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.entity.custom.User;
import lk.ijse.hostelmanagementsystem.repo.custom.UserRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.UserRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.UserService;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Converter converter;

    FactoryConfiguration factory;

    UserRepo userRepo;

    public UserServiceImpl() {
        this.converter = Converter.getInstance();
        this.factory = FactoryConfiguration.getInstance();
        this.userRepo = new UserRepoImpl();
    }

    @Override
    public UserDTO search(String s) {
        Session session = factory.getSession();
        try {
            User search = userRepo.search(s, session);
            return converter.toOnlyUserDTO(search);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = converter.toOnlyUser(userDTO);
            userRepo.delete(user,session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(UserDTO userDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = converter.toOnlyUser(userDTO);
            userRepo.update(user,session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = converter.toOnlyUser(userDTO);
            User save = userRepo.save(user, session);
            transaction.commit();
            return converter.toOnlyUserDTO(save);
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<UserDTO> getAll() {
        Session session = factory.getSession();
        List<UserDTO> list = new ArrayList<>();
        try {
            List<User> all = userRepo.getAll(session);
            for (User user : all) {
                list.add(converter.toOnlyUserDTO(user));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
