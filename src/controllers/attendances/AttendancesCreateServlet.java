package controllers.attendances;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import models.validators.AttendanceValidator;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesCreateServlet
 */
@WebServlet("/attendances/create")
public class AttendancesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Attendance a = new Attendance();

            a.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            Date work_date =new Date(System.currentTimeMillis());
            String wd_str = request.getParameter("work_date");
            if(wd_str != null && !wd_str.equals("")) {
                work_date = Date.valueOf(request.getParameter("work_date"));
            }
            a.setWork_date(work_date);
            a.setEntry(work_date);
            a.setName(((Employee)request.getSession().getAttribute("login_employee")).getName());

            Date arrive = new Date(System.currentTimeMillis());
            String ar = request.getParameter("arrive");
            if(ar != null && !ar.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                try {
                    arrive = new Date(sdf.parse(request.getParameter("arrive")).getTime());
                } catch(ParseException e) {
                    e.printStackTrace();
                }
            }
            a.setArrive(arrive);

            Date finish = new Date(System.currentTimeMillis());
            String fh = request.getParameter("finish");
            if(fh != null && !fh.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                try {
                    finish = new Date(sdf.parse(request.getParameter("finish")).getTime());
                } catch(ParseException e) {
                    e.printStackTrace();
                }
            }
            a.setFinish(finish);


                List<String> errors = AttendanceValidator.validate(a,true,true);
                if(errors.size() > 0) {
                    em.close();


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();
                em.close();
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("attendance", a);
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/attendances/index");
            }
        }
    }
}




