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

public class RandProductSearch {
    public static void main(String[] args) {
        RandProductSearchFrame frame = new RandProductSearchFrame();
        frame.setVisible(true);
    }

    public static class RandProductSearchFrame extends JFrame {
        JPanel mainPnl, titlePnl, inputPnl, displayPnl, btnPnl;
        JLabel titleLabel, inputLabel;
        JTextField inputTxtFld;
        JTextArea displayTxtArea;
        JScrollPane displayScroll;
        JButton searchBtn, quitBtn;
        ActionListener quit = new quitListener();
        ActionListener search = new searchListener();
        String input, display;

        RandProductSearchFrame() {
            setTitle("Product Search");
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenHeight = screenSize.height;
            int screenWidth = screenSize.width;
            setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mainPnl = new JPanel();
            titlePnl = new JPanel();
            inputPnl = new JPanel();
            displayPnl = new JPanel();
            btnPnl = new JPanel();
            titleLabel = new JLabel("Random Product Search");
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
            inputLabel = new JLabel("Product Search :");
            inputLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
            inputTxtFld = new JTextField();
            displayTxtArea = new JTextArea(6, 75);
            displayScroll = new JScrollPane(displayTxtArea);


            searchBtn = new JButton("Search");
            searchBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            searchBtn.addActionListener(search);
            quitBtn = new JButton("Quit");
            quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            quitBtn.addActionListener(quit);

            add(mainPnl);
            mainPnl.setLayout(new GridLayout(4, 1, 50, 50));

            mainPnl.add(titlePnl);
            titlePnl.add(titleLabel);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            mainPnl.add(inputPnl);
            inputPnl.setLayout(new GridLayout(2, 1, 150, 0));
            inputPnl.add(inputLabel);
            inputPnl.add(inputTxtFld);
            inputTxtFld.setFont(new Font("Times New Roman", Font.PLAIN, 20));

            mainPnl.add(displayPnl);
            displayPnl.add(displayScroll);
            displayTxtArea.setFont(new Font("Times New Roman", Font.BOLD, 15));

            mainPnl.add(btnPnl);
            btnPnl.add(searchBtn);
            btnPnl.add(quitBtn);
        }

        private class searchListener implements ActionListener {
            public void actionPerformed(ActionEvent AE) {
                displayTxtArea.setText("");
                input = inputTxtFld.getText();
                File workingDirectory = new File(System.getProperty("user.dir"));
                Path file = Paths.get(workingDirectory.getPath() + "\\ProductsCreation.txt");
                try {
                    RandomAccessFile inFile = new RandomAccessFile(file.toString(), "r");
                    while ((display = inFile.readLine()) != null) {
                        if (display.contains(input)) {
                            displayTxtArea.append(display + "\n");
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found!!!");
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private class quitListener implements ActionListener {
            public void actionPerformed(ActionEvent AE) {
                System.exit(0);
            }
        }
    }
}

