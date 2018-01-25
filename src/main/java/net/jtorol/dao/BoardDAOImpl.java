package net.jtorol.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;
import net.jtorol.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession session;
	
	private static String namespace = "net.jtorol.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listPage", cri);
	}
	
	@Override
	public int countBoard(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".countBoard", cri);
	}
}
