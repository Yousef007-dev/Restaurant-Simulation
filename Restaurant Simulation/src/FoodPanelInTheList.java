import javax.swing.*;
import java.awt.*;

class FoodPanelInTheList extends JPanel {


    Meal meal;
    public FoodPanelInTheList(Meal meal) {
        this.meal=meal;
        setLayout(null);
        setPreferredSize(new Dimension(400, 200));



        // Food Name Label
        JLabel foodNameLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250, 150, 0));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);

                g2d.setColor(Color.white);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(meal.mealName);
                int textHeight = fm.getHeight();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + 15;

                g2d.drawString(meal.mealName, x, y);
            }
        };
        foodNameLabel.setBounds(30, 75, 150, 50);

        // Food Photo Label
        ImageIcon imageicon=new ImageIcon(meal.mealImagePath);
        JLabel foodPhotoLabel = new JLabel();
        foodPhotoLabel.setIcon(imageicon);
        foodPhotoLabel.setBounds(200,10,200,180);

        add(foodPhotoLabel);
        add(foodNameLabel);

        repaint();
        revalidate();
    }

    public Meal getMeal() {
        return meal;
    }
}