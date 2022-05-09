package servlets;

import dao.QuestionnaireDao;
import entity.questionaire.Questionnaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CreatForm;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "distributedQuestionnaire", value = "/questionnaires/*")
public class distributedQuestionnaire extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        String formId = path.substring(path.lastIndexOf("/")+1);
        QuestionnaireDao questionnaireDao = new QuestionnaireDao();
        Questionnaire questionnaire = questionnaireDao.findOne(formId);
        request.setAttribute("questionnaire",questionnaire);
//        if (!(new File("/questionnaire/questionnaires/" + formId + ".jsp").exists())) {
//            CreatForm creatForm = new CreatForm();
//            creatForm.createNew(UUID.fromString(formId));
//        }
        request.getRequestDispatcher("/model.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
