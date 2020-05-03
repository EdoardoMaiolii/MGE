package it.unibo.oop.mge.model;
import java.util.List;

import it.unibo.oop.mge.c3d.geometry.*;
import it.unibo.oop.mge.libraries.*;
public interface FunctionFeatures {
	/**
	 * 
	 * @return get the points belong to the domain
	 */
	public List<PointND> getPointsInDomain();
	/**
	 * 
	 * @return get the points that not belong to the domain
	 */
	public List<PointND> getPointsOutOfDomain();
	/**
	 * 
	 * @return get the Absolute Max of the function in the interval
	 */
	public PointND getAbsoluteMax();
	/**
	 * 
	 * @return get the Absolute Min of the function in the interval
	 */
	public PointND getAbsoluteMin();
	/**
	 * 
	 * @return get the Poligonal Model of the Function in the interval
	 */
	public List<Segment3D> getPolygonalModel();
}
