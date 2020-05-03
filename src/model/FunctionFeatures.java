package model;
import java.util.List;
import c3d.geometry.*;
import libraries.NDPoint;

public interface FunctionFeatures {
	/**
	 * 
	 * @return get the points belong to the domain
	 */
	public List<NDPoint> getPointsInDomain();
	/**
	 * 
	 * @return get the points that not belong to the domain
	 */
	public List<NDPoint> getPointsOutOfDomain();
	/**
	 * 
	 * @return get the Absolute Max of the function in the interval
	 */
	public NDPoint getAbsoluteMax();
	/**
	 * 
	 * @return get the Absolute Min of the function in the interval
	 */
	public NDPoint getAbsoluteMin();
	/**
	 * 
	 * @return get the Poligonal Model of the Function in the interval
	 */
	public List<Segment3D> getPolygonalModel();
}
