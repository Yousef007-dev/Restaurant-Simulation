import javax.swing.*;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Frame extends JFrame {

    static int logInFontSize=16;
    static int signInFontSize=16;

    public HashMap<String ,String> accountsHashMap = new HashMap<>();



    InputStream input=null;
    OutputStream output= null;


    public Frame() {

        try {
            input = new FileInputStream("accounts");
            ObjectInputStream ois = new ObjectInputStream(input);
            accountsHashMap = (HashMap) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        accountsHashMap.put("0000", "Admin");


        setSize(500, 700);
        setTitle("Welcome Frame");
        int frameWidth = getWidth();
        int frameHight = getHeight();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Set layout to null before adding components
        setResizable(false);
        // Welcome Label
        JLabel welcomeLabel = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Singular, for proper rendering
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw an oval
                g2d.setColor(new Color(250, 150, 0));
                g2d.fillOval(0, 0, getWidth(), getHeight());

                // Draw text
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                g2d.drawString("Welcome To Our Restaurant", 60, 30);
            }
        };
        welcomeLabel.setBounds(frameWidth / 8, frameHight / 8, frameWidth / 2 + 100, 50); // Set bounds
        welcomeLabel.setOpaque(false); // Ensure background doesn't interfere

        // log-In Button

        JButton logInButton = new JButton() {

            @Override
            protected void paintComponent(Graphics g) {
                // Properly render default button components (background, etc.)
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fill with a custom rounded rectangle
                g2d.setColor(new Color(250, 150, 0));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);

                // Set text color and font
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, logInFontSize));

                // Draw the custom text
                String text = "Log in";
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                // Calculate x and y for centered text
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2; // Adjust for baseline
                g2d.drawString(text, x, y);
            }
        };
        logInButton.setBounds(frameWidth / 8, frameHight - frameHight / 6, frameWidth / 2 + 100, 50); // Set bounds
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logInButton.setBounds(frameWidth / 8 - 25, frameHight - 25 - frameHight / 6, frameWidth / 2 + 150, 100);
                logInFontSize = 2 * logInFontSize;
                logInButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logInButton.setBounds(frameWidth / 8, frameHight - frameHight / 6, frameWidth / 2 + 100, 50); // Set bounds
                logInFontSize = logInFontSize / 2;
                logInButton.repaint();
            }
        });

        // very important to make the button without edges
        logInButton.setOpaque(false);
        logInButton.setFocusPainted(false);
        logInButton.setBorderPainted(false);// Erases the edges
        logInButton.setContentAreaFilled(false);// Erases the unused area

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInFrame s = new LogInFrame(accountsHashMap);
                s.setVisible(true);
            }
        });

        JButton signInButton = new JButton() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250, 150, 0));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);

                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, signInFontSize));

                FontMetrics fm = g2d.getFontMetrics();

                String text = "Sign up";

                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2;

                g2d.drawString(text, x, y);
            }

        };

        signInButton.setBounds(frameWidth / 8, frameHight - (2 * frameHight / 6), frameWidth / 2 + 100, 50);//frameHight-70 -(frameHight/6)
        signInButton.setBorderPainted(false);
        signInButton.setContentAreaFilled(false);

        signInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Enlarge the button when the mouse enters
                signInButton.setBounds(frameWidth / 8 - 25, frameHight - (2 * frameHight / 6) - 25, frameWidth / 2 + 150, 100);
                signInFontSize = 2 * signInFontSize;
                signInButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore the button to its original size when the mouse exits
                signInButton.setBounds(frameWidth / 8, frameHight - (2 * frameHight / 6), frameWidth / 2 + 100, 50);
                signInFontSize = signInFontSize / 2;
                signInButton.repaint();
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInFrame s = new SignInFrame(accountsHashMap);
                s.setVisible(true);
            }
        });


        // Add components to the frame
        add(welcomeLabel);
        add(logInButton);
        add(signInButton);

        setVisible(true);
    }


}