package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.questionaire.Question;
import entity.questionaire.Questionnaire;
import utils.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author zhihuan
 */
public class Questionnaires {
    private int id;
    public Questionnaires(int id) {
        this.id = id;
    }
    Logger logger = Logger.getLogger("ques");
    public void createNew(JSONArray jsonArray) throws SQLException {
        Questionnaire questionnaire = new Questionnaire();
        List<String> options = new ArrayList<>();
        for (Object sre : jsonArray) {
            logger.info(sre.toString());
        }
        for (Object str : jsonArray) {
            logger.info(str.toString());
            questionnaire.addQuestion(JSON.parseObject(str.toString(),Question.class));
        }
        for (Question question : questionnaire.getQuestions()) {
            options.add(String.join("#", question.getOptions()));
        }
        String insetSql = "INSERT INTO questionnaire values (?,?,?,?,?,?,?)";
        Connection conn = DBTools.getDatasource().getConnection();
        PreparedStatement ps = conn.prepareStatement(insetSql);
        List<Question> questionList = questionnaire.getQuestions();
        logger.info(questionnaire.getFormId().toString());
        for (int i = 0; i < questionnaire.getQuestions().size(); i++) {
            Question question = questionList.get(i);
            ps.setString(1, questionnaire.getFormId().toString());
            ps.setInt(2,i);
            ps.setInt(3,id);
            ps.setString(4,question.getQuestion_name());
            ps.setString(5,question.getType());
            ps.setString(6,null);
            ps.setString(7, options.get(i));
            ps.executeUpdate();
        }
    }

    public void createNew(String jsonArray) {
        List<Question> questions = null;
        questions = JSON.parseArray(jsonArray, Question.class);
    }

    public Questionnaire findOne(int formId) {
        Questionnaire questionnaire = new Questionnaire();
        return questionnaire;
    }

    public List<Questionnaire> findAll() {
        var questionnaires = new ArrayList<Questionnaire>();

        return questionnaires;
    }
}
