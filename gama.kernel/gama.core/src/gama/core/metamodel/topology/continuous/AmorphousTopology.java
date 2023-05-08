/*******************************************************************************************************
 *
 * AmorphousTopology.java, in gama.core, is part of the source code of the GAMA modeling and simulation platform
 * (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 *
 ********************************************************************************************************/
package gama.core.metamodel.topology.continuous;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;

import gama.core.common.geometry.Envelope3D;
import gama.core.metamodel.agent.IAgent;
import gama.core.metamodel.population.IPopulation;
import gama.core.metamodel.shape.GamaPoint;
import gama.core.metamodel.shape.GamaShape;
import gama.core.metamodel.shape.IShape;
import gama.core.metamodel.topology.ISpatialIndex;
import gama.core.metamodel.topology.ITopology;
import gama.core.metamodel.topology.filter.IAgentFilter;
import gama.core.runtime.IScope;
import gama.core.runtime.exceptions.GamaRuntimeException;
import gama.core.util.GamaListFactory;
import gama.core.util.IContainer;
import gama.core.util.IList;
import gama.core.util.path.GamaSpatialPath;
import gama.core.util.path.PathFactory;
import gaml.core.operators.Maths;
import gaml.core.operators.Spatial;
import gaml.core.types.GamaGeometryType;
import gaml.core.types.IType;
import gaml.core.types.Types;

/**
 * The class AmorphousTopology.
 *
 * @author drogoul
 * @since 2 d�c. 2011
 *
 */
public class AmorphousTopology implements ITopology {

	/** The expandable environment. */
	IShape expandableEnvironment = GamaGeometryType.createPoint(new GamaPoint(0, 0));

	/**
	 * @see gama.core.interfaces.IValue#type()
	 */
	// @Override
	// public IType type() {
	// return Types.TOPOLOGY;
	// }

	/**
	 * @see gama.core.interfaces.IValue#stringValue()
	 */
	@Override
	public String stringValue(final IScope scope) throws GamaRuntimeException {
		return "Expandable topology";
	}

	@Override
	public IType<?> getGamlType() { return Types.TOPOLOGY; }

	/**
	 * @see gama.core.interfaces.IValue#toGaml()
	 */
	@Override
	public String serialize(final boolean includingBuiltIn) {
		return "topology({0,0})";
	}

	/**
	 * @see gama.core.interfaces.IValue#copy()
	 */
	@Override
	public ITopology copy(final IScope scope) throws GamaRuntimeException {
		return new AmorphousTopology();
	}

	/**
	 * @see gama.core.environment.ITopology#initialize(gama.core.interfaces.IPopulation)
	 */
	@Override
	public void initialize(final IScope scope, final IPopulation<? extends IAgent> pop) throws GamaRuntimeException {}

	@Override
	public void updateAgent(final Envelope3D previous, final IAgent agent) {
		final IShape ng =
				Spatial.Operators.union(agent.getScope(), expandableEnvironment.getGeometry(), agent.getGeometry());
		expandableEnvironment.setGeometry(new GamaShape(ng.getInnerGeometry().getEnvelope()));
	}

	@Override
	public void removeAgent(final IAgent agent) {}

	@Override
	public IList<IAgent> getAgentClosestTo(final IScope scope, final IShape source, final IAgentFilter filter,
			final int number) {
		return GamaListFactory.EMPTY_LIST;
	}

	@Override
	public IAgent getAgentClosestTo(final IScope scope, final IShape source, final IAgentFilter filter) {
		return null;
	}

	@Override
	public IAgent getAgentFarthestTo(final IScope scope, final IShape source, final IAgentFilter filter) {
		return null;
	}

	@Override
	public Set<IAgent> getNeighborsOf(final IScope scope, final IShape source, final Double distance,
			final IAgentFilter filter) throws GamaRuntimeException {
		return Collections.EMPTY_SET;
	}

	/**
	 * @see gama.core.environment.ITopology#getAgentsIn(gama.core.interfaces.IGeometry,
	 *      gama.core.environment.IAgentFilter, boolean)
	 */
	@Override
	public Set<IAgent> getAgentsIn(final IScope scope, final IShape source, final IAgentFilter f,
			final SpatialRelation relation) {
		return Collections.EMPTY_SET;
	}

	/**
	 * @see gama.core.environment.ITopology#distanceBetween(gama.core.interfaces.IGeometry,
	 *      gama.core.interfaces.IGeometry)
	 */
	@Override
	public Double distanceBetween(final IScope scope, final IShape source, final IShape target) {
		return source.euclidianDistanceTo(target);
	}

	@Override
	public Double distanceBetween(final IScope scope, final GamaPoint source, final GamaPoint target) {
		return source.euclidianDistanceTo(target);
	}

