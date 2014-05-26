package com.ahmed.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	@Autowired
	private PasswordEncoder passwordEncoder;
//	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private SessionFactory sessionFacory;
	
	public Session session()
	{
		return sessionFacory.getCurrentSession();
	}

	public UsersDao() {
		System.out.println("Successufuly loaded offersDao");
	}

//	@Autowired
//	public void setDataSource(DataSource jdbc) {
//		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
//	}

	

	@Transactional
	public void create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
		
		 
	}

	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		//for any attribute
		//crit.add(Restrictions.eq("username", username));
		//for id
		crit.add(Restrictions.idEq(username));
		User user = (User)crit.uniqueResult();
		return user != null;
		
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
//		return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
		
	}

	
}
