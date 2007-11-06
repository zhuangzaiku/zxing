/*
 * Copyright 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.common;

/**
 * <p>A simple, fast array of bits, represented compactly by an array of ints internally.</p>
 *
 * @author srowen@google.com (Sean Owen)
 */
public final class BitArray {

  private final int[] bits;

  public BitArray(int size) {
    int arraySize = size >> 5;
    if ((size & 0x1F) != 0) {
      arraySize++;
    }
    bits = new int[arraySize];
  }

  /**
   * @return true iff bit i is set
   */
  public boolean get(int i) {
    return (bits[i >> 5] & (1 << (i & 0x1F))) != 0;
  }

  /**
   * Sets bit i.
   */
  public void set(int i) {
    bits[i >> 5] |= 1 << (i & 0x1F);
  }

  public void setBulk(int i, int newBits) {
    bits[i >> 5] = newBits;
  }

  /**
   * Clears all bits.
   */
  public void clear() {
    int max = bits.length;
    for (int i = 0; i < max; i++) {
      bits[i] = 0;
    }
  }

  /**
   * @return underlying array of ints. The first element holds the first 32 bits, and the least
   *  significant bit is bit 0.
   */
  public int[] getBitArray() {
    return bits;
  }

}