package framework.plugin;

import framework.lib.Point2D;
import framework.lib.Simulation;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LifeRule rule = new LifeRule();
		LifeVisualization viz = new LifeVisualization();
		
		Simulation<Point2D> sim = Simulation.getSimulation(rule, viz);
		
		sim.run();
	}

}
