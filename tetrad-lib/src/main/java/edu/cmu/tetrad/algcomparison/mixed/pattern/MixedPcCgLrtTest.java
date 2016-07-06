package edu.cmu.tetrad.algcomparison.mixed.pattern;

import edu.cmu.tetrad.algcomparison.Algorithm;
import edu.cmu.tetrad.algcomparison.DataType;
import edu.cmu.tetrad.algcomparison.Parameters;
import edu.cmu.tetrad.data.DataSet;
import edu.cmu.tetrad.graph.Graph;
import edu.cmu.tetrad.search.*;

/**
 * Created by jdramsey on 6/4/16.
 */
public class MixedPcCgLrtTest implements Algorithm {
    public Graph search(DataSet dataSet, Parameters parameters) {
        IndependenceTest test = new IndTestConditionalGaussianLrt(dataSet,
                parameters.getDouble("alpha"));
        Pc pc = new Pc(test);
        return pc.search();
    }

    public Graph getComparisonGraph(Graph dag) {
        return SearchGraphUtils.patternForDag(dag);
    }

    public String getDescription() {
        return "PC using the Conditional Gaussian LRT test";
    }

    @Override
    public DataType getDataType() {
        return DataType.Mixed;
    }
}
