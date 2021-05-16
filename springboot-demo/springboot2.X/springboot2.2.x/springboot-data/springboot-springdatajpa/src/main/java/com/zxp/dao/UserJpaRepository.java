package com.zxp.dao;

import com.zxp.entity.po.UserStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 不仅继承啦 PagingAndSortingRepository 同时继承了 QueryByExampleExecutor（示例匹配器）
 *
 * 根据规则组织方法名称
 * 可以快速自定义查询方法
 */
 public interface UserJpaRepository extends JpaRepository<UserStudent, Long> {
    /**
     * And
     * 对应sql：where name=? and age=?
     *
     * @param name
     * @param age
     * @return
     */
     List<UserStudent> findByNameAndAge(String name, int age);

    /**
     * Or
     * 对应sql：where name=? or age=?
     *
     * @param name
     * @param age
     * @return
     */
     List<UserStudent> findByNameOrAge(String name, int age);

    /**
     * Is
     * 对应sql：where name=?
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameIs(String name);

    /**
     * Equals
     * 对应sql：where name=?
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameEquals(String name);

    /**
     * Between
     * 对应sql：where age between ? and ?
     *
     * @param LittleAge
     * @param bigAge
     * @return
     */
     List<UserStudent> findByAgeBetween(int LittleAge, int bigAge);

    /**
     * LessThan
     * 对应sql：where age<?
     *
     * @param age
     * @return
     */
     List<UserStudent> findByAgeLessThan(int age);

    /**
     * AgeLessThanEqual
     * 对应sql：where age<=?
     *
     * @param age
     * @return
     */
     List<UserStudent> findByAgeLessThanEqual(int age);

    /**
     * LessThan
     * 对应sql：where age>?
     *
     * @param age
     * @return
     */
     List<UserStudent> findByAgeGreaterThan(int age);

    /**
     * GreaterThanEqual
     * 对应sql：where age>=?
     *
     * @param age
     * @return
     */
     List<UserStudent> findByAgeGreaterThanEqual(int age);

    /**
     * After
     * 对应sql：where age>?
     *
     * @param age
     * @return
     */
     List<UserStudent> findByAgeAfter(int age);

    /**
     * Before
     * 对应sql：where age<?
     *
     * @param age
     * @return
     */
     List<UserStudent> findByAgeBefore(int age);

    /**
     * IsNull（不用参数）
     * 对应sql：where name is null
     *
     * @return
     */
     List<UserStudent> findByNameIsNull();

    /**
     * IsNotNull（不用参数）
     * 对应sql：where name is not null
     *
     * @return
     */
     List<UserStudent> findByNameIsNotNull();

    /**
     * NotNull（不用参数）
     * 对应sql：where name is not null
     *
     * @return
     */
     List<UserStudent> findByNameNotNull();

    /**
     * Not
     * 对应sql：where name <>?
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameNot(String name);

    /**
     * In
     * 对应sql：where age in (?)
     *
     * @param ageList
     * @return
     */
     List<UserStudent> findByAgeIn(List<Integer> ageList);

    /**
     * NotIn
     * 对应sql：where age not in (?)
     *
     * @param ageList
     * @return
     */
     List<UserStudent> findByAgeNotIn(List<Integer> ageList);

    /**
     * NotLike
     * 对应sql：where name not like ?
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameNotLike(String name);

    /**
     * Like
     * 对应sql：where name like ?
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameLike(String name);

    /**
     * StartingWith
     * 对应sql：where name like '?%'
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameStartingWith(String name);

    /**
     * EndingWith
     * 对应sql：where name like '%?'
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameEndingWith(String name);

    /**
     * Containing
     * 对应sql：where name like '%?%'
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameContaining(String name);

    /**
     * Contains
     * 对应sql：where name like '%?%'
     *
     * @param name
     * @return
     */
     List<UserStudent> findByNameContains(String name);

    /**
     * OrderBy
     * 对应sql：order by age desc
     * desc：降序，asc：升序
     *
     * @return
     */
     List<UserStudent> findByOrderByAgeDesc();

    /**
     * Ignorecase
     * 对应sql：where UPPER(name) = UPPER(?)
     *
     * @return
     */
     List<UserStudent> findByNameIgnoreCase(String name);








}
