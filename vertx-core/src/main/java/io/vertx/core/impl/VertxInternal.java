/*
 * Copyright (c) 2011-2013 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.core.impl;


import io.netty.channel.EventLoopGroup;
import io.vertx.core.Handler;
import io.vertx.core.http.impl.HttpServerImpl;
import io.vertx.core.net.impl.NetServerImpl;
import io.vertx.core.net.impl.ServerID;
import io.vertx.core.spi.cluster.VertxSPI;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * This interface provides services for vert.x core internal use only
 * It is not part of the public API and should not be used by
 * developers creating vert.x applications
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public interface VertxInternal extends VertxSPI {

  EventLoopGroup getEventLoopGroup();

  ExecutorService getWorkerPool();

  ContextImpl getOrCreateContext();

  Map<ServerID, HttpServerImpl> sharedHttpServers();

  Map<ServerID, NetServerImpl> sharedNetServers();

	/**
	 * Get the current context
	 * @return the context
	 */
	ContextImpl getContext();

	/**
	 * Set the current context
	 */
  void setContext(ContextImpl context);

  /**
   * @return event loop context
   */
  EventLoopContext createEventLoopContext();

  /**
   * @return worker loop context
   */
  ContextImpl createWorkerContext(boolean multiThreaded);

  void simulateKill();

  Deployment getDeployment(String deploymentID);

  void failoverCompleteHandler(Handler<Boolean> failoverCompleteHandler);

  boolean isKilled();

  void failDuringFailover(boolean fail);

  String getNodeID();
}
