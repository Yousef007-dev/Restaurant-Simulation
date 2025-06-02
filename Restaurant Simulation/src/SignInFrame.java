import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class SignInFrame extends JFrame{

    static int buttonFontSize = 20;
    private OutputStream output = null;
    public SignInFrame(HashMap<String ,String> accounts){


        setSize(500,700);
        setTitle("Sing in widow ");
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

        JTextField nameTextField = new JTextField("")/*;{
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.drawRoundRect(0,0,getWidth(),getHeight(),100,100);
            }

        }*/;


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


        JButton singInButtonInTheLogInFrame = new JButton(){
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
                String text = "Sign up";
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y);

            }

        };

        singInButtonInTheLogInFrame.setBounds(150,frameHeight/2,200,50);
        singInButtonInTheLogInFrame.setBorderPainted(false);
        singInButtonInTheLogInFrame.setContentAreaFilled(false);
        singInButtonInTheLogInFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                singInButtonInTheLogInFrame.setBounds(125,frameHeight/2 -25,250,100);
                buttonFontSize *=2;
                singInButtonInTheLogInFrame.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                singInButtonInTheLogInFrame.setBounds(150,frameHeight/2,200,50);
                buttonFontSize/=2;
                singInButtonInTheLogInFrame.repaint();
            }
        });

        singInButtonInTheLogInFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonFontSize =20;
                singInButtonInTheLogInFrame.repaint();
                String passWord = new String(passwordField.getPassword());
                String name = nameTextField.getText();
                if (accounts.get(passWord) != null) {
                    if (accounts.get(passWord).equals(name)){
                        JDialog jDialog = new JDialog();
                        jDialog.setTitle("Error");
                        jDialog.setBounds(50,200,400,200);
                        JLabel notValidLabel = new JLabel("not valid username or password try something else");
                        jDialog.add(notValidLabel);
                        jDialog.setVisible(true);
                    }
                    else {
                        accounts.put(passWord,name);
                        try {
                            output = new FileOutputStream("accounts");
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
                            objectOutputStream.writeObject(accounts);
                            objectOutputStream.flush();
                            objectOutputStream.close();
                        }catch (IOException ex){
                            System.out.println(ex.getMessage());
                        }finally {
                            try {
                                output.close();
                            }catch (IOException ioException){
                                System.out.println(ioException.getMessage());
                            }
                        }
                    }
                    CustomerFoodWindow customerFoodWindow = new CustomerFoodWindow();
                    customerFoodWindow.setVisible(true);
                    dispose();
                }
                else {
                    accounts.put(passWord,name);
                    try {
                        output = new FileOutputStream("accounts");
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
                        objectOutputStream.writeObject(accounts);
                        objectOutputStream.flush();
                        objectOutputStream.close();
                    }catch (IOException ex){
                        System.out.println(ex.getMessage());
                    }finally {
                        try {
                            output.close();
                        }catch (IOException ioException){
                            System.out.println(ioException.getMessage());
                        }
                    }
                    CustomerFoodWindow customerFoodWindow = new CustomerFoodWindow();
                    customerFoodWindow.setVisible(true);
                    dispose();
                }


                //go to customer widow form the guys & don't forget the "setVisible(false)"
            }
        });




        //add component
        add(nameLabel);
        add(nameTextField);
        add(passWordLabel);
        add(passwordField);
        add(singInButtonInTheLogInFrame);
    }

}