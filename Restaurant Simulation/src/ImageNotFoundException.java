import javax.swing.*;

public class ImageNotFoundException extends Exception{
    public void printException(){
        JDialog jDialog = new JDialog();
        JLabel jLabel = new JLabel("Error:Image Not Found");
        jDialog.setBounds(50,200,400,100);
        jDialog.add(jLabel);
        jDialog.setVisible(true);
    }
}
