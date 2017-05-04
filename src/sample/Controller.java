package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    String path="Test";
    @FXML
    JFXButton submit;

    @FXML
    JFXTextField rule_name;

    @FXML
    JFXTextArea description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        submit.setOnMouseClicked(event -> {
            File root=new File(path);
            if (!root.isDirectory()){
                root.mkdir();
            }

            String rule=rule_name.getText();
            String desc=description.getText();

            path=path+File.separator+rule;
            System.out.print(Paths.get("").toAbsolutePath().toString()+File.separator+"Test"+File.separator+rule);
            File file=new File(path);
            if (!file.isDirectory()){
                file.mkdir();
            }

            path=path+File.separator;

            File rulesfile=new File(path+rule+".js");
            if (!rulesfile.exists()){
                try {
                    rulesfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                FileWriter writer=new FileWriter(rulesfile);
                writer.write("// This is automated rule file generated for writing custom rule\n");
                writer.write(Main.getTemplate(desc,true,"Custom Rule"));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            File metadata=new File(path+rule+"metadata.json");
            if (!metadata.exists()){
                try {
                    metadata.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                FileWriter writer=new FileWriter(metadata);
                JSONObject object=new JSONObject();
                object.put("Desc",desc);
                object.put("Recommended",true);
                object.put("Fixable",false);
                object.put("Link","http://www.google.com");
                object.put("Name",rule);
                writer.write(object.toString(4));
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            File ruleConfig=new File(path+rule+"config.json");
            if (!ruleConfig.exists()){
                try {
                    ruleConfig.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                FileWriter writer=new FileWriter(ruleConfig);

                writer.write(Main.getParentJSONObject().put("rules",new JSONObject().put(rule,"error")).toString(4));
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


            File TestRule=new File(path+rule+"test.js");
            if (!TestRule.exists()){
                try {
                    TestRule.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                FileWriter writer=new FileWriter(TestRule);
                writer.write("//This is a sample test file for given rule\n");
                writer.write("var a=\"BMC rule\"");
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Platform.exit();
        });

    }
}
