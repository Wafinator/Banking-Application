package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
    This gui will allow the user to login or launch the register gui
    This extends from the BaseFrame which means we will need to define our own addGuiComponent()
 */
public class LoginGui extends BaseFrame {
    public LoginGui(){
        super("Banking App Login");
    }


    @Override
    protected void addGuiComponents(){
        //creating banking app label
        JLabel bankingAppLabel =new JLabel("Banking Application");

        //set the location and the size of the gui component
        bankingAppLabel.setBounds(0,20,super.getWidth(),40);

        //change the font style
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD,32));

        //center text in JLabel
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //add to gui
        add(bankingAppLabel);

        //username label
        JLabel usernameLabel = new JLabel("Username:");


        // getWidth() returns the width of our frame which is about 420
        usernameLabel.setBounds(20,120,getWidth()-30,24);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(usernameLabel);

        //create username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,160,getWidth()-50,40);
        usernameField.setFont(new Font("Dialog",Font.PLAIN,20));
        add(usernameField);

        //password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20,250,getWidth()-30,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(passwordLabel);

        //create password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,290,getWidth()-50,40);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,20));
        add(passwordField);

        //create login button
        JButton LoginButton = new JButton("Login");
        LoginButton.setBounds(20,460,getWidth()-50,40);
        LoginButton.setFont(new Font("Dialog",Font.BOLD,20));
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get username
                String username = usernameField.getText();

                //get password
                String password = String.valueOf(passwordField.getPassword());

                //validate login
                User user = MyJDBC.validateLogin(username, password);

                // if user is null it means invalid otherwise it is a valid account
                if(user!=null){
                    //means valid login

                    //dispose this gui
                    LoginGui.this.dispose();

                    //launch bank app gui
                    BankingAppGui bankingAppGui = new BankingAppGui(user);
                    bankingAppGui.setVisible(true);

                    //show success dialog
                    JOptionPane.showMessageDialog(bankingAppGui, "Login Successful!");
                }else{
                    //invalid login
                    JOptionPane.showMessageDialog(LoginGui.this, "Login Failed!");
                }
            }
        });


        add(LoginButton);

        //create register label
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0,510,getWidth()-10,30);
        registerLabel.setFont(new Font("Dialog",Font.PLAIN,18));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //adds an event listener so when
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //dispose of this gui
                LoginGui.this.dispose();

                //launch the register gui
                new RegisterGui().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
