package au.edu.unsw.sltf.services.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MYHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int BYTES_DOWNLOAD = 1024;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("HttpRequest "+request.getRequestURL());
		String requestUri= request.getRequestURI();
		String eventSetId=requestUri.substring(requestUri.lastIndexOf('/')+1);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename="+eventSetId);
		ServletContext ctx = getServletContext();
		InputStream is = ctx.getResourceAsStream("/WEB-INF/classes/market_data/"+eventSetId);

		int read = 0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();

		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
	}

}
