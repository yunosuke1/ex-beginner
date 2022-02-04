package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Member;

@Repository
public class MemberRepository {
	@Autowired
	NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Member> MEMBER_ROW_MAPPER = new BeanPropertyRowMapper<>(Member.class);

	/**
	 * 名前を曖昧検索して結果のリストを返す
	 * @param name
	 * @return memberList
	 */
	public List<Member> findFromName(String name) {
		String sql = "SELECT * FROM members WHERE name LIKE :name";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Member> memberList = template.query(sql,param, MEMBER_ROW_MAPPER);
		
		return memberList;
	}
}