	/**
	 * @see gama.core.environment.ITopology#pathBetween(gama.core.interfaces.IGeometry, gama.core.interfaces.IGeometry)
	 */
	@Override
	public GamaSpatialPath pathBetween(final IScope scope, final IShape source, final IShape target)
			throws GamaRuntimeException {
		// return new GamaPath(this, GamaList.with(source, target));
		return PathFactory.newInstance(scope, this, GamaListFactory.wrap(Types.GEOMETRY, source, target), 0.0);
	}

	@Override
	public GamaPoint getDestination(final GamaPoint source, final double direction, final double distance,
			final boolean nullIfOutside) {
		final double cos = distance * Maths.cos(direction);
		final double sin = distance * Maths.sin(direction);
		return new GamaPoint(source.getX() + cos, source.getY() + sin);

	}

	@Override
	public GamaPoint getDestination3D(final GamaPoint source, final double heading, final double pitch,
			final double distance, final boolean nullIfOutside) {
		final double x = distance * Maths.cos(pitch) * Maths.cos(heading);
		final double y = distance * Maths.cos(pitch) * Maths.sin(heading);
		final double z = distance * Maths.sin(pitch);
		return new GamaPoint(source.getX() + x, source.getY() + y, source.getZ() + z);
	}

	@Override
	public GamaPoint getRandomLocation(final IScope scope) {
		return new GamaPoint(scope.getRandom().next(), scope.getRandom().next());
	}

	@Override
	public IContainer<?, IShape> getPlaces() {
		final IList<IShape> result = GamaListFactory.create(Types.GEOMETRY);
		result.add(expandableEnvironment);
		return result;
	}

	@Override
	public IShape getEnvironment() { return expandableEnvironment; }

	@Override
	public GamaPoint normalizeLocation(final GamaPoint p, final boolean nullIfOutside) {
		return p;
	}

	@Override
	public double getWidth() { return expandableEnvironment.getEnvelope().getWidth(); }

	@Override
	public double getHeight() { return expandableEnvironment.getEnvelope().getHeight(); }

	@Override
	public void dispose() {}

	@Override
	public boolean isValidLocation(final IScope scope, final GamaPoint p) {
		return true;
	}

	@Override
	public boolean isValidGeometry(final IScope scope, final IShape g) {
		return true;
	}

	@Override
	public Double directionInDegreesTo(final IScope scope, final IShape g1, final IShape g2) {
		final GamaPoint source = g1.getLocation();
		final GamaPoint target = g2.getLocation();
		final double x2 = /* translateX(source.x, target.x); */target.getX();
		final double y2 = /* translateY(source.y, target.y); */target.getY();
		final double dx = x2 - source.getX();
		final double dy = y2 - source.getY();
		final double result = Maths.atan2(dy, dx);
		return Maths.checkHeading(result);
	}

	@Override
	public GamaSpatialPath pathBetween(final IScope scope, final GamaPoint source, final GamaPoint target)
			throws GamaRuntimeException {
		return PathFactory.newInstance(scope, this, GamaListFactory.create(scope, Types.POINT, source, target), 0.0);
	}

	@Override
	public List<Geometry> listToroidalGeometries(final Geometry geom) {
		return Collections.EMPTY_LIST;
	}

	@Override
	public boolean isTorus() { return false; }

	@Override
	public boolean isContinuous() { return true; }

	@Override
	public ISpatialIndex getSpatialIndex() {
		return new ISpatialIndex() {

			@Override
			public void insert(final IAgent agent) {}

			@Override
			public void remove(final Envelope3D previous, final IAgent agent) {}

			@Override
			public IAgent firstAtDistance(final IScope scope, final IShape source, final double dist,
					final IAgentFilter f) {
				return null;
			}

			@Override
			public Collection<IAgent> firstAtDistance(final IScope scope, final IShape source, final double dist,
					final IAgentFilter f, final int number, final Collection<IAgent> alreadyChosen) {
				return Collections.EMPTY_LIST;
			}

			@Override
			public Collection<IAgent> allInEnvelope(final IScope scope, final IShape source, final Envelope envelope,
					final IAgentFilter f, final boolean contained) {
				return Collections.EMPTY_LIST;
			}

			@Override
			public Collection<IAgent> allAtDistance(final IScope scope, final IShape source, final double dist,
					final IAgentFilter f) {
				return Collections.EMPTY_LIST;
			}

			@Override
			public void dispose() {}

		};
	}

	@Override
	public IList<GamaSpatialPath> KpathsBetween(final IScope scope, final IShape source, final IShape target,
			final int k) {
		final IList<GamaSpatialPath> paths = GamaListFactory.create(Types.PATH);
		paths.add(pathBetween(scope, source, target));
		return paths;
	}

	@Override
	public IList<GamaSpatialPath> KpathsBetween(final IScope scope, final GamaPoint source, final GamaPoint target,
			final int k) {
		final IList<GamaSpatialPath> paths = GamaListFactory.create(Types.PATH);
		paths.add(pathBetween(scope, source, target));
		return paths;
	}

	@Override
	public void setRoot(final IScope scope, final RootTopology rt) {

	}
}
