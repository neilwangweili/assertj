/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2013 the original author or authors.
 */
package org.assertj.guava.api;

import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;

import java.io.IOException;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import org.assertj.core.util.VisibleForTesting;

import com.google.common.io.ByteSource;

/**
 * Assertions for Guava {@link ByteSource}.
 *
 * @author Andrew Gaul
 */
public class ByteSourceAssert extends AbstractAssert<ByteSourceAssert, ByteSource> {

  @VisibleForTesting
  Failures failures = Failures.instance();

  protected ByteSourceAssert(ByteSource actual) {
    super(actual, ByteSourceAssert.class);
  }

  // visible for test
  protected ByteSource getActual() {
    return actual;
  }

  /**
   * Verifies that the actual {@link ByteSource} contains has the same content as the provided one.<br>
   * <p>
   * Example :
   *
   * <pre>
   * ByteSource actual = ByteSource.wrap(new byte[1]);
   * ByteSource other = ByteSource.wrap(new byte[1]);
   *
   * assertThat(actual).hasSameContentAs(other);
   * </pre>
   *
   * @param other ByteSource to compare against.
   * @return this {@link ByteSourceAssert} for assertions chaining.
   * @throws IOException    if {@link ByteSource#contentEquals} throws one.
   * @throws AssertionError if the actual {@link ByteSource} is {@code null}.
   * @throws AssertionError if the actual {@link ByteSource} does not contain the same content.
   */
  public ByteSourceAssert hasSameContentAs(ByteSource other) throws IOException {
    Objects.instance().assertNotNull(info, actual);
    if (!actual.contentEquals(other)) throw failures.failure(info, shouldBeEqual(actual, other));
    return this;
  }

  /**
   * Verifies that the actual {@link ByteSource} is empty.
   * <p>
   * Example :
   *
   * <pre>
   * ByteSource actual = ByteSource.wrap(new byte[0]);
   *
   * assertThat(actual).isEmpty();
   * </pre>
   *
   * @throws IOException    if {@link ByteSource#isEmpty} throws one.
   * @throws AssertionError if the actual {@link ByteSource} is {@code null}.
   * @throws AssertionError if the actual {@link ByteSource} is not empty.
   */
  public void isEmpty() throws IOException {
    Objects.instance().assertNotNull(info, actual);
    if (!actual.isEmpty()) throw failures.failure(info, shouldBeEmpty(actual));
  }

  /**
   * Verifies that the size of the actual {@link ByteSource} is equal to the given one.
   * <p>
   * Example :
   *
   * <pre>
   * ByteSource actual = ByteSource.wrap(new byte[9]);
   *
   * assertThat(actual).hasSize(9);
   * </pre>
   *
   * @param expectedSize the expected size of actual {@link ByteSource}.
   * @return this {@link ByteSourceAssert} for assertions chaining.
   * @throws IOException    if {@link com.google.common.io.ByteSource#size()} throws one.
   * @throws AssertionError if the actual {@link ByteSource} is {@code null}.
   * @throws AssertionError if the number of values of the actual {@link ByteSource} is not equal to the given one.
   */
  public ByteSourceAssert hasSize(long expectedSize) throws IOException {
    Objects.instance().assertNotNull(info, actual);
    long sizeOfActual = actual.size();
    if (sizeOfActual != expectedSize) throw failures.failure(info, shouldHaveSize(actual, sizeOfActual, expectedSize));
    return this;
  }

}
