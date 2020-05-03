package model;
import java.util.List;
import c3d.geometry.*;
import libraries.PointNDImpl;

public interface FunctionFeatures {
	/**
	 * 
	 * @return get the points belong to the domain
	 */
	public List<PointNDImpl> getPointsInDomain();
	/**
	 * 
	 * @return get the points that not belong to the domain
	 */
	public List<PointNDImpl> getPointsOutOfDomain();
	/**
	 * 
	 * @return get the Absolute Max of the function in the interval
	 */
	public PointNDImpl getAbsoluteMax();
	/**
	 * 
	 * @return get the Absolute Min of the function in the interval
	 */
	public PointNDImpl getAbsoluteMin();
	/**
	 * 
	 * @return get the Poligonal Model of the Function in the interval
	 */
	public List<Segment3D> getPolygonalModel();
}
