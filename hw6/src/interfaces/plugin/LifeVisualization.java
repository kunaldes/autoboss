package interfaces.plugin;

import interfaces.interfaces.Visualization;

import java.awt.Color;


public class LifeVisualization implements Visualization
{

	@Override
	public Color getColorRep(int state)
	{
		if(state == 1)
			return Color.YELLOW;
		else
			return Color.BLACK;
	}

}
