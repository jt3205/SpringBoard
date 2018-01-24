package net.jtorol.dao;

import java.util.List;

import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;

public interface BoardDAO {
	public void create(BoardVO vo) throws Exception;
	public BoardVO read(int bno) throws Exception;
	public void update(BoardVO vo) throws Exception;
	public void delete(int bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(Criteria cri) throws Exception;
}
