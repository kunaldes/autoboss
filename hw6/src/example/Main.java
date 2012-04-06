package example;

import java.util.HashMap;

import framework.interfaces.Rule;
import framework.lib.Neighborhood;
import framework.lib.Point1D;
import framework.lib.Point2D;
import framework.lib.Simulation;
import framework.lib.State;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//this is an example of a 1D simulation. Uncomment it to make it work
		/*
		Rule<Point1D> rule = new Rule<Point1D>(){

			@Override
			public int stepCell(Neighborhood<Point1D> n)
			{
				return 1-n.getCellState(getOrigin());
			}

			@Override
			public Point1D getOrigin()
			{
				return new Point1D(0);
			}

			@Override
			public int getNeighborhoodSize()
			{
				return 0;
			}

			@Override
			public State<Point1D> getInitialState()
			{
				HashMap<Point1D, Integer> state = new HashMap<Point1D, Integer>();
				Point1D size = new Point1D(10);
				state.put(new Point1D(4), 1);
				return new State<Point1D>(size, 2, null, state);
			}
			
		};
		LifeVisualization viz = new LifeVisualization();
		
		Simulation<Point1D> sim = Simulation.getSimulation(rule, viz);
		*/
		
		LifeRule life = new LifeRule();
		LifeVisualization vis = new LifeVisualization();

		Simulation.getSimulation(life, vis).run();

	}

}
