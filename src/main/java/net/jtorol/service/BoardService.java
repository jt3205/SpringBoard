package net.jtorol.service;

import java.util.List;

import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;
import net.jtorol.domain.SearchCriteria;

public interface BoardService {
	public void regist(BoardVO vo) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void modify(BoardVO vo) throws Exception;
	
	public void remove(int bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(SearchCriteria cri) throws Exception;
	
	public int countTotal(SearchCriteria cri) throws Exception;
}
