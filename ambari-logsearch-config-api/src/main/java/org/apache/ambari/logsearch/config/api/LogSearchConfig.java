/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ambari.logsearch.config.api;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

/**
 * Log Search Configuration, which uploads, retrieves configurations, and monitors it's changes.
 */
public interface LogSearchConfig extends Closeable {
  /**
   * Enumeration of the components of the Log Search service.
   */
  public enum Component {
    SERVER, LOGFEEDER;
  }
  
  /**
   * Initialization of the configuration.
   * 
   * @param component The component which will use the configuration.
   * @param properties The properties of that component.
   * @throws Exception
   */
  void init(Component component, Map<String, String> properties) throws Exception;
  
  /**
   * Returns all the service names with input configurations of a cluster. Will be used only in SERVER mode.
   * 
   * @param clusterName The name of the cluster which's services are required.
   * @return List of the service names.
   */
  List<String> getServices(String clusterName);
  
  /**
   * Checks if input configuration exists.
   * 
   * @param clusterName The name of the cluster where the service is looked for.
   * @param serviceName The name of the service looked for.
   * @return If input configuration exists for the service.
   * @throws Exception
   */
  boolean inputConfigExists(String clusterName, String serviceName) throws Exception;
  
  /**
   * Returns the input configuration of a service in a cluster. Will be used only in SERVER mode.
   * 
   * @param clusterName The name of the cluster where the service is looked for.
   * @param serviceName The name of the service looked for.
   * @return The input configuration for the service if it exists, null otherwise.
   */
  String getInputConfig(String clusterName, String serviceName);
  
  /**
   * Uploads the input configuration for a service in a cluster.
   * 
   * @param clusterName The name of the cluster where the service is.
   * @param serviceName The name of the service of which's input configuration is uploaded.
   * @param inputConfig The input configuration of the service.
   * @throws Exception
   */
  void setInputConfig(String clusterName, String serviceName, String inputConfig) throws Exception;
  
  /**
   * Starts the monitoring of the input configurations, asynchronously. Will be used only in LOGFEEDER mode.
   * 
   * @param configMonitor The input config monitor to call in case of a config change.
   * @throws Exception
   */
  void monitorInputConfigChanges(InputConfigMonitor configMonitor) throws Exception;
}
