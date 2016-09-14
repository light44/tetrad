/*
 * Copyright (C) 2015 University of Pittsburgh.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.cmu.tetrad.graph;

import java.io.ObjectStreamException;

import edu.cmu.tetrad.util.TetradSerializable;

/**
 * 
 * Sep 14, 2016 2:51:13 PM
 * 
 * @author Chirayu (Kong) Wongchokprasitti, PhD
 *
 */
public final class GraphConstraintType implements TetradSerializable {

	static final long serialVersionUID = 23L;

	public static final GraphConstraintType AtMostOneEdgePerPair = new GraphConstraintType("AtMostOneEdgePerPair");
	public static final GraphConstraintType NoEdgesToSelf = new GraphConstraintType("NoEdgesToSelf");
	public static final GraphConstraintType DirectedUndirectedOnly = new GraphConstraintType("DirectedUndirectedOnly");
	public static final GraphConstraintType InArrowImpliesNonancestor = new GraphConstraintType(
			"InArrowImpliesNonancestor");
	public static final GraphConstraintType DirectedEdgesOnly = new GraphConstraintType("DirectedEdgesOnly");

	private final transient String name;

	private GraphConstraintType(String name) {
		this.name = name;
	}

	public static GraphConstraint graphConstraintInstance(GraphConstraintType graphConstraintType) {
		switch (graphConstraintType.name) {
		case "AtMostOneEdgePerPair":
			return new AtMostOneEdgePerPair();
		case "NoEdgesToSelf":
			return new NoEdgesToSelf();
		case "DirectedUndirectedOnly":
			return new DirectedUndirectedOnly();
		case "InArrowImpliesNonancestor":
			return new InArrowImpliesNonancestor();
		case "DirectedEdgesOnly":
			return new DirectedEdgesOnly();
		}
		return null;
	}

	public String toString() {
		return name;
	}

	// Declarations required for serialization.
	private static int nextOrdinal = 0;
	private final int ordinal = nextOrdinal++;
	private static final GraphConstraintType[] TYPES = { AtMostOneEdgePerPair, NoEdgesToSelf, DirectedUndirectedOnly,
			InArrowImpliesNonancestor, DirectedEdgesOnly };

	Object readResolve() throws ObjectStreamException {
		return TYPES[ordinal]; // Canonicalize.
	}

}
