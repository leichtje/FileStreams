import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Double.parseDouble;

public class RandProductMaker {
    public static void main(String[] args) {
        RandProductMakerFrame frame = new RandProductMakerFrame();
        frame.setVisible(true);
    }

    public static class RandProductMakerFrame extends JFrame {
        JPanel mainPnl, titlePnl, inputPnl, btnPnl;
        JLabel titleLabel, nameLabel, descriptionLabel, idLabel, costLabel, countLabel;
        JTextField nameTxt, descriptionTxt, idText, costText, countText;
        JButton addButton, quitButton;

        ActionListener quit = new quitListener();
        ActionListener add = new addListener();

        String name, description, id, outputString;
        double cost;
        int count;

        RandProductMakerFrame() {
            setTitle("Random Product Maker");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenHeight = screenSize.height;
            int screenWidth = screenSize.width;
            setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
            setLocationRelativeTo(null);
            setResizable(false);


            mainPnl = new JPanel();
            titlePnl = new JPanel();
            inputPnl = new JPanel();
            btnPnl = new JPanel();

            titleLabel = new JLabel("Product Maker");
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
            nameLabel = new JLabel("Product Name:");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            descriptionLabel = new JLabel("Description:");
            descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            idLabel = new JLabel("Product ID:");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            costLabel = new JLabel("Product Cost:");
            costLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            countLabel = new JLabel("Records Added:");
            countLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            nameTxt = new JTextField();
            nameTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
            descriptionTxt = new JTextField();
            descriptionTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
            idText = new JTextField();
            idText.setFont(new Font("Times New Roman", Font.BOLD, 15));
            costText = new JTextField();
            costText.setFont(new Font("Times New Roman", Font.BOLD, 15));
            countText = new JTextField();
            countText.setFont(new Font("Times New Roman", Font.BOLD, 15));

            addButton = new JButton("Add");
            addButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            addButton.addActionListener(add);
            quitButton = new JButton("Quit");
            quitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            quitButton.addActionListener(quit);


            add(mainPnl);
            mainPnl.setLayout(new GridLayout(3,1));

            mainPnl.add(titlePnl);
            titlePnl.add(titleLabel);

            mainPnl.add(inputPnl);
            inputPnl.setLayout(new GridLayout(5,2));
            inputPnl.add(nameLabel);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            inputPnl.add(nameTxt);
            inputPnl.add(descriptionLabel);
            descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
            inputPnl.add(descriptionTxt);
            inputPnl.add(idLabel);
            idLabel.setHorizontalAlignment(JLabel.CENTER);
            inputPnl.add(idText);
            inputPnl.add(costLabel);
            costLabel.setHorizontalAlignment(JLabel.CENTER);
            inputPnl.add(costText);
            inputPnl.add(countLabel);
            countLabel.setHorizontalAlignment(JLabel.CENTER);
            inputPnl.add(countText);
            countText.setEditable(false);
            countText.setFont(new Font("Times New Roman", Font.BOLD, 15));
            countText.setText(String.valueOf(count));

            mainPnl.add(btnPnl);
            btnPnl.add(addButton);
            btnPnl.add(quitButton);
        }

        private class addListener implements ActionListener
        {
            public void actionPerformed(ActionEvent AE)
            {
                if(!(nameTxt.getText().equals("")) &&
                        !(descriptionTxt.getText().equals("")) &&
                        !(idText.getText().equals("")) &&
                        !(costText.getText().equals("")))
                {
                    if((nameTxt.getText().length() <= 35) &&
                            (descriptionTxt.getText().length() <= 75) &&
                            (idText.getText().length() <= 6))
                    {
                        name = nameTxt.getText();
                        description = descriptionTxt.getText();
                        id = idText.getText();
                        cost = parseDouble(costText.getText());

                        outputString = String.format("\n%-6s %-35s %-75s  %.2f", id, name, description, cost);

                        File workingDirectory = new File(System.getProperty("user.dir"));
                        Path file = Paths.get(workingDirectory.getPath() + "\\ProductsCreation.txt");
                        try
                        {
                            RandomAccessFile outFile = new RandomAccessFile(file.toString(), "rw");
                            outFile.seek(outFile.length());
                            outFile.write(outputString.getBytes());
                            outFile.close();
                            JOptionPane.showMessageDialog(null,"Product Written to File");
                        }
                        catch (FileNotFoundException e) {
                            System.out.println("File not found.");
                            e.printStackTrace();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        nameTxt.setText("");
                        descriptionTxt.setText("");
                        idText.setText("");
                        costText.setText("");
                        count++;
                        countText.setText(String.valueOf(count));
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please check that the information entered fits into the fields based on the following:\nName: 35 Characters\nDescription: 75 Characters\nID: 6 Characters");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please do not leave any fields empty.");
                }
            }
        }

        public static class quitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent AE)
            {
                System.exit(0);
            }
        }
    }
}
