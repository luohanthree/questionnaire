package service;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author zhihuan
 */
public class CreatForm {
    public void createNew(UUID formId) {
        Logger logger = Logger.getLogger("service.CreatFrom");
        try(FileChannel model = new FileInputStream("/questionnaire/questionnaires/model.jsp").getChannel();
          FileChannel newForm = new FileOutputStream("/questionnaire/questionnaires/" + formId.toString() + ".jsp").getChannel()) {
          newForm.transferFrom(model,0,model.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
