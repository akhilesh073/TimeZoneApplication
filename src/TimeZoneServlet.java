

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.akh.service.TimeZoneServiceImpl;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet({"/","/showTimeZone"})
public class TimeZoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeZoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TimeZoneServiceImpl.getAvailableTimeZines();
		request.setAttribute("availableZones", TimeZoneServiceImpl.availableTimezones);
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("showZone") != null) {
			System.out.println("showZone");
			TimeZoneServiceImpl tzone = new TimeZoneServiceImpl();
			String[] requests = request.getParameterValues("zones");
			Map<String, String> timeZones = tzone.getTimeZone(requests);
			request.setAttribute("timeZones", timeZones);
			request.getRequestDispatcher("/WEB-INF/zones.jsp").forward(request,
					response);
		}
		if (request.getParameter("setZone") != null) {
			System.out.println("setZone");
			String zoneName=request.getParameter("zoneName");
			String offset=request.getParameter("offset");
			TimeZoneServiceImpl tzone = new TimeZoneServiceImpl();
			tzone.setZone(zoneName,offset);
			request.setAttribute("availableZones", TimeZoneServiceImpl.availableTimezones);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			
			
			
		}
	}

}
