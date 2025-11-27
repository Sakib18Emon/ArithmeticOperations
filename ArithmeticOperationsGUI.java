import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArithmeticOperationsGUI extends JFrame {

    private JTextField binaryNum1Field;
    private JTextField binaryNum2Field;
    private JTextField resultBinaryField;

    private JComboBox<String> operationComboBox;

    public ArithmeticOperationsGUI() {
        setTitle("Binary Arithmetic Operations");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JPanel selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.setBackground(Color.DARK_GRAY);

        String[] operations = {"Addition", "Subtraction", "Multiplication", "Division"};
        operationComboBox = new JComboBox<>(operations);
        operationComboBox.setBackground(Color.WHITE);
        JButton selectOperationButton = new JButton("Select Operation");
        selectOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOperation();
            }
        });
        selectOperationButton.setBackground(Color.GREEN);
        selectionPanel.add(operationComboBox);
        selectionPanel.add(selectOperationButton);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBackground(Color.LIGHT_GRAY);
        JLabel binaryNum1Label = new JLabel("Binary Number 1:");
        binaryNum1Label.setForeground(Color.BLUE);
        binaryNum1Field = new JTextField();
        JLabel binaryNum2Label = new JLabel("Binary Number 2:");
        binaryNum2Label.setForeground(Color.BLUE);
        binaryNum2Field = new JTextField();
        JLabel resultBinaryLabel = new JLabel("Result (Binary):");
        resultBinaryLabel.setForeground(Color.BLUE);
        resultBinaryField = new JTextField();
        resultBinaryField.setEditable(false);

        inputPanel.add(binaryNum1Label);
        inputPanel.add(binaryNum1Field);
        inputPanel.add(binaryNum2Label);
        inputPanel.add(binaryNum2Field);
        inputPanel.add(resultBinaryLabel);
        inputPanel.add(resultBinaryField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        calculateButton.setBackground(Color.YELLOW);
        buttonPanel.add(calculateButton);

        mainPanel.add(selectionPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void selectOperation() {
        try {
            String binaryNum1 = binaryNum1Field.getText();
            String binaryNum2 = binaryNum2Field.getText();

            String result = performOperation(binaryNum1, binaryNum2, operationComboBox.getSelectedIndex());

            resultBinaryField.setText(result);
        } catch (IllegalArgumentException ex) {
            resultBinaryField.setText(ex.getMessage());
        }
    }

    private void calculate() {
        try {
            String binaryNum1 = binaryNum1Field.getText();
            String binaryNum2 = binaryNum2Field.getText();

            String result = performOperation(binaryNum1, binaryNum2, operationComboBox.getSelectedIndex());

            resultBinaryField.setText(result);
        } catch (IllegalArgumentException ex) {
            resultBinaryField.setText(ex.getMessage());
        }
    }

    private String performOperation(String binaryNum1, String binaryNum2, int operationIndex) {
        switch (operationIndex) {
            case 0: // Addition
                return binaryAddition(binaryNum1, binaryNum2);

            case 1: // Subtraction
                return binarySubtraction(binaryNum1, binaryNum2);

            case 2: // Multiplication
                return boothMultiplication(binaryNum1, binaryNum2);

            case 3: // Division
                return binaryDivision(binaryNum1, binaryNum2);

            default:
                throw new IllegalArgumentException("Invalid operation.");
        }
    }

    private String binaryAddition(String binaryNum1, String binaryNum2) {
        int maxLength = Math.max(binaryNum1.length(), binaryNum2.length());
        binaryNum1 = padWithZeros(binaryNum1, maxLength);
        binaryNum2 = padWithZeros(binaryNum2, maxLength);

        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int bit1 = binaryNum1.charAt(i) - '0';
            int bit2 = binaryNum2.charAt(i) - '0';

            int sum = (bit1 ^ bit2) ^ carry;
            carry = (bit1 & bit2) | ((bit1 ^ bit2) & carry);

            result.insert(0, sum);
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    private String binarySubtraction(String binaryNum1, String binaryNum2) {
        int maxLength = Math.max(binaryNum1.length(), binaryNum2.length());
        binaryNum1 = padWithZeros(binaryNum1, maxLength);
        binaryNum2 = padWithZeros(binaryNum2, maxLength);

        StringBuilder result = new StringBuilder();
        int borrow = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int bit1 = binaryNum1.charAt(i) - '0';
            int bit2 = binaryNum2.charAt(i) - '0';

            int difference = (bit1 ^ bit2) ^ borrow;
            borrow = (~bit1 & bit2) | ((~bit1 | bit2) & borrow);

            result.insert(0, difference);
        }

        return result.toString();
    }

    private String padWithZeros(String binaryNum, int length) {
        StringBuilder paddedBinaryNum = new StringBuilder(binaryNum);
        while (paddedBinaryNum.length() < length) {
            paddedBinaryNum.insert(0, '0');
        }
        return paddedBinaryNum.toString();
    }

    private String boothMultiplication(String binaryNum1, String binaryNum2) {
        int num1 = Integer.parseInt(binaryNum1, 2);
        int num2 = Integer.parseInt(binaryNum2, 2);

        Booth booth = new Booth();
        int result = booth.multiply(num1, num2);

        // Adjust the result to 8 bits
        result = result & 0xFF;

        return Integer.toBinaryString(result);
    }

    private String binaryDivision(String binaryNum1, String binaryNum2) {
        int num1 = Integer.parseInt(binaryNum1, 2);
        int num2 = Integer.parseInt(binaryNum2, 2);

        if (num2 != 0) {
            int quotient = num1 / num2;
            int remainder = num1 % num2;

            if (remainder < 0) {
                remainder += Math.abs(num2);
            }

            return "Quotient: " + Integer.toBinaryString(quotient) + " Remainder: " + Integer.toBinaryString(remainder);
        } else {
            throw new ArithmeticException("Error: Division by zero is not allowed.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArithmeticOperationsGUI();
            }
        });
    }

    private class Booth {
        public int multiply(int n1, int n2) {
            int[] m = binary(n1);
            int[] m1 = binary(-n1);
            int[] r = binary(n2);
            int[] A = new int[9];
            int[] S = new int[9];
            int[] P = new int[9];

            for (int i = 0; i < 4; i++) {
                A[i] = m[i];
                S[i] = m1[i];
                P[i + 4] = r[i];
            }
            display(A, 'A');
            display(S, 'S');
            display(P, 'P');

            System.out.println();

            for (int i = 0; i < 4; i++) {
                if (P[7] == 0 && P[8] == 0)
                    // do nothing
                    ;
                else if (P[7] == 1 && P[8] == 0)
                    add(P, S);
                else if (P[7] == 0 && P[8] == 1)
                    add(P, A);
                else if (P[7] == 1 && P[8] == 1)
                    // do nothing
                    ;

                rightShift(P);
                display(P, 'P');
            }
            return getDecimal(P);
        }

        public int getDecimal(int[] B) {
            int p = 0;
            int t = 1;
            for (int i = 7; i >= 0; i--, t *= 2)
                p += (B[i] * t);
            if (p > 64)
                p = -(256 - p);
            return p;
        }

        public void rightShift(int[] A) {
            for (int i = 8; i >= 1; i--)
                A[i] = A[i - 1];
        }

        public void add(int[] A, int[] B) {
            int carry = 0;
            for (int i = 8; i >= 0; i--) {
                int temp = A[i] + B[i] + carry;
                A[i] = temp % 2;
                carry = temp / 2;
            }
        }

        public int[] binary(int n) {
            int[] bin = new int[4];
            int ctr = 3;
            int num = n;

            if (n < 0)
                num = 16 + n;

            while (num != 0) {
                bin[ctr--] = num % 2;
                num /= 2;
            }
            return bin;
        }

        public void display(int[] P, char ch) {
            System.out.print("\n" + ch + " : ");
            for (int i = 0; i < P.length; i++) {
                if (i == 4)
                    System.out.print(" ");
                if (i == 8)
                    System.out.print(" ");
                System.out.print(P[i]);
            }
        }
    }
}
