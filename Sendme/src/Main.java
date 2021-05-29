import java.io.*;
import java.net.*;
import javax.swing.*;

public class Main extends Thread{
	
	static String filename,path,ip,iptemp,ipa;
	static boolean conected=false;
	static long length,sending;
	static JFrame frame;
	static JMenu help;
	static JMenuBar menu;
	static JMenuItem how, mainmenu;
	static JButton b1, b2, b3,background;
	static JLabel la1,la2,pic;
	static JTextField tx;
	static DefaultBoundedRangeModel model = new DefaultBoundedRangeModel();
	static JProgressBar pro;
	static URL url = Main.class.getResource("/assest/help.jpg");
	static Icon icon = new ImageIcon(url);
	
	public static void main (String[] args)throws IOException {
		

		
		frame = new JFrame("SENDME");
		tx =new JTextField();
		la1 = new JLabel("label");
		la2 = new JLabel("info");
		menu = new JMenuBar();
		help = new JMenu("HELP");
		how = new JMenuItem("HOW TO USE");
		b1 = new JButton("SEND");
		b2 = new JButton("RECEIVE");
		background = new JButton(icon);
		background.setRolloverIcon(icon);
		background.setVisible(false);
		mainmenu = new JMenuItem("MAIN MENU");
		mainmenu.setVisible(false);
		b3 = new JButton("EXIT");
		pro = new JProgressBar(model);
		
		
		
		help.add(how);
		help.add(mainmenu);
		menu.add(help);
		
		b1.setBounds(175, 50, 150, 30);
		b1.setFocusable(false);
		b2.setBounds(175, 90, 150, 30);
		b2.setFocusable(false);
		b3.setBounds(175, 130, 150, 30);
		b3.setFocusable(false);
		background.setBounds(0, 0, 480, 220);
		tx.setBounds(175, 170, 150, 30);
		la1.setBounds(175,200, 300,30);
		la2.setBounds(175,200, 300,30);
		pro.setValue(0);
		pro.setStringPainted(true);
		pro.setBounds(175, 90, 150, 30);
		
		
		
		b1.addActionListener(e ->
		{	
			ip= tx.getText();
			connect(ip);
			
		});
		
		
		
		b2.addActionListener(e ->
		{       
			b2.setVisible(false);
			receive();
		});
		
		
		
		b3.addActionListener(e ->
		{
			frame.dispose();
		});
		
		
		
		how.addActionListener(e -> 
		{
				how.setVisible(false);
				mainmenu.setVisible(true);
				background.setVisible(true);
				b1.setVisible(false);
				b2.setVisible(false);
				b3.setVisible(false);
				la1.setVisible(false);
				la2.setVisible(false);
				tx.setVisible(false);
				pro.setVisible(false);
			
		});
		mainmenu.addActionListener(e ->
		{
			mainmenu.setVisible(false);
			background.setVisible(false);
			how.setVisible(true);
			b1.setVisible(true);
			b2.setVisible(true);
			b3.setVisible(true);
			la1.setVisible(true);
			la2.setVisible(true);
			tx.setVisible(true);
			pro.setVisible(true);
			
		});
		
		ip = ipad();
		int i= 0;
		while(ip.length()!=i)
		{
			if(ip.charAt(i)=='/')
			{
				iptemp = ip.substring(i+1,ip.length());
				break;
			}
			i++;
			
		}
		
		la1.setText(iptemp);
		System.out.println(ip);
		la2.setText("");

		
		
		frame.add(menu);
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.add(tx);
		frame.add(la2);
		frame.add(la1);
		frame.add(pro);
		frame.add(background);
		frame.setJMenuBar(menu);
		frame.setSize(500, 300);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
	public static String fileCho()throws IOException{
		
		JFileChooser file = new JFileChooser();
        file.setMultiSelectionEnabled(true);
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.setFileHidingEnabled(false);
        if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
          java.io.File fi = file.getSelectedFile();
          path = fi.getPath();
         }
        System.out.print(path+"\n");	
        if(path==null)
        {
        	path=fileCho();
        }
		return path;
	}

	public static void connect(String ipa)
	{
		
		Threads t = new Threads();
		Thread t1 = new Thread(t);
		la2.setText("");
		t1.start();
	
	}
	
	public static void receive()
	{
		
		Thread2 t = new Thread2();
		Thread t2 = new Thread(t);
		t2.start();
		
	}
	
	public static String ipad()throws IOException
	{
		InetAddress ipaddress;
		ipaddress = InetAddress.getLocalHost();
		ip=ipaddress.toString();
		return ip;
	}

}

class Threads extends Main implements Runnable{
	public void run() 
	{
		pro.setBounds(175, 50, 150, 30);
		try {
			path =fileCho();

			File file = new File(path); 
			length = file.length();

			filename=file.getName();
			
			Socket socket = new Socket(InetAddress.getByName(ip), 4445);
			byte[] bytes = new byte[32*1024];	
			InputStream in = new FileInputStream(file);
			OutputStream out = socket.getOutputStream();
			int count ;
			DataOutputStream stream = new DataOutputStream(out);
			stream.writeUTF(filename);
			
			DataOutputStream stream1 = new DataOutputStream(out);
			String len =Long.toString(length);
			stream1.writeUTF(len);
			
			sending =0;
			long temp;
			while((count =in.read(bytes)) >0)
			{
				sending +=32768;
				temp =(sending *100)/length;
				pro.setValue((int)temp);
				out.write(bytes, 0, count);
			}
			System.out.println("completed");
			out.close();
			b1.setVisible(true);
			in.close();
			socket.close();
			stream.close();
			}
			catch(Exception e)
			{
				la1.setVisible(false);
				la2.setText("Receiver not found!!!");
				e.printStackTrace();
			} 
		
			}


}
class Thread2 extends Main implements Runnable {
	public void run()
	{
		try {
			ServerSocket serversocket= new ServerSocket(4445);
			Socket socket = serversocket.accept();


			InputStream in = socket.getInputStream();
			String output;
			
			DataInputStream din = new DataInputStream(in);
			output = din.readUTF();
			
			DataInputStream din2 = new DataInputStream(in);
			String len =din2.readUTF();
			
			String  outp ="C:\\Sendme\\received\\"+output;
			File file = new File("C:\\Sendme\\received\\");
			if(!file.exists())
			{
				file.mkdirs();
			}

			OutputStream out = new FileOutputStream(outp);
			byte[] bytes = new byte[32*1024];//32768
			int count;
			long temp=0;
			long length =Long.parseLong(len);
			
			while((count = in.read(bytes)) > 0)
			{
				out.write(bytes , 0, count);
				sending +=32768;
				temp =(sending *100)/length;
				pro.setValue(((int)temp));
			}
			pro.setValue(0);
			out.close();
			in.close();
			socket.close();
			serversocket.close();
			din.close();
			din2.close();
			b2.setVisible(true);
			}
			catch(IOException e)
			{
			e.printStackTrace();
			}
	}
}