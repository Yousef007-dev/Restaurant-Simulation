import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMealInfo extends JFrame {

    CustomerMealInfo(Meal meal,List <Meal> order){
        setSize(500,700);
        setTitle("Window of the food");
        setResizable(false);
        setLayout(null);
        setVisible(true);

        JLabel foodPhotoLabel = new JLabel(new ImageIcon(meal.mealImagePath));
        foodPhotoLabel.setBounds(40, 10, 400, 200);

        JButton back=Maker.ButtonMaker(80,600,100,50,"Back!",new Color(168, 7, 7));
        JButton AddButton=Maker.ButtonMaker(300,600,100,50,"Add to cart!",new Color(53, 168, 7));

        back.addActionListener(e -> {this.dispose();});

        AddButton.addActionListener(e -> {
            order.add(meal);
            this.dispose();
        });

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

        JLabel componentsTextField = new JLabel(meal.mealComponents);// add the components here
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
        JLabel priceTextField = new JLabel(String.valueOf(meal.maelPrise));// add the price here
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


        // addComponent
        add(foodPhotoLabel);
        add(back);//When you save the changes send the content of the txt fields to file the saves their values
        add(AddButton);
        add(priceTextField);
        add(priseLabel);
        add(componentsLabel);
        add(componentsTextField);
        add(nameLabel);



        repaint();
        revalidate();
    }
}
