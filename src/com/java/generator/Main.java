package com.java.generator;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

	private JFrame frame;
	private JTextField dbHost;
	private JTextField dbPort;
	private JTextField dbUserName;
	private JTextField dbPassword;
	private JTextField dbDb;
	private JTextField dbSchema;
	private JTextField dbtableName;
	private JTextField writerName;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 639, 665);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setTitle("");
//		setContentPane(contentPane);
		frame = new JFrame();
		frame.setTitle("DTO 생성 프로그램");
		frame.getContentPane().setFont(new Font("굴림", Font.BOLD, 17));
		frame.setBounds(100, 100, 636, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);

		JLabel lblNewLabel = new JLabel("DTO 생성 프로그램");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 598, 42);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("데이터베이스 종류 : ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(-27, 87, 186, 21);
		frame.getContentPane().add(lblNewLabel_4);

		dbHost = new JTextField();
		dbHost.setFont(new Font("굴림", Font.PLAIN, 12));
		dbHost.setToolTipText("HOST주소(IP주소)");
		dbHost.setBounds(163, 157, 140, 21);
		frame.getContentPane().add(dbHost);
		dbHost.setColumns(10);

		JLabel dbHostLabel = new JLabel("HOST주소 (IP주소) : ");
		dbHostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbHostLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbHostLabel.setBounds(38, 157, 121, 15);
		frame.getContentPane().add(dbHostLabel);

		JLabel dbPortLabel = new JLabel("PORT번호 : ");
		dbPortLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPortLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbPortLabel.setBounds(38, 187, 121, 15);
		frame.getContentPane().add(dbPortLabel);

		dbPort = new JTextField();
		dbPort.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPort.setToolTipText("PORT번호");
		dbPort.setBounds(163, 187, 140, 21);
		frame.getContentPane().add(dbPort);
		dbPort.setColumns(10);

		JLabel dbUserNameLabel = new JLabel("USERNAME : ");
		dbUserNameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbUserNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbUserNameLabel.setBounds(38, 217, 121, 15);
		frame.getContentPane().add(dbUserNameLabel);

		dbUserName = new JTextField();
		dbUserName.setFont(new Font("굴림", Font.PLAIN, 12));
		dbUserName.setToolTipText("USERNAME");
		dbUserName.setBounds(163, 217, 140, 21);
		frame.getContentPane().add(dbUserName);
		dbUserName.setColumns(10);

		JLabel dbPasswordLabel = new JLabel("PASSWORD : ");
		dbPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbPasswordLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPasswordLabel.setBounds(38, 247, 121, 15);
		frame.getContentPane().add(dbPasswordLabel);

		dbPassword = new JTextField();
		dbPassword.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPassword.setToolTipText("PASSWORD");
		dbPassword.setBounds(163, 247, 140, 21);
		frame.getContentPane().add(dbPassword);
		dbPassword.setColumns(10);

		JLabel dbDbLabel = new JLabel("DB명 : ");
		dbDbLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbDbLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbDbLabel.setBounds(38, 277, 121, 15);
		frame.getContentPane().add(dbDbLabel);

		dbDb = new JTextField();
		dbDb.setFont(new Font("굴림", Font.PLAIN, 12));
		dbDb.setToolTipText("DB명");
		dbDb.setBounds(163, 277, 140, 21);
		frame.getContentPane().add(dbDb);
		dbDb.setColumns(10);

		JLabel dbSchemaLabel = new JLabel("Schema명 : ");
		dbSchemaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbSchemaLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbSchemaLabel.setBounds(38, 307, 121, 15);
		frame.getContentPane().add(dbSchemaLabel);

		dbSchema = new JTextField();
		dbSchema.setFont(new Font("굴림", Font.PLAIN, 12));
		dbSchema.setToolTipText("Schema명");
		dbSchema.setBounds(163, 307, 140, 21);
		frame.getContentPane().add(dbSchema);
		dbSchema.setColumns(10);

		JLabel tableNameLabel = new JLabel("테이블 명 : ");
		tableNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		tableNameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		tableNameLabel.setBounds(38, 337, 121, 15);
		frame.getContentPane().add(tableNameLabel);

		dbtableName = new JTextField();
		dbtableName.setToolTipText("테이블 명");
		dbtableName.setFont(new Font("굴림", Font.PLAIN, 12));
		dbtableName.setBounds(163, 337, 140, 21);
		frame.getContentPane().add(dbtableName);
		dbtableName.setColumns(10);

		writerName = new JTextField();
		writerName.setText("BUSIL");
		writerName.setToolTipText("개발자");
		writerName.setFont(new Font("굴림", Font.PLAIN, 12));
		writerName.setBounds(163, 367, 140, 21);
		frame.getContentPane().add(writerName);
		writerName.setColumns(10);

		JLabel nameLabel = new JLabel("개발자 : ");
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(38, 367, 121, 15);
		frame.getContentPane().add(nameLabel);

		JRadioButton postgresqlBtn = new JRadioButton("postgresql");
		postgresqlBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		postgresqlBtn.setBounds(163, 128, 140, 21);
		frame.getContentPane().add(postgresqlBtn);
		postgresqlBtn.setActionCommand("postgresql");

		JRadioButton mariadbBtn = new JRadioButton("mariadb");
		mariadbBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		mariadbBtn.setBounds(163, 108, 140, 21);
		frame.getContentPane().add(mariadbBtn);
		mariadbBtn.setActionCommand("mariadb");

		JRadioButton mysqlBtn = new JRadioButton("mysql");
		mysqlBtn.setSelected(true);
		mysqlBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		mysqlBtn.setBounds(163, 87, 140, 21);
		frame.getContentPane().add(mysqlBtn);
		mysqlBtn.setActionCommand("mysql");

		ButtonGroup group = new ButtonGroup();
		group.add(postgresqlBtn);
		group.add(mariadbBtn);
		group.add(mysqlBtn);

		JLabel copyright = new JLabel("Copyright 2022. BUSIL. All rights reserved.");
		copyright.setHorizontalAlignment(SwingConstants.CENTER);
		copyright.setBounds(12, 479, 598, 15);
		frame.getContentPane().add(copyright);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(332, 73, 266, 356);
		frame.getContentPane().add(textArea);

		JButton button = new JButton("출력");
		button.setFont(new Font("굴림", Font.PLAIN, 12));
		button.setBounds(209, 448, 94, 21);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(null);
				String dbType = null;
				if (postgresqlBtn.isSelected()) {
					dbType = group.getSelection().getActionCommand();
				}
				if (mariadbBtn.isSelected()) {
					dbType = group.getSelection().getActionCommand();
				}
				if (mysqlBtn.isSelected()) {
					dbType = group.getSelection().getActionCommand();
				}
				String dbHst = dbHost.getText();
				String dbPrt = dbPort.getText();
				String dbUsrNm = dbUserName.getText();
				String dbPswd = dbPassword.getText();
				String dbNm = dbDb.getText();
				String dbShm = dbSchema.getText();
				String tblNm = dbtableName.getText();
				try {
					GsGenerator gsGenerator = new GsGenerator();
					List<GsDto> gsDtoList = gsGenerator.connectDB(dbType, dbHst, dbPrt, dbUsrNm, dbPswd, dbShm, dbNm, tblNm);
					List<String> gsGeneratedList = gsGenerator.gsGenerator(gsDtoList);
//					System.out.println(gsGeneratedList.toString());
					if (!gsGeneratedList.isEmpty()) {
						for (String line : gsGeneratedList) {
							textArea.append(line + "\n");
						}
					} else {
						textArea.append("알 수 없는 이유로 쿼리 조회 시 아무것도 안나옵니다.\n");
						textArea.append("저의 능력 부족으로\n");
						textArea.append("테이블명 조회 시 대소문자 구분이 존재합니다.ㅜㅜ\n");
						textArea.append("테이블명 대소문자 구분 때문에\n");
						textArea.append("조회가 안될수 있으므로 확인부탁드립니다.\n");
					}

				} catch (Exception e) {
					textArea.append(e.toString() + "\n");
				}
			}
		});

		JButton exit = new JButton("종료");
		exit.setFont(new Font("굴림", Font.PLAIN, 12));
		exit.setBounds(309, 448, 94, 21);
		frame.getContentPane().add(exit);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(332, 73, 266, 356);
		frame.getContentPane().add(scrollPane);

	}
}
