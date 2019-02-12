/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import entity.Appointment;
import entity.User;
import entity.Worker;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AppointmentFacade;
import session.UserFacade;
import session.WorkerFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "WorkerController", urlPatterns = {
    "/fwAddClick",
    "/fwLoadWorkers",
    "/fwUpdateClick",
    "/fwDeleteClick",
    "/fwListClick",
    "/fwDelAppointment",
    "/fwSelectAccount",
    "/fwInsertAccount",
    "/fwDeleteAccount",
    "/fwSetFiltr",
    "/fwSetDayStop",
        

})
public class WorkerController extends HttpServlet {
    @EJB private AppointmentFacade appointmentFacade;
    @EJB private UserFacade userFacade;
    @EJB private WorkerFacade workerFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Map<String,String> collBack= new HashMap<>();
        String path = request.getServletPath();
        if(path!=null){
            switch (path) {
                case "/fwAddClick":
                    String newApp = "false";
                    String status = "false";
                    String  data = request.getParameter("data");
                    Map<String,String> mapJson = new Gson().fromJson(data, HashMap.class);
                    String name = mapJson.get("name");
                    String surname = mapJson.get("surname");
                    String code = mapJson.get("code");
                    
                    String listOrNewAppointment = mapJson.get("listOrNewAppointment");
                    Appointment appointment = null;
                    if("New".equals(listOrNewAppointment)){
                        String appointmentName = mapJson.get("appointment");
                        appointment = new Appointment(appointmentName);
                        appointmentFacade.create(appointment);
                        newApp="true";
                    }else if("List".equals(listOrNewAppointment)){
                        String appointmentId = mapJson.get("appointment");
                        appointment = appointmentFacade.find(Long.parseLong(appointmentId));
                    }
                    String phone = mapJson.get("phone");
                    String bankAccount = mapJson.get("bankAccount");
                    String city = mapJson.get("city");
                    String login = mapJson.get("ligin");
                    String password = mapJson.get("password");
                    User user = new User(login, password);
                    userFacade.create(user);
                    Worker worker = new Worker(name, surname, code, appointment, phone, bankAccount, city, code, user);
                    workerFacade.create(worker);
                    status = "true";
                    // вернуть newApp и status
                    collBack.put("newApp", newApp);
                    collBack.put("status", status);
                    break;
                case "/fwSelectAccount":
                    data = request.getParameter("data");
                    mapJson = new Gson().fromJson(data, HashMap.class);
                    String w_id = mapJson.get("w_id");
                    user = userFacade.find(Long.parseLong(w_id));
                    collBack.put("login", user.getLogin());
                    //Получить usera по  w_id 
                    //вернуть login этого пользователя
                    break;
            }

            try (PrintWriter out = response.getWriter()) {
                out.print(new Gson().toJson(collBack));
            }
        }
    }
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
