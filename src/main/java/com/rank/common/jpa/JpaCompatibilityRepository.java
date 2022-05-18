package com.rank.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caolp
 * 相当于你在使用spring data jpa 的时候，
 * 每个实体类有需要实现的相同的方法，就可以单独抽取出来，放在一个公共的接口MyRepository中，并这个类继承了jpa的相关Repository接口或类，
 * 由MyRepository接口来衔接jpa的相关操作，其他实体类需要实现的操作就直接继承MyRepository接口，不用每次都去继承jpa的相关接口或类啦，
 * 所以这个公共接口就需要这个注解@NoRepositoryBean来标识
 */
@NoRepositoryBean
public interface JpaCompatibilityRepository<ET, IDT extends Serializable> extends JpaRepository<ET, IDT> {

    /**
     * save all entities
     * @param entities must not be null
     * @return the saved entities
     */
    default <S extends ET> List<S> save(Iterable<S> entities){
        return saveAll(entities);
    }

    /**
     * delete all given entities
     * @param entities given entities
     */
    default void delete(Iterable<? extends ET> entities){
        deleteAll(entities);
    }

    /**
     * delete an entity by id
     * @param id given id
     */
    default void delete(IDT id) {
        deleteById(id);
    }

    /**
     * find one entity by id
     * @param id given id
     * @return entity
     */
    default ET findOne(IDT id) {
        return findById(id).orElse(null);
    }

    /**
     * find one entity by id
     * @param id given id
     * @return entity
     */
    default ET findByIdOrElseNull(IDT id) {
        return findById(id).orElse(null);
    }

    /**
     * find all entities
     * @param ids given ids
     * @return all entities
     */
    default List<ET> findAll(Iterable<IDT> ids) {
        return findAllById(ids);
    }

}
