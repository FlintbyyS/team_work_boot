package ru.flint.team_work_boot.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.entity.Group;

import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Group> getAllGroups() {

        return entityManager.createQuery("from Group ",Group.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Group getGroupByName(String name) {

        return entityManager.find(Group.class,name);
    }

    @Override
    @Transactional
    public void saveGroup(Group group) {
        entityManager.persist(group);
    }
}
