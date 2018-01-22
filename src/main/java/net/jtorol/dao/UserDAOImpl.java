package net.jtorol.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.jtorol.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "net.jtorol.mapper.UserMapper";

	@Override
	public String getTime() {
		sqlSession.selectOne(namespace + ".getTime");
		return null;
	}
	@Override
	public void insertUser(UserVO vo) {
		sqlSession.insert(namespace + ".insertUser", vo);
	}
}
