package interfaces.interfaces;

import java.awt.Color;


/**
 * A Visualization is the most simple way of modifying how the cellular
 * automaton is drawn to screen. The visualization can be used with the default
 * GUI system to choose colors based on an integer state.
 * 
 * @author Kunal Desai, James Grugett, Prasanth Somasundar
 */
public interface Visualization
{

	/**
	 * This function returns a color for a given state. For example, in a binary
	 * system with two states, you could make state 0 map to Color.white and
	 * state 1 map to Color.black
	 * 
	 * @param state the state for which to get a color
	 * @return the color of the state given
	 */
	public Color getColorRep(int state);

}
