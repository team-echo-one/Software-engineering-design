package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.Course;
import bean.Message;
import listener.HttpListenThread;
import utils.Configure;
import utils.HibernateUtil;
import utils.Var;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainForm
{
	HttpListenThread listenThread = null;
	private JFrame frmServer;
	private JTextField tf_IP;
	private JLabel lb_Port;
	private JTextField tf_Port;
	private JButton btn_Stop;
	private JLabel lb_Count;
	// private JTextField tf_Count;
	// private JTextArea tarea_info;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainForm window = new MainForm();
					window.frmServer.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmServer = new JFrame();
		frmServer.setResizable(false);
		frmServer.setTitle("server");
		frmServer.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		frmServer.setBounds(100, 100, 548, 371);
		frmServer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);

		JLabel lab_IP = new JLabel("IP:");
		lab_IP.setBounds(10, 10, 25, 15);
		frmServer.getContentPane().add(lab_IP);

		tf_IP = new JTextField();
		tf_IP.setEditable(false);
		tf_IP.setBounds(32, 7, 93, 21);
		frmServer.getContentPane().add(tf_IP);
		tf_IP.setColumns(10);

		lb_Port = new JLabel("Port:");
		lb_Port.setBounds(135, 10, 36, 15);
		frmServer.getContentPane().add(lb_Port);

		tf_Port = new JTextField();
		tf_Port.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{ // 设置只能输入数字
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
				{
				} else
				{
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
		tf_Port.setText("6789");
		tf_Port.setBounds(166, 8, 93, 21);
		frmServer.getContentPane().add(tf_Port);
		tf_Port.setColumns(10);

		lb_Count = new JLabel("Count:");
		lb_Count.setBounds(269, 10, 54, 15);
		frmServer.getContentPane().add(lb_Count);

		Var.tf_Count = new JTextField();
		Var.tf_Count.setEditable(false);
		Var.tf_Count.setBounds(310, 7, 66, 21);
		frmServer.getContentPane().add(Var.tf_Count);
		Var.tf_Count.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 512, 287);
		frmServer.getContentPane().add(scrollPane);

		Var.tarea_info = new JTextArea();
		scrollPane.setViewportView(Var.tarea_info);
		Var.tarea_info.setEditable(true);

		JButton btn_Start = new JButton("Start");
		btn_Start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Timer timer = new Timer();
				timer.schedule(new CheckTask(), 0, Configure.getCheckPeriod() * 60 * 1000);
				listenThread = new HttpListenThread();
				int port = 0;
				try
				{
					port = Integer.parseInt(tf_Port.getText());
					if (port > 65535)
					{
						throw new Exception();
					}
				} catch (Exception ex)
				{
					JOptionPane.showInternalMessageDialog(frmServer.getContentPane(), "Port Error", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					tf_Port.setText("");
					return;
				}
				Var.Port = port;
				try
				{
					// address = InetAddress.getByName(Var.Host);
					listenThread.start();
				} catch (Exception ex)
				{
					ex.printStackTrace();
				}
				// tf_IP.setText(address.getHostAddress());
				btn_Start.setEnabled(false);
			}
		});
		btn_Start.setBounds(386, 6, 63, 23);
		frmServer.getContentPane().add(btn_Start);

		btn_Stop = new JButton("Stop");
		btn_Stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					listenThread.close();
				} catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
				btn_Start.setEnabled(true);
			}
		});
		btn_Stop.setBounds(459, 6, 63, 23);
		frmServer.getContentPane().add(btn_Stop);
	}

	class CheckTask extends TimerTask
	{
		@Override
		public void run()
		{
			Date nowDate = new Date();
			if(Configure.getShutDownDate()==null)
			{
				return;
			}
			if(nowDate.before(Configure.getShutDownDate()))
			{
				return;
			}
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			ArrayList<Course> courses = new ArrayList<>();
			try
			{
				String hql = "from Course";
				Query query = session.createQuery(hql);
				for (Object o : query.list())
				{
					Course course = (Course) o;
					if (course.getStudents().size() <= 3)
					{
						courses.add(course);
					}
				}
				String title = "Notification : Course has been canceled";
				StringBuilder content = new StringBuilder("");
				content.append("Course Id").append("\t").append("Course Name\n");
				for(Course course : courses)
				{
					content.append(course.getId()).append("\t").append(course.getName()).append("\n");
				}
				content.append("because of the number of selected students is too little,these courses has been canceled");
				Message message = new Message();
				message.setContent(content.toString());
				message.setTitle(title);
				message.setReleaseDate(new Date());
				session.save(message);
				tx.commit();
			} catch (Exception e)
			{
				tx.rollback();
				e.printStackTrace();
				return;
			}
			return;
		}
	}
}
