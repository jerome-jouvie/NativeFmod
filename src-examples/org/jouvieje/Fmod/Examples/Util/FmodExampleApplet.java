/*
 * Created on 8 janv. 08
 */
package org.jouvieje.Fmod.Examples.Util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JApplet;

public class FmodExampleApplet extends JApplet {
	private static final long serialVersionUID = 1L;
	private FmodExample example;
	private Thread thread = null;

	public void init() {
		//Instanciate the fmod example choosed
		try {
			this.example = (FmodExample)Class.forName(getParameter("fmodExample")).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.setSize(example.getPanel().getSize());
		this.setContentPane(example.getPanel());
		this.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) example.stop();
			}
		});
	}

	public void start() {
		thread = new Thread(example);
		thread.start();
	}

	public void stop() {
		if(example != null) {
			//Attempt to stop the thread if running
			if(thread != null && thread.isAlive()) {
				try {
					thread.suspend();
					thread.interrupt();
					thread.stop();
				}
				catch(Error er) {}
				catch(Exception ex) {}
			}

			example.stop();
		}
	}
}
