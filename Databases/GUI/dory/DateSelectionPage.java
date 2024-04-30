import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSelectionPage extends JFrame {
    private JDateChooser collectionDateChooser;
    private JDateChooser returnDateChooser;
    private JButton selectButton;

    public DateSelectionPage() {
        setTitle("Date Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, with gaps of 10 pixels

        collectionDateChooser = new JDateChooser();
        returnDateChooser = new JDateChooser();
        selectButton = new JButton("Select");

        mainPanel.add(new JLabel("Select Collection Date:"));
        mainPanel.add(collectionDateChooser);
        mainPanel.add(new JLabel("Select Return Date:"));
        mainPanel.add(returnDateChooser);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date collectionDate = collectionDateChooser.getDate();
                Date returnDate = returnDateChooser.getDate();
                if (collectionDate != null && returnDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    JOptionPane.showMessageDialog(null, "Collection Date: " + sdf.format(collectionDate) + "\nReturn Date: " + sdf.format(returnDate));
                } else {
                    JOptionPane.showMessageDialog(null, "Please select both collection and return dates.");
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
