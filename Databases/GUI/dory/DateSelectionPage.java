import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSelectionPage extends JFrame {
    private JFormattedTextField collectionDateField;
    private JFormattedTextField returnDateField;
    private JButton selectButton;

    public DateSelectionPage() {
        setTitle("Date Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, with gaps of 10 pixels

        collectionDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        returnDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        selectButton = new JButton("Select");

        mainPanel.add(new JLabel("Select Collection Date (YYYY-MM-DD):"));
        mainPanel.add(collectionDateField);
        mainPanel.add(new JLabel("Select Return Date (YYYY-MM-DD):"));
        mainPanel.add(returnDateField);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date collectionDate = sdf.parse(collectionDateField.getText());
                    Date returnDate = sdf.parse(returnDateField.getText());
                    JOptionPane.showMessageDialog(null, "Collection Date: " + sdf.format(collectionDate) + "\nReturn Date: " + sdf.format(returnDate));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please enter dates in YYYY-MM-DD format.");
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(selectButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DateSelectionPage datePage = new DateSelectionPage();
                datePage.setVisible(true);
            }
        });
    }
}
