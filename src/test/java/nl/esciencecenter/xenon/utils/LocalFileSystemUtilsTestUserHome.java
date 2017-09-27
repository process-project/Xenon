/*
 * Copyright 2013 Netherlands eScience Center
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
package nl.esciencecenter.xenon.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ProvideSystemProperty;

public class LocalFileSystemUtilsTestUserHome {

    @Rule
    public final ProvideSystemProperty p3 = new ProvideSystemProperty("user.home", "/foo/bar");

    @Test
    public void test_expand_tilde_withPath() throws Exception {
        // System.setProperty("user.home", "/foo/bar");
        String result = LocalFileSystemUtils.expandTilde("~/filesystem-test-fixture/links/file0");
        assertEquals("/foo/bar/filesystem-test-fixture/links/file0", result);
    }

    @Test
    public void test_expand_tilde() throws Exception {
        // System.setProperty("user.home", "/foo/bar");
        String result = LocalFileSystemUtils.expandTilde("~");
        assertEquals("/foo/bar", result);
    }

    @Test
    public void test_expand_tilde_null() throws Exception {
        // System.setProperty("user.home", "/foo/bar");
        String result = LocalFileSystemUtils.expandTilde(null);
        assertNull(result);
    }

    @Test
    public void test_expand_tilde_notilde() throws Exception {
        // System.setProperty("user.home", "/foo/bar");
        String result = LocalFileSystemUtils.expandTilde("/aap/noot");
        assertEquals("/aap/noot", result);
    }

    @Test
    public void test_expand_tilde_wrongspot() throws Exception {
        // System.setProperty("user.home", "/foo/bar");
        String result = LocalFileSystemUtils.expandTilde("/aap/noot/~/mies");
        assertEquals("/aap/noot/~/mies", result);
    }
}
