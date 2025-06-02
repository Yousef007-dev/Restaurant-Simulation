import javax.swing.*;
import java.io.Serializable;

public class Meal implements Serializable {

    static final long serialVersionUID = 1L;
    String mealImagePath;
    String mealName;
    String mealComponents;
    double maelPrise;

    Meal(String mealImagePath,String mealComponents,String mealName,double maelPrise){
        this.maelPrise=maelPrise;
        this.mealComponents= mealComponents;
        this.mealImagePath= mealImagePath;
        this.mealName= mealName;
    }

    void editMeal(String mealComponents,double maelPrise){
        this.maelPrise=maelPrise;
        this.mealComponents= mealComponents;
    }
}