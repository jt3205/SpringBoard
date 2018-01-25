package net.jtorol.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.jtorol.dao.BoardDAO;
import net.jtorol.dao.UserDAO;
import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;
import net.jtorol.domain.SearchCriteria;
import net.jtorol.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardDAOTest {
	@Inject
	private BoardDAO dao;

	// @Test
	public void testCreate() throws Exception {
		BoardVO vo = new BoardVO();

		vo.setTitle("새로운 글의 제목 입니다");
		vo.setContent("글의 내용입니다");
		vo.setWriter("jtorol");
		dao.create(vo);
	}

	// @Test
	public void testRead() throws Exception {
		System.out.println(dao.read(1).toString());
	}

	// @Test
	public void testGetList() throws Exception {
		List<BoardVO> voList = new ArrayList<BoardVO>();
		voList = dao.listAll();

		for (BoardVO vo : voList) {
			System.out.println(vo.toString());
		}
	}

	// @Test
	public void testUpdate() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setTitle("수정된 제목입니다");
		vo.setContent("수정된 내용입니다. 이 내용은 수정되었습니다.");

		dao.update(vo);
	}

	// @Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}

	@Test
	public void testListPage() throws Exception {
		SearchCriteria cri = new SearchCriteria();

		cri.setPage(2);
		cri.setPerPageNum(15);

		List<BoardVO> list = dao.listPage(cri);

		for (BoardVO board : list) {
			System.out.println(board.toString());
		}
	}
}
