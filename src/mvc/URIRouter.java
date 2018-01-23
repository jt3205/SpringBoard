package mvc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URIRouter extends HttpServlet {
	private Map<String, URIHandler> uriHandlerMap = new HashMap();

	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);

		try {
			FileReader fs = new FileReader(configFilePath);
			prop.load(fs);
		} catch (IOException e) {
			throw new ServletException(e);
		}

		Iterator keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);

			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				URIHandler handlerInstance = (URIHandler) handlerClass.newInstance();
				uriHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
		
		System.out.println(uriHandlerMap.toString());
	}

	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String command = req.getRequestURI();
		if (command.indexOf(req.getContextPath()) == 0) {
			// ���ؽ�Ʈ �н���ŭ �߶󳽴�.
			command = command.substring(req.getContextPath().length());
			int slashIndex = command.indexOf('/', 1);
			if (slashIndex >= 0) {
				command = command.substring(0, slashIndex);
			}
		}
		// ���� http://localhost:8080/TestJSP/main/a �� ��û�ߴٸ�
		// RequestURI �� /TestJSP/main/a �̰�
		// ���⼭ contextPath�� �߶󳻸� /main/a�� ���� �ȴ�.
		URIHandler handler = uriHandlerMap.get(command);
		if (handler == null) {
			// ���ٸ� ���ڵ鷯�� �������ְ�.
			handler = new NullHandler();
		}
		// �ִٸ� �ش� �ڵ鷯�� process�� ������ ������ viewPage�� ������ dispatch���ش�.
		String viewPage = null;
		try {
			viewPage = handler.process(req, res);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		// ���� ���� viewPage�� �Ѿ�Դٸ� �ش� ���������� ����ġ
		if (viewPage != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}
}
