package net.jtorol.dao;

import net.jtorol.domain.UserVO;

public interface UserDAO {
	public String getTime();
	public void insertUser(UserVO vo);
}
