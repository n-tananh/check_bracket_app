/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstapp;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 
public class CheckBracketApp {
 
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    
    //Contructor
    public CheckBracketApp(){
       prepareGUI();
    }
    
    //Main
    public static void main(String[] args) {
        CheckBracketApp app = new CheckBracketApp();
        app.showText();
    }
     
    //prepare GUI
    private void prepareGUI() {
        mainFrame = new JFrame("Bracket Balanced");
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    
    //checking bracket method
    public static boolean check_bracket(String s){
        //Initialize 1 stack called stack
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if ((chr == '[') || (chr == '(') || (chr == '{')) {
                stack.push(chr);
            }else if (chr == ']'){
                if(stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }else if (chr == ')'){
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }       
            }else if (chr == '}'){
                if(stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }   
        }
        return stack.empty();
    }
    
    private void showText() {
        headerLabel.setText("Program checking the bracket in your code");
        
        JLabel inputLabel = new JLabel("Input area: ", JLabel.RIGHT);
        
        final JTextArea inputTextArea = new JTextArea(
                "Enter your code here !!!" , 5, 20);
        
        JScrollPane scrollPane = new JScrollPane(inputTextArea);
        JButton checkButton = new JButton("Check");
        
        checkButton.addActionListener((ActionEvent e) -> {
            
            String input = inputTextArea.getText();
            
            if (check_bracket(input)) {
                statusLabel.setText("Bracket balenced !");
            }else{
                statusLabel.setText("Bracket not balenced !");
            }
             
        });
        
        controlPanel.add(inputLabel);
        controlPanel.add(scrollPane);
        controlPanel.add(checkButton);
        mainFrame.setVisible(true);
    }
}