import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class LogInFrame extends JFrame{
    static int buttonFontSize = 20;

    public LogInFrame(HashMap<String ,String> accounts){


        setSize(500,700);
        setTitle("Log in widow ");
        setLayout(null);
        int frameHeight = getHeight();

        JLabel nameLabel = new JLabel(){

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillOval(0,0,getWidth(),getHeight());

                g2d.setColor(Color.WHITE);

                g2d.setFont(new Font("Arial",Font.BOLD,16));

                FontMetrics fm = g2d.getFontMetrics();

                String text = "Name";

                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2 -3;

                g2d.drawString(text,x,y);

            }

        };

        nameLabel.setBounds(30,frameHeight/4 -15,100,30);

        JTextField nameTextField = new JTextField("");


        nameTextField.setBounds(150,160,300,30);

        JLabel passWordLabel = new JLabel(){

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillOval(0,0,getWidth(),getHeight());

                g2d.setColor(Color.WHITE);

                g2d.setFont(new Font("Arial",Font.BOLD,15));

                FontMetrics fm = g2d.getFontMetrics();

                String text = "PassWord";

                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2 -3;

                g2d.drawString(text,x,y);

            }

        };

        passWordLabel.setBounds(30,frameHeight/2 -100,100,30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150,frameHeight/2 -100,300,30);


        JButton logInButtonInTheLogInFrame = new JButton(){

          @Override
            protected void paintComponent(Graphics g){
              super.paintComponent ( g);

              Graphics2D g2d = (Graphics2D) g;
              g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

              g2d.setColor(new Color(250,150,0));
              g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

              g2d.setColor(Color.WHITE);
              g2d.setFont(new Font("Arial",Font.BOLD,buttonFontSize));

              FontMetrics fm = g2d.getFontMetrics();
              String text = "Log in";
              int textWidth = fm.stringWidth(text);
              int textHeight = fm.getAscent();

              int x = (getWidth() - textWidth)/2;
              int y = (getHeight() + textHeight)/2;

              g2d.drawString(text,x,y);

          }

        };

        logInButtonInTheLogInFrame.setBounds(150,frameHeight/2,200,50);
        logInButtonInTheLogInFrame.setBorderPainted(false);
        logInButtonInTheLogInFrame.setContentAreaFilled(false);
        logInButtonInTheLogInFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logInButtonInTheLogInFrame.setBounds(125,frameHeight/2 -25,250,100);
                buttonFontSize *=2;
                logInButtonInTheLogInFrame.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logInButtonInTheLogInFrame.setBounds(150,frameHeight/2,200,50);
                buttonFontSize/=2;
                logInButtonInTheLogInFrame.repaint();
            }
        });

        logInButtonInTheLogInFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonFontSize /=2;
                logInButtonInTheLogInFrame.repaint();
                String  passWord = new String(passwordField.getPassword());
                String Name = nameTextField.getText();


                if (accounts.get(passWord) !=null)
                {
                    if (accounts.get(passWord).equals(Name)){
                        //goes to the adminFrame
                        if (passWord.equals("0000")&&Name.equals("Admin")) {
                            ManagerFrame managerFrame = new ManagerFrame();
                            managerFrame.setVisible(true);
                            dispose();
                        }
                        //goes to the custumar
                        else {
                            CustomerFoodWindow customerFoodWindow = new CustomerFoodWindow();
                            customerFoodWindow.setVisible(true);
                            dispose();
                        }

                    }
                    else {
                        JDialog jDialog = new JDialog();
                        jDialog.setTitle("Error");
                        jDialog.setLayout(null);
                        jDialog.setBounds(50,200,400,200);
                        JLabel noSuchInfo = new JLabel("No such account.");
                        noSuchInfo.setBounds(10,20,300,70);
                        JButton signUp = new JButton("Sign up");
                        signUp.setBounds(140,110,100,20);
                        signUp.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                SignInFrame signInFrame = new SignInFrame(accounts);
                                signInFrame.setVisible(true);
                                setVisible(false);
                                jDialog.setVisible(false);
                            }
                        });
                        jDialog.add(noSuchInfo);
                        jDialog.add(signUp);
                        jDialog.setVisible(true);
                    }
                }
                else {
                    JDialog jDialog = new JDialog();
                    jDialog.setTitle("Error");
                    jDialog.setLayout(null);
                    jDialog.setBounds(50,200,400,200);
                    JLabel noSuchInfo = new JLabel("No such account.");
                    noSuchInfo.setBounds(10,20,300,70);
                    JButton signUp = new JButton("Sign up");
                    signUp.setBounds(140,110,100,20);
                    signUp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            SignInFrame signInFrame = new SignInFrame(accounts);
                            signInFrame.setVisible(true);
                            setVisible(false);
                            jDialog.setVisible(false);
                        }
                    });
                    jDialog.add(noSuchInfo);
                    jDialog.add(signUp);
                    jDialog.setVisible(true);
                }



            }
        });




        //add component
        add(nameLabel);
        add(nameTextField);
        add(passWordLabel);
        add(passwordField);
        add(logInButtonInTheLogInFrame);
    }

}