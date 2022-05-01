package servlets;

import entity.Questionnaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "createForm", value = "/doCreate.do")
public class createForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Questionnaire questionnaire = new Questionnaire();
        List<Questionnaire> questionnaireList = new ArrayList<>();
        Enumeration<String> names = request.getParameterNames();
        String[] quesNames = request.getParameterValues("question_name");
        String[] types = request.getParameterValues("type");
        String[] options = request.getParameterValues("options");
        String[] tests = request.getParameterValues("jsonStr");
        Logger logger = Logger.getLogger("ques");
        logger.info(Arrays.toString(quesNames));
        logger.info(Arrays.toString(tests));
        while (names.hasMoreElements()) {
            logger.info(names.nextElement());
        }
//       for (String q : quesNames) {
//           logger.info(q);
//       }
//       for (String t : types) {
//           logger.info(t);
//       }
//       for (String o : options) {
//           logger.info(o);
//       }
    }
}
