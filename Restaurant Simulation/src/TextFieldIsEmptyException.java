import javax.swing.*;

public class TextFieldIsEmptyException extends Exception{
    public void printException(){
        JDialog jDialog = new JDialog();
        JLabel jLabel = new JLabel("Error:One or more text Filed is empty.");
        jDialog.setBounds(50,200,400,100);
        jDialog.add(jLabel);
        jDialog.setVisible(true);
    }
}
