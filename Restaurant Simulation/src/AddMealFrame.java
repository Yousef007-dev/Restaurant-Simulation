import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class AddMealFrame extends JFrame {
    String mealImagePath;
    String mealName;
    String mealComponents;
    double maelPrice;
    List<Meal> mealsAvailable = new ArrayList<>();
    InputStream input = null;

    AddMealFrame (){
        try {
            input = new FileInputStream("Meals");
            ObjectInputStream ois = new ObjectInputStream(input);
            mealsAvailable = (List<Meal>) ois.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        setSize(500,500);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        JLabel imagePathLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Image Path";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        imagePathLabel.setBounds(20,30,200,50);
        JTextField imagePathTextField = new JTextField();
        imagePathTextField.setBounds(230,30,200,50);


        JLabel mealNameLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Name of the meal";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        mealNameLabel.setBounds(20,90,200,50);
        JTextField mealNameTextField = new JTextField();
        mealNameTextField.setBounds(230,90,200,50);

        JLabel ingredientsLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Meal's Ingredients";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        ingredientsLabel.setBounds(20,150,200,50);
        JTextField ingredientsTextField = new JTextField();
        ingredientsTextField.setBounds(230,150,200,50);


        JLabel mealPriceLabel = new JLabel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Meal's price";

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight= fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() - textHeight)/2 +15;

                g2d.drawString(text,x,y);
            }
        };
        mealPriceLabel.setBounds(20,210,200,50);
        JTextField mealPriceTextField = new JTextField();
        mealPriceTextField.setBounds(230,210,200,50);

        JButton addMealButton = ButtonMarker.ButtonMaker(90,300,300,75,"Add The Meal",new Color(53, 168, 7));
        addMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ImageIcon imageIcon = null;
                try {
                    mealName = mealNameTextField.getText();
                    mealComponents = ingredientsTextField.getText();
                    mealImagePath = imagePathTextField.getText();
                    imageIcon = new ImageIcon(mealImagePath);
                    if (imageIcon == null) {
                        throw new ImageNotFoundException();
                    }
                    if (mealName.isBlank() || mealComponents.isBlank() || mealImagePath.isBlank()) {
                        throw new TextFieldIsEmptyException();
                    }
                    maelPrice = Double.parseDouble(mealPriceTextField.getText());
                    if (maelPrice == 0.0) {
                        throw new NumberFormatException();
                    }
                } catch (TextFieldIsEmptyException textFieldIsEmptyException) {
                    textFieldIsEmptyException.printException();
                } catch (ImageNotFoundException imageNotFoundException) {
                    imageNotFoundException.printException();
                } catch (NumberFormatException exception) {
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("Error:The Price Field is empty or invalid input");
                    jDialog.setBounds(50, 200, 400, 100);
                    jDialog.add(jLabel);
                    jDialog.setVisible(true);
                }

                if (mealName.isBlank() != true && mealComponents.isBlank()!=true&& imageIcon != null && maelPrice != 0.0) {
                    mealsAvailable.add(new Meal(mealImagePath, mealComponents, mealName, maelPrice));
                    OutputStream out = null;
                    try {

                        out = new FileOutputStream("Meals");
                        ObjectOutputStream oos = new ObjectOutputStream(out);
                        oos.writeObject(mealsAvailable);
                    } catch (IOException exception) {
                        System.out.println(exception.getMessage());
                    } finally {
                        try {
                            out.close();
                        } catch (IOException e1) {
                            e1.getStackTrace();
                        }
                    }
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("Meal saved");
                    jDialog.setBounds(50, 200, 400, 100);
                    jDialog.add(jLabel);
                    jDialog.setVisible(true);
                    dispose();
                }
            }
        });

        //adding components
        add(imagePathLabel);
        add(imagePathTextField);

        add(mealNameTextField);
        add(mealNameLabel);

        add(ingredientsLabel);
        add(ingredientsTextField);

        add(mealPriceLabel);
        add(mealPriceTextField);
        add(addMealButton);

        repaint();
        revalidate();

    }
}
