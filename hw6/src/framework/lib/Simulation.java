package framework.lib;

import framework.interfaces.Rule;
import framework.interfaces.Viewport;
import framework.interfaces.Visualization;
import framework.lib.Simulation2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;


/**
 * Defines a simulation for a cellular automaton. This class handles the GUI
 * elements for simulations of arbitrary dimension. Implementations are given
 * for 1 and 2 dimensional simulations, but one can easily create a simulation
 * for a higher dimension. <br>
 * <br>
 * This class offers a factory method that can create a simulation for
 * {@code Rule<Point1D>} and {@code Rule<Point2D>}, as well as a way to create
 * simulations for other types of Points.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 * @param <T> A point that specifies a concrete dimension that this simulation
 *            will support. Should be a strict subclass of Point.
 */
public abstract class Simulation<T extends Point> implements ActionListener
{

	private Rule<T>		rule;
	private Viewport<T>	viewport;
	private State<T>	state;

	private boolean		playing;

	private JFrame		frame;
	private JPanel		controls, view;
	private JButton		playBtn, pauseBtn;
	private JSlider		speedSlider;
	private Timer		playTimer;

	/**
	 * This is a factory method takes a rule and a visualization and produces a
	 * Simulation suitable for viewing the automaton.
	 * 
	 * @param r A Rule, which must be of Point1D or Point2D, otherwise this
	 *            method will fail
	 * @param v a visualization to use for coloring the states
	 * @return a Simulation that can be used to visualize a cellular automaton.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Point> Simulation<T> getSimulation(Rule<T> r,
			Visualization v)
	{
		Class<? extends Point> c = r.getOrigin().getClass();
		if (c.isInstance(Point1D.class))
			return (Simulation<T>) new Simulation1D((Rule<Point1D>) r, v);
		else if (c.isInstance(Point2D.class))
			return (Simulation<T>) new Simulation2D((Rule<Point2D>) r, v);
		else
			throw new RuntimeException("Illegal Point");
	}

	/**
	 * Constructs a simulation.
	 */
	protected Simulation(Rule<T> r, Viewport<T> viewport)
	{
		this.rule = r;
		this.viewport = viewport;

		playing = false;
	}

	private void initControlsGUI()
	{
		playBtn = new JButton();
		playBtn.setText("Play");
		playBtn.setActionCommand("play");
		playBtn.addActionListener(this);

		pauseBtn = new JButton();
		pauseBtn.setText("Pause");
		playBtn.setActionCommand("pause");
		pauseBtn.addActionListener(this);

		speedSlider = new JSlider();
		speedSlider.setMinimum(1);
		speedSlider.setMaximum(5);
		speedSlider.setBorder(BorderFactory.createTitledBorder("Speed"));
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
		speedSlider.setValue(1);

		playTimer = new Timer(0, this);
		playTimer.setRepeats(false);
		playTimer.setActionCommand("step");

		controls = new JPanel();
		controls.add(playBtn);
		controls.add(pauseBtn);
		controls.add(speedSlider);
	}

	private void initGUI()
	{
		initControlsGUI();

		view = new JPanel();

		JPanel mainPanel = new JPanel();
		mainPanel.add(view);
		mainPanel.add(controls);

		frame = new JFrame();
		frame.setTitle("AutoBoss");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
	}

	private int getSpeed()
	{
		int val = speedSlider.getValue() - speedSlider.getMinimum();
		int range = speedSlider.getMaximum() - speedSlider.getMinimum();
		double fraction = 1 - ((double) val) / range; // smaller values give
														// larger delay
		return (int) (Math.pow(fraction * 10, 3)) + 50;
	}

	private void timerNextStep()
	{
		if (!playTimer.isRunning()) {
			playTimer.setInitialDelay(getSpeed());
			playTimer.start();
		}
	}

	private void setPlaying(boolean playing)
	{
		playBtn.setEnabled(!playing);
		pauseBtn.setEnabled(playing);

		if (playing) {
			timerNextStep();
		}

		this.playing = playing;
	}

	/**
	 * Causes the simulation to move one step forward according to the given
	 * rules. If no rules are set, this method will fail with an
	 * IllegalStateException.
	 * 
	 * @throws IllegalStateException If no Rule has been given to this
	 *             simulation
	 */
	protected void step()
	{
		this.state.step(this.rule);

		viewport.drawState(state, view.getGraphics());
	}

	/**
	 * Calls {@code step()} times number of times.
	 * 
	 * @param times the number of times to call {@code step()}
	 */
	protected void step(int times)
	{
		for (int i = 0; i < times; i++) {
			this.state.step(this.rule);
		}
	}

	/**
	 * Called by the client to start the GUI. If the rules haven't been set,
	 * this method will fail. The factory method will automatically set the
	 * rules, so the only time this will fail is if you create your own subclass
	 * of Simulation
	 */
	public void run()
	{
		initGUI();

		setPlaying(false);
	}

	/**
	 * Allows you to swap Viewports of the program out.
	 * 
	 * @param v a Viewport you want to use to visualize the data.
	 */
	public void setViewport(Viewport<T> v)
	{
		this.viewport = v;
	}

	/**
	 * Allows you to swap out a Visualization (color picker)
	 * 
	 * @param v the new visualization
	 */
	public void setVisualization(Visualization v)
	{
		this.viewport.setVisualization(v);
	}

	/**
	 * This method allows for on-the-fly switching with rules, as well as
	 * setting the initial rule.
	 * 
	 * @param r the new Rule to use for the simulation.
	 */
	public void setRule(Rule<T> r)
	{
		rule = r;
	}

	/**
	 * Gets the current state of the simulation
	 * 
	 * @return the current state of the simulation
	 */
	public abstract State<T> getState();

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action.equals("play"))
			setPlaying(true);
		else if (action.equals("pause"))
			setPlaying(false);
		else if (action.equals("step")) {
			step();
			if (playing) timerNextStep();
		}
	}
}
