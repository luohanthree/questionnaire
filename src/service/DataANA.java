package service;

import dao.QuestionnaireDao;
import dao.SamplesDao;
import entity.Results;
import entity.Samples;
import entity.questionaire.Question;

import java.util.*;

/**
 * @author zhihuan
 */
public class DataANA {
    public void addSamples(Map<String,String[]> answers, UUID formId, String ip) {
        QuestionnaireDao questionnaireDao = new QuestionnaireDao();
        List<Question> questions = questionnaireDao.findOne(formId.toString()).getQuestions();
        List<Samples> sampleList = new ArrayList<>();
        Samples samples = new Samples();
        UUID samplesId= UUID.randomUUID();
        for (Question question : questions) {
           samples.setSampleId(samplesId);
           samples.setAnswer(List.of(answers.get(question.getQuestion_name())));
           samples.setFormId(formId);
           samples.setIp(ip);
           samples.setQuestion_name(question.getQuestion_name());
           samples.setFormId(formId);
           sampleList.add(samples);
        }
        SamplesDao samplesDao = new SamplesDao();
        samplesDao.addOne(sampleList);
    }

    public Map<String, Map<String,Integer>> dataANA(UUID formId) {
        SamplesDao samplesDao = new SamplesDao();
        Results results = samplesDao.getData(formId);
        int totalNums = 0;

        return null;
    }
}
