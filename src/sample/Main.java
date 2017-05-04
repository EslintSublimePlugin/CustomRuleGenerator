package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Rule Generator");
        primaryStage.setScene(new Scene(root, 395, 343));
        primaryStage.show();
    }

    public static JSONObject getParentJSONObject(){
        JSONObject parent=new JSONObject();
        JSONObject env=new JSONObject();
        env.put("browser",true);
        parent.put("env",env);
        return parent;
    }

    public static void main(String[] args) {
//        System.out.println(getTemplate("Hey there",true,"My hair"));

        launch(args);
    }

    public static String getTemplate(String description,Boolean recommended,String category){
        TemplateBuilderEngine engine=new TemplateBuilderEngine();
        String module="module.exports={";
        String meta=engine.generateJSONObject("\tmeta");
        String docs=engine.generateJSONObject("\t\tdocs");
        docs=engine.addChild(docs,engine.generateJSONObject("\t\t\tdescription",description)+",");
        docs=engine.addChild(docs,engine.generateJSONObject("\t\t\tcategory",category)+",");
        docs=engine.addChild(docs,engine.generateJSONObject("\t\t\trecommended",recommended));
        docs=engine.finalizeObjectorFunction(docs+"\n\t\t");
        meta=engine.addChild(meta,docs);
        meta=engine.addChild(meta+",",engine.generateJSarray("\t\tschema"));
        meta=engine.finalizeObjectorFunction(meta+"\n\t");

        module=engine.addChild(module,meta);

        String function=engine.createJSFunction("\tcreate","context");
        String returnString="\t\t"+engine.returnJS();
        returnString=engine.addComment(returnString+"\n\t\t\t","Add your whole code here");
        returnString=engine.finalizeObjectorFunction(returnString+"\n\t\t");
        function=engine.addChild(function,returnString);
        function=engine.finalizeObjectorFunction(function+"\n\t");

        module=engine.addChild(module+",",function);
        module=engine.finalizeObjectorFunction(module+"\n");

        return engine.generateTemplate(module);

    }
}
