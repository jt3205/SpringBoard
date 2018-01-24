package net.jtorol.dao;

import java.util.HashMap;
import java.util.Map;

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
	
	@Override
	public UserVO readUser(String uid) throws Exception {	
		return sqlSession.selectOne(namespace + ".selectUser", uid);
	}
	@Override
	public UserVO readUserWithPW(String uid, String upw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("upw", upw);
		return sqlSession.selectOne(namespace + ".selectUserWithPW", paramMap);
	}
}
