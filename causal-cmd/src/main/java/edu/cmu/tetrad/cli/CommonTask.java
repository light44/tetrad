/*
 * Copyright (C) 2016 University of Pittsburgh.
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
package edu.cmu.tetrad.cli;

import edu.cmu.tetrad.cli.util.AppTool;
import edu.cmu.tetrad.cli.util.FileIO;
import edu.cmu.tetrad.data.DataSet;
import edu.cmu.tetrad.io.DataReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Sep 9, 2016 4:59:21 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class CommonTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTask.class);

    private void logStartTask(String task) {
        System.out.printf("%s: Start %s.%n", AppTool.fmtDateNow(), task);
        LOGGER.info(String.format("Start %s.", task));
    }

    private void logEndTask(String task) {
        System.out.printf("%s: End %s.%n", AppTool.fmtDateNow(), task);
        LOGGER.info(String.format("End %s.", task));
    }

    private void logFailedTask(String task, Exception exception) {
        String errMsg = String.format("Failed %s.", task);
        System.err.println(errMsg);
        LOGGER.error(errMsg, exception);
    }

    protected DataSet readInDataSet(Set<String> excludedVariables, Path dataFile, DataReader dataReader) {
        DataSet dataSet = null;

        String task = "reading in data file " + dataFile.getFileName();
        logStartTask(task);
        try {
            dataSet = excludedVariables.isEmpty() ? dataReader.readInData() : dataReader.readInData(excludedVariables);
        } catch (IOException exception) {
            logFailedTask(task, exception);
        }
        logEndTask(task);

        return dataSet;
    }

    protected Set<String> readInVariables(Path variableFile) {
        Set<String> variables = new HashSet<>();

        if (variableFile != null) {
            String task = "reading in excluded variable file " + variableFile.getFileName();
            logStartTask(task);
            try {
                variables.addAll(FileIO.extractUniqueLine(variableFile));
            } catch (IOException exception) {
                logFailedTask(task, exception);
            }
            logEndTask(task);
        }

        return variables;
    }

}
