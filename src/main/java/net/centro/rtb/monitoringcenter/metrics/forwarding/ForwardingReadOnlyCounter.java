/*
 * Copyright 2016 Centro, Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.centro.rtb.monitoringcenter.metrics.forwarding;

import com.codahale.metrics.Counter;
import com.google.common.base.Preconditions;

public class ForwardingReadOnlyCounter extends Counter {
    private MetricProvider<Counter> metricProvider;

    public ForwardingReadOnlyCounter(final Counter delegate) {
        Preconditions.checkNotNull(delegate);
        this.metricProvider = new MetricProvider<Counter>() {
            @Override
            public Counter get() {
                return delegate;
            }
        };
    }

    public ForwardingReadOnlyCounter(MetricProvider<Counter> metricProvider) {
        Preconditions.checkNotNull(metricProvider);
        this.metricProvider = metricProvider;
    }

    @Override
    public void inc() {
        throw new UnsupportedOperationException("Operation is not allowed for a read-only counter");
    }

    @Override
    public void inc(long n) {
        throw new UnsupportedOperationException("Operation is not allowed for a read-only counter");
    }

    @Override
    public void dec() {
        throw new UnsupportedOperationException("Operation is not allowed for a read-only counter");
    }

    @Override
    public void dec(long n) {
        throw new UnsupportedOperationException("Operation is not allowed for a read-only counter");
    }

    @Override
    public long getCount() {
        return metricProvider.get().getCount();
    }
}
