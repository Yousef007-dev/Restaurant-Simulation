import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.*;


public class ReportFrame extends JFrame {
    HashMap<Meal,Integer> mostOrderedMealAtAllTime = new HashMap<>();
    HashMap<String ,Integer> mostPersonHashMap = new HashMap<>();
    InputStream input= null;
    ReportFrame(){


        try {
            input= new FileInputStream("MostOrderedMeal");
            ObjectInputStream ois = new ObjectInputStream(input);
            mostOrderedMealAtAllTime = (HashMap<Meal, Integer>) ois.readObject();
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
        catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }


        setSize(500,500);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        JLabel numberOfOrdersString = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Number Of Orders";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        numberOfOrdersString.setBounds(20,30,200,50);
        JLabel numberOfOrdersAsNumber = new JLabel(String.valueOf(WelcomeLabel.lastorder.size()));
        numberOfOrdersAsNumber.setBounds(250,30,100,50);




        JLabel mostOrderedMeal = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Most Ordered Meal";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        mostOrderedMeal.setBounds(20,90,200,50);
        int HighestMealInt = Collections.max(mostOrderedMealAtAllTime.values());
        Meal HighestMeal=null;
        for (Map.Entry<Meal, Integer> meal:
        mostOrderedMealAtAllTime.entrySet()){
            if (meal.getValue() == HighestMealInt){
                HighestMeal=meal.getKey();
            }
        }
        JLabel mostOrderedMealNumber = new JLabel(HighestMeal.mealName);
        mostOrderedMealNumber.setBounds(250,90,100,50);



        JLabel profit = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "The profit";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        profit.setBounds(20,150,200,50);
        JLabel profitNumber = new JLabel(String.valueOf(WelcomeLabel.totalincoming));
        profitNumber.setBounds(250,150,100,50);

        JLabel MostPerson = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Top Customer";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        MostPerson.setBounds(20,210,200,50);
        try {
            input= new FileInputStream("mostPerson");
            ObjectInputStream ois = new ObjectInputStream(input);
            mostPersonHashMap = (HashMap<String, Integer>) ois.readObject();
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
        catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
        String personName=null;
        int HighestPerson = Collections.max(mostPersonHashMap.values());
        for (Map.Entry<String ,Integer>  person:
             mostPersonHashMap.entrySet()) {
            if (HighestPerson == person.getValue()){
                personName = person.getKey();
            }

        }
        JLabel NameOfThePersonWhoVisitsTheMost = new JLabel(personName);
        NameOfThePersonWhoVisitsTheMost.setBounds(250,210,100,50);


        add(NameOfThePersonWhoVisitsTheMost);
        add(MostPerson);
        add(profitNumber);
        add(profit);
        add(mostOrderedMealNumber);
        add(mostOrderedMeal);
        add(numberOfOrdersAsNumber);
        add(numberOfOrdersString);


        repaint();
        revalidate();

    }
}
