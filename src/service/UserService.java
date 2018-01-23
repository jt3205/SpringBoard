package service;

import java.sql.Connection;

import dao.UserDAO;
import domain.UserVO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class UserService {
	// �̱��� ����
	private static UserService us = new UserService();

	public static UserService getInstance() {
		return us;
	}

	private UserService() {
	}
	// �̱��� ���� ��.

	// ȸ������ ����.
	public int join(UserVO vo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection(); // DB���� ��������
			// UserVO data = UserDAO.getInstance().selectById(conn, user.getId()); // �ش�
			// ���̵��� ������ �ִ��� ã�ƺ���
			UserDAO dao = UserDAO.getInstance();
			UserVO data = dao.selectById(conn, vo.getId());
			if (vo != null) { // ȸ���� �����Ұ�쿡�� 0 ����
				return 0;
			}
			dao.insert(conn, vo);
			// UserDAO.getInstance().insert(conn, vo);
			return 1; // ���������� ���Խ� 1����.
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // ȸ�� ���� ������ ���� �߻��ÿ��� -1 ����
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public UserVO getUser(String id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			UserVO data = UserDAO.getInstance().selectById(conn, id);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
