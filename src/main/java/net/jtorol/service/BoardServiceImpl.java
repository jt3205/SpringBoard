package net.jtorol.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.jtorol.dao.BoardDAO;
import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;
import net.jtorol.domain.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listPage(SearchCriteria cri) throws Exception {
		return dao.listPage(cri);
	}
	
	@Override
	public int countTotal(SearchCriteria cri) throws Exception {
		return dao.countBoard(cri);
	}
}
