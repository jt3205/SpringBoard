package net.jtorol.service;

import java.util.List;

import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;

public interface BoardService {
	public void regist(BoardVO vo) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void modify(BoardVO vo) throws Exception;
	
	public void remove(int bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(Criteria cri) throws Exception;
}
