import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.*;
import java.util.List;

public class FoodSelectedToEdit extends JFrame {


    FoodSelectedToEdit(List<Meal> mealsAvailable, Meal meal){


        setSize(500,700);
        setTitle("Window of the food");
        setResizable(false);
        setLayout(null);
        setVisible(true);

        JLabel foodPhotoLabel = new JLabel(new ImageIcon(meal.mealImagePath));
        foodPhotoLabel.setBounds(40, 10, 400, 200);

        JButton saveButton = new JButton(){
          protected void paintComponent(Graphics g){
              super.paintComponent(g);

              Graphics2D g2d = (Graphics2D) g;
              g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

              g2d.setColor(new Color(53, 168, 7));
              g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

              g2d.setColor( Color.WHITE);
              g2d.setFont(new Font("Arial",Font.BOLD,16));
              FontMetrics fm = g2d.getFontMetrics();

              int textHeight = fm.getHeight();
              int textWidth = fm.stringWidth("Save");

              int x = (getWidth() - textWidth)/2;
              int y = (getHeight() + textHeight)/2 -5;

              g2d.drawString("Save",x,y);
          }
        };

        saveButton.setBounds(80,600,100,50);
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveButton.setBounds(50,600,160,50);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setBounds(80,600,100,50);
            }
        });

        saveButton.setBorderPainted(false);
        saveButton.setContentAreaFilled(false);

        JButton deleteButton = new JButton(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(168, 7, 7));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor( Color.WHITE);
                g2d.setFont(new Font("Arial",Font.BOLD,16));
                FontMetrics fm = g2d.getFontMetrics();

                int textHeight = fm.getHeight();
                int textWidth = fm.stringWidth("Delete");

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2 -5;

                g2d.drawString("Delete",x,y);
            }
        };

        deleteButton.setBounds(300,600,100,50);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBounds(270,600,160,50);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBounds(300,600,100,50);
            }
        });
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);

        JLabel componentsLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "The Components";

                g2d.setFont(new Font("Arial",Font.BOLD,10));
                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y);

            }
        };
        componentsLabel.setBounds(10,350,100,50);

        JTextField componentsTextField = new JTextField(meal.mealComponents);// add the components here
        componentsTextField.setBounds(120,350,350,50);

        JLabel priseLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "The Praise";

                g2d.setFont(new Font("Arial",Font.BOLD,10));
                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y);

            }
        };

        priseLabel.setBounds(10,450,100,50);
        JTextField priceTextField = new JTextField(String.valueOf(meal.maelPrise));// add the price here
        priceTextField.setBounds(120,450,350,50);

        JLabel nameLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillOval(0,0,getWidth(),getHeight());

                g2d.setColor(Color.WHITE);

                g2d.setFont(new Font("Arial",Font.BOLD,40));
                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(meal.mealName);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2 -10;

                g2d.drawString(meal.mealName,x,y);
            }
        };

        nameLabel.setBounds(130,240,250,80);



        // add the actions

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (componentsTextField.getText().isBlank()){
                        throw new TextFieldIsEmptyException();
                    }
                    if (Double.parseDouble(priceTextField.getText()) ==0.0){
                        throw new NumberFormatException();
                    }
                }catch (TextFieldIsEmptyException textFieldIsEmptyException){
                    textFieldIsEmptyException.printException();
                }catch (NumberFormatException numberFormatException){
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("Error:The Price Field is empty or invalid input");
                    jDialog.setBounds(50, 200, 400, 100);
                    jDialog.add(jLabel);
                    jDialog.setVisible(true);
                }
                if (componentsTextField.getText().isBlank()!=true && Double.parseDouble(priceTextField.getText()) !=0.0 )
                {mealsAvailable.remove(meal);
                meal.editMeal(componentsTextField.getText(),Double.parseDouble(priceTextField.getText()));
                mealsAvailable.add(meal);
                OutputStream out =null;
                try {

                    out = new FileOutputStream("Meals");
                    ObjectOutputStream oos = new ObjectOutputStream(out);
                    oos.writeObject(mealsAvailable);
                }
                catch (IOException exception){
                    System.out.println(exception.getMessage());
                }
                finally {
                    try {
                        out.close();
                    }catch (IOException e1){
                        e1.getStackTrace();
                    }
                }

                JDialog savedDialog= new JDialog();
                savedDialog.setBounds(100,300,300,100);
                JLabel saveTextLabel = new JLabel("Change Saved ");
                savedDialog.add(saveTextLabel);
                savedDialog.setVisible(true);
                setVisible(false);
            }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mealsAvailable.remove(meal);
                OutputStream out =null;
                try {

                    out = new FileOutputStream("Meals");
                    ObjectOutputStream oos = new ObjectOutputStream(out);
                    oos.writeObject(mealsAvailable);
                }
                catch (IOException exception){
                    System.out.println(exception.getMessage());
                }
                finally {
                    try {
                        out.close();
                    }catch (IOException e1){
                        e1.getStackTrace();
                    }
                }

                JDialog deletedDialog= new JDialog();
                deletedDialog.setBounds(100,300,300,100);
                JLabel deleteTextLabel = new JLabel("meal deleted ");
                deletedDialog.add(deleteTextLabel);
                deleteTextLabel.setVisible(true);
                dispose();

            }
        });

        // addComponent
        add(foodPhotoLabel);
        add(saveButton);// when you save the changes send the content of the txt fields to file the saves their values
        add(deleteButton);
        add(priceTextField);
        add(priseLabel);
        add(componentsLabel);
        add(componentsTextField);
        add(nameLabel);



        repaint();
        revalidate();
    }
}
