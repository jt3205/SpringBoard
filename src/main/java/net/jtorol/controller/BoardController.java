package net.jtorol.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.jtorol.domain.BoardVO;
import net.jtorol.domain.Criteria;
import net.jtorol.domain.PageMaker;
import net.jtorol.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService bs;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void regsterGET() throws Exception {
		logger.info("글 등록 요청입니다. GET");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(BoardVO board, Model model, RedirectAttributes rttr) {
		logger.info("글 등록 POST 요청입니다.");
		logger.info(board.toString());
		String result = "success";
		try {
			bs.regist(board);
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
			model.addAttribute("errorMsg", e.getMessage());
			return "/board/afterPost";
		}
		// model.addAttribute("result", result);
		rttr.addFlashAttribute("result", "success");
		rttr.addFlashAttribute("msg", "글이 성공적으로 작성되었습니다.");
		return "redirect:/board/list";
	}

	// @RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("모든 글을 가져옵니다");
		List<BoardVO> boardList = bs.listAll();
		model.addAttribute("list", boardList);
	}

	@RequestMapping(value = { "/list/{page}/{perPageNum}", "/list", "/list/{page}" }, method = RequestMethod.GET)
	public String listPage(Criteria cri, Model model) throws Exception {
		logger.info("페이지 조건에 맞는 글을 불러옵니다.");
		List<BoardVO> boardList = bs.listPage(cri);
		model.addAttribute("list", boardList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(200);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/board/list";
	}

	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") int bno, Model model) throws Exception {
		BoardVO board = bs.read(bno);
		String content = board.getContent();
		content = content.replace("\r\n", "<br>");
		board.setContent(content);
		model.addAttribute("board", board);
		return "/board/read";
	}

	@RequestMapping(value = "/remove/{bno}", method = RequestMethod.GET)
	public String remove(@PathVariable("bno") int bno, RedirectAttributes rttr) throws Exception {
		bs.remove(bno);
		rttr.addFlashAttribute("result", "success");
		rttr.addFlashAttribute("msg", "성공적으로 삭제되었습니다.");
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/modify/{bno}", method = RequestMethod.GET)
	public String modifyGET(@PathVariable("bno") int bno, Model model) throws Exception {
		model.addAttribute("board", bs.read(bno));
		return "/board/modify";
	}

	@RequestMapping(value = "/modify/{bno}", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		rttr.addFlashAttribute("result", "success");
		rttr.addFlashAttribute("msg", "수정되었습니다.");
		return "redirect:/board/list";
	}
}