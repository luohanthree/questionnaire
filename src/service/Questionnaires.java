package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.questionaire.Question;
import entity.questionaire.Questionnaire;
import org.jetbrains.annotations.NotNull;
import utils.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author zhihuan
 */
public class Questionnaires {
    Logger logger = Logger.getLogger("ques");
    private final int id;

    /**
     * @param id 当前登录用于的id;
     */
    public Questionnaires(int id) throws SQLException {
        this.id = id;
    }

    /**
     * @param questions 传入JSON数组
     * @throws SQLException
     */
    public void createNew(JSONArray questions, @NotNull String questionnaire_name) throws SQLException {
        Questionnaire questionnaire = new Questionnaire();
        List<String> options = new ArrayList<>();
        for (Object str : questions) {
            logger.info(str.toString());
            questionnaire.addQuestion(JSON.parseObject(str.toString(), Question.class));
        }
        String insetSql = "INSERT INTO questionnaire values (?,?,?,?,?,?,?)";
        Connection conn = DBTools.getDatasource().getConnection();
        PreparedStatement ps = conn.prepareStatement(insetSql);
        List<Question> questionList = questionnaire.getQuestions();
        logger.info(questionnaire.getFormId().toString());
        for (int i = 0; i < questionnaire.getQuestions().size(); i++) {
            Question question = questionList.get(i);
            ps.setString(1, questionnaire.getFormId().toString());
            ps.setInt(2, i);
            ps.setInt(3, id);
            ps.setString(4, question.getQuestion_name());
            ps.setString(5, question.getType());
            ps.setString(6, questionnaire_name);
            ps.setString(7, String.join("#",question.getOptions()));
            ps.executeUpdate();
        }
    }

    /**
     * @param formId 表单的UUID
     * @return 返回formID对应的问卷
     */
    public Questionnaire findOne(String formId) throws SQLException {
        Questionnaire questionnaire = new Questionnaire();
        List<Question> questions = new ArrayList<>();
        Connection conn = DBTools.getDatasource().getConnection();
        String selectQuestionnaire = "SELECT * FROM questionnaire WHERE formId = ?";
        PreparedStatement ps = conn.prepareStatement(selectQuestionnaire);
        ps.setString(1, formId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Question question = new Question();
            question.setQuestion_name(rs.getString("quesName"));
            question.setType(rs.getString("quesType"));
            question.setOptions(Arrays.stream(rs.getString("options").split("#")).toList());
            questionnaire.addQuestion(question);
        }
        return questionnaire;
    }

    /**
     * @param userId 用户ID
     * @return 返回该用户创建的问卷表
     * @throws SQLException
     */
    public List<Questionnaire> findAll(Integer userId) throws SQLException {
        Connection conn = DBTools.getDatasource().getConnection();
        var questionnaires = new ArrayList<Questionnaire>();
        Questionnaire questionnaire = new Questionnaire();
        String selectFormId = "SELECT formId FROM questionnaire WHERE createUserId = ?";
        PreparedStatement ps = conn.prepareStatement(selectFormId);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            questionnaire = findOne(rs.getString("formId"));
            questionnaires.add(questionnaire);
        }
        return questionnaires;
    }

    /**
     * @return 返回数据库这种所有问卷，用于管理
     */
    public List<Questionnaire> findAll() throws SQLException {
        Connection conn = DBTools.getDatasource().getConnection();
        List<Questionnaire> questionnaires = new ArrayList<>();
        String selectFormId = "SELECT formId FROM questionnaire";
        PreparedStatement ps = conn.prepareStatement(selectFormId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Questionnaire questionnaire = findOne(rs.getString("formId"));
            questionnaires.add(questionnaire);
        }

        return questionnaires;
    }

    /**
     * @param formId 需要删除的调查表的id
     * @throws SQLException
     */
    public void delOne(String formId) throws SQLException {
        Connection conn = DBTools.getDatasource().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM questionnaire WHERE formId = ?");
        ps.setString(1, formId);
        ps.executeUpdate();
    }

    public void delAll(Integer userId) throws SQLException {
        Connection conn = DBTools.getDatasource().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM questionnaire WHERE createUserId = ?");
        ps.setInt(1, userId);
        ps.executeUpdate();
    }

}
