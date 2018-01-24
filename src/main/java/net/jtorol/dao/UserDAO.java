package net.jtorol.dao;

import net.jtorol.domain.UserVO;

public interface UserDAO {
	public String getTime();

	public void insertUser(UserVO vo);

	public UserVO readUser(String uid) throws Exception;

	public UserVO readUserWithPW(String uid, String upw) throws Exception;

}
