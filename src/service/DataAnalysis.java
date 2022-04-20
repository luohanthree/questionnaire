package service;

import entity.Questionnaire;
import entity.Samples;
import utils.DBTools;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhihuan
 */
public class DataAnalysis {
    private List<Samples> resultSamples;

    public List<Samples> dataAnalysis(Integer formId) throws SQLException {
        Samples samples = new Samples();
        Connection conn = DBTools.getDatasource().getConnection();
        String sql = " SELECT * FROM samples where formId = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,formId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Integer sampleId = rs.getInt("sampleId");
            Integer quesId = rs.getInt("quesId");
            String answer = rs.getString("answer");
            samples.setSampleId(sampleId);
            samples.setAnswer(answer);
            samples.setQuesId(quesId);
            samples.setFormId(formId);
            this.resultSamples.add(samples);
        }

        return resultSamples;
    }

    public Map selectQues(Integer createUserId) throws SQLException {
        List<Questionnaire> forms = new ArrayList<>();
        Map<Integer, List> userForm = new HashMap<>();
        Questionnaire questionnaire = new Questionnaire();
        Connection conn = DBTools.getDatasource().getConnection();
        String sql = " SELECT * FROM questionnaire where formId = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, createUserId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Integer quesId = rs.getInt("quesId");
            Integer formId = rs.getInt("formId");
            String quesName = rs.getString("quesName");
            String quesType = rs.getString("quesType");
            questionnaire.setQuesId(quesId);
            questionnaire.setQuesName(quesName);
            questionnaire.setQuesType(quesType);
            questionnaire.setCreateId(createUserId);
            questionnaire.setFormId(formId);
            forms.add(createUserId,questionnaire);
        }
        userForm.put(createUserId, forms);
        return userForm;
    }
}
