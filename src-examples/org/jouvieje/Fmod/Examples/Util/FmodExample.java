/*
 * Created on 12 janv. 08
 */
package org.jouvieje.Fmod.Examples.Util;

import javax.swing.JPanel;

public interface FmodExample extends Runnable
{
	public String getTitle();
	public JPanel getPanel();
	public void stop();
}
