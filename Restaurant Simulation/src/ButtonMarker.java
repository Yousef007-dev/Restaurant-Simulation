import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonMarker {
    public static JButton ButtonMaker(int X_Bounds,int Y_Bounds,int W_Bounds,int H_Bounds,String titel,Color color) {
        JButton button = new JButton() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(color);
                g2d.fillOval(0, 0, getWidth(), getHeight());

                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth = fm.stringWidth(titel);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2;

                g2d.drawString(titel, x, y);
            }
        };
        button.setBounds(X_Bounds, Y_Bounds, W_Bounds, H_Bounds);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBounds(X_Bounds-30, Y_Bounds, W_Bounds+60, H_Bounds);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBounds(X_Bounds, Y_Bounds, W_Bounds, H_Bounds);
            }
        });
        button.setVisible(true);
        return button;
    }
}
