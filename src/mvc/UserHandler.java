package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.UserVO;
import service.UserService;

public class UserHandler implements URIHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// uri �Ľ̺κ�
		String command = req.getRequestURI();
		String cmds[] = null;
		if (command.indexOf(req.getContextPath()) == 0) {
			// ���ؽ�Ʈ �н���ŭ �߶󳽴�.
			command = command.substring(req.getContextPath().length());
			cmds = command.split("/");
		}
		/*
		 * cmds�� 0:���� 1:user 2: join �Ǵ� login ���
		 */

		String view = null;
		String menu = null;
		if (cmds.length < 3) {
			menu = "login"; // �⺻ ���� login����
		} else {
			menu = cmds[2];
		}
		switch (menu) {
		case "join":
			view = joinUser(req, res);
			break;
		case "login":
			view = loginUser(req, res);
			break;
		}
		return view;
	}

	private String joinUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) { // ȸ�� ���� ��û������
			req.setCharacterEncoding("UTF-8");
			String id = req.getParameter("uid");
			String name = req.getParameter("uname");
			String pass1 = req.getParameter("pass1");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setName(name);
			vo.setPassword(pass1);
			
			UserService service = UserService.getInstance();
			int result = service.join(vo);
			
			if(result < 0) {
				req.setAttribute("msg", "�۾��� ���� �߻�");
				return "/WEB-INF/view/join.jsp";
			} else if(result == 0) {
				req.setAttribute("msg", "�ߺ��� id�� �ֽ��ϴ�");
				return "/WEB-INF/view/join.jsp";
			}
			res.sendRedirect("/");
			return null;
		} else { // ȸ�� ���� ������ ��û
			return "/WEB-INF/view/join.jsp";
		}
	}

	private String loginUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) { // ȸ�� ���� ��û������
			req.setCharacterEncoding("UTF-8");
			
			String id = req.getParameter("uid");
			String pass = req.getParameter("pass1");
			
			UserVO user = UserService.getInstance().getUser(id);
			
			if(user == null) {
				req.setAttribute("msg", "�ش� ������ �������� �ʽ��ϴ�");
				return "/WEB-INF/view/login.jsp";
			}
			req.getSession().setAttribute("login", user);
			res.sendRedirect("/");
			return null;
		} else { // ȸ�� ���� ������ ��û
			return "/WEB-INF/view/login.jsp";
		}
	}
}

