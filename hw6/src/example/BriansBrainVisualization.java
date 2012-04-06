package example;

import java.awt.Color;

import framework.interfaces.Visualization;

public class BriansBrainVisualization implements Visualization
{

	@Override
	public Color getColorRep(int state)
	{
		if(state == 0)
			return Color.BLACK;
		else if(state == 1)
			return Color.CYAN;
		else
			return Color.YELLOW;
	}

}
