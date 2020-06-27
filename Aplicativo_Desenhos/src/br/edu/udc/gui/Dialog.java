package br.edu.udc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;


public class Dialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static Frame nomeDialog;
	private int result = CANCEL;
	public final static int OK = 1;
	public final static int CANCEL = 0;
	private static String valor;
	
	public static void main(String[] args) {
		try {
			Dialog dialog = new Dialog("Altera Clock","nome");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setVisible(true);
			System.out.println(dialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Dialog(String titulo, String label){
		setTitle(titulo);
		SpringLayout layout = new SpringLayout();
		setBounds(100, 100, 356, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(layout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);

		JLabel nomeText = new JLabel(label);
		layout.putConstraint(SpringLayout.NORTH, nomeText, 0, SpringLayout.NORTH, contentPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nomeText, 0, SpringLayout.HORIZONTAL_CENTER, contentPanel);
		contentPanel.add(nomeText);

		JTextField nome = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, nome, 6, SpringLayout.SOUTH, nomeText);
		layout.putConstraint(SpringLayout.WEST, nome, 139, SpringLayout.WEST, contentPanel);
		layout.putConstraint(SpringLayout.EAST, nome, 78, SpringLayout.WEST, nomeText);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nomeText, 0, SpringLayout.HORIZONTAL_CENTER, contentPanel);
		contentPanel.add(nome);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					valor =  nome.getText();
					//TODO altera E/S 1
					result = OK;
					
					setVisible(false);
				}
			});
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					result = CANCEL;
					setVisible(false);

				}
			});
		}
	}
		
		

	public int getResult() {
		return result;
	}

	public String getValor() {
		//if (result == OK)
			return valor;
		
	}

}

