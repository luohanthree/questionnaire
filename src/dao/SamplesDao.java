package dao;

import entity.Results;
import entity.Samples;
import utils.DBTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author zhihuan
 */
public class SamplesDao {
    /**
     *
     * @param samples 传入的样本
     */
    public void addOne(List<Samples> samples) {
        try(Connection conn = DBTools.getDatasource().getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO samples values (?,?,?,?,?)")){
            for (Samples tep : samples) {
                ps.setString(1,tep.getIp());
                ps.setString(2,tep.getQuestion_name());
                ps.setString(3,tep.getAnswer().toString());
                ps.setString(4,tep.getFormId().toString());
                ps.setString(5,tep.getUuid().toString());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param formId 要查看的的formId
     */
    public Results getData(UUID formId) {
        Results results = new Results();
        try(Connection conn = DBTools.getDatasource().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT answer from samples where question_name = ?");
        PreparedStatement psQues = conn.prepareStatement("SELECT quesName from questionnaire where formId = ?");
        PreparedStatement psNums = conn.prepareStatement("SELECT count(*) FROM samples WHERE formId = ?")) {
            List<String> question_name = new ArrayList<>();
            Map<String, List<String>> Q_A = new HashMap<>();
            psQues.setString(1,formId.toString());
            psNums.setString(1,formId.toString());
            ResultSet rs = psQues.executeQuery();
            ResultSet rsNum = psNums.executeQuery();
            while (rs.next()) {
                question_name.add(rs.getString(1));
            }
            int count = 0;
            for (String s : question_name) {
                ps.setString(1,s);
                ResultSet rsData = ps.executeQuery();
                String answers = null;
                while (rsData.next()) {
                    answers = rsData.getString(2);
                }
                while (rsNum.next()) {
                    count = rsNum.getInt(1);
                }
                if (answers != null) {
                    List<String> answer = Arrays.stream(answers.split("&")).toList();
                    Q_A.put(s,answer);
                }
            }
            results.setNums(count);
            results.setFormId(formId);
            results.setQ_A(Q_A);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
