package com.zxp.dao;


import com.zxp.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 不仅继承啦 PagingAndSortingRepository 同时继承了 QueryByExampleExecutor（示例匹配器）
 *
 * 根据规则组织方法名称
 * 可以快速自定义查询方法
 */
 public interface UserJpaRepository extends JpaRepository<User, Long> {
    /**
     * And
     * 对应sql：where name=? and age=?
     *
     * @param name
     * @param age
     * @return
     */
     List<User> findByNameAndAge(String name, int age);

    /**
     * Or
     * 对应sql：where name=? or age=?
     *
     * @param name
     * @param age
     * @return
     */
     List<User> findByNameOrAge(String name, int age);

    /**
     * Is
     * 对应sql：where name=?
     *
     * @param name
     * @return
     */
     List<User> findByNameIs(String name);

    /**
     * Equals
     * 对应sql：where name=?
     *
     * @param name
     * @return
     */
     List<User> findByNameEquals(String name);

    /**
     * Between
     * 对应sql：where age between ? and ?
     *
     * @param LittleAge
     * @param bigAge
     * @return
     */
     List<User> findByAgeBetween(int LittleAge, int bigAge);

    /**
     * LessThan
     * 对应sql：where age<?
     *
     * @param age
     * @return
     */
     List<User> findByAgeLessThan(int age);

    /**
     * AgeLessThanEqual
     * 对应sql：where age<=?
     *
     * @param age
     * @return
     */
     List<User> findByAgeLessThanEqual(int age);

    /**
     * LessThan
     * 对应sql：where age>?
     *
     * @param age
     * @return
     */
     List<User> findByAgeGreaterThan(int age);

    /**
     * GreaterThanEqual
     * 对应sql：where age>=?
     *
     * @param age
     * @return
     */
     List<User> findByAgeGreaterThanEqual(int age);

    /**
     * After
     * 对应sql：where age>?
     *
     * @param age
     * @return
     */
     List<User> findByAgeAfter(int age);

    /**
     * Before
     * 对应sql：where age<?
     *
     * @param age
     * @return
     */
     List<User> findByAgeBefore(int age);

    /**
     * IsNull（不用参数）
     * 对应sql：where name is null
     *
     * @return
     */
     List<User> findByNameIsNull();

    /**
     * IsNotNull（不用参数）
     * 对应sql：where name is not null
     *
     * @return
     */
     List<User> findByNameIsNotNull();

    /**
     * NotNull（不用参数）
     * 对应sql：where name is not null
     *
     * @return
     */
     List<User> findByNameNotNull();

    /**
     * Not
     * 对应sql：where name <>?
     *
     * @param name
     * @return
     */
     List<User> findByNameNot(String name);

    /**
     * In
     * 对应sql：where age in (?)
     *
     * @param ageList
     * @return
     */
     List<User> findByAgeIn(List<Integer> ageList);

    /**
     * NotIn
     * 对应sql：where age not in (?)
     *
     * @param ageList
     * @return
     */
     List<User> findByAgeNotIn(List<Integer> ageList);

    /**
     * NotLike
     * 对应sql：where name not like ?
     *
     * @param name
     * @return
     */
     List<User> findByNameNotLike(String name);

    /**
     * Like
     * 对应sql：where name like ?
     *
     * @param name
     * @return
     */
     List<User> findByNameLike(String name);

    /**
     * StartingWith
     * 对应sql：where name like '?%'
     *
     * @param name
     * @return
     */
     List<User> findByNameStartingWith(String name);

    /**
     * EndingWith
     * 对应sql：where name like '%?'
     *
     * @param name
     * @return
     */
     List<User> findByNameEndingWith(String name);

    /**
     * Containing
     * 对应sql：where name like '%?%'
     *
     * @param name
     * @return
     */
     List<User> findByNameContaining(String name);

    /**
     * Contains
     * 对应sql：where name like '%?%'
     *
     * @param name
     * @return
     */
     List<User> findByNameContains(String name);

    /**
     * OrderBy
     * 对应sql：order by age desc
     * desc：降序，asc：升序
     *
     * @return
     */
     List<User> findByOrderByAgeDesc();

    /**
     * Ignorecase
     * 对应sql：where UPPER(name) = UPPER(?)
     *
     * @return
     */
     List<User> findByNameIgnoreCase(String name);








}
