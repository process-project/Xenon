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

package nl.esciencecenter.octopus.engine.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.UUID;

import nl.esciencecenter.octopus.jobs.JobDescription;
import nl.esciencecenter.octopus.jobs.Scheduler;

/**
 * @author Jason Maassen <J.Maassen@esciencecenter.nl>
 *
 */
public class JobImplementationTest {
    
    @org.junit.Test
    public void test_constructor0() throws Exception {
        JobDescription desc = new JobDescription();

        Scheduler s = new SchedulerImplementation("test", "id1", new URI("test:///"), new String [] { "testq" }, null, 
                null, true, true, true);     
        
        UUID uuid = UUID.randomUUID();
        
        new JobImplementation(desc, s, uuid, "id1", true, true);
    }
        
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test_constructor1() throws Exception {
        new JobImplementation(null, null, null, null, true, true);
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test_constructor2() throws Exception {
        JobDescription desc = new JobDescription();
        new JobImplementation(desc, null, null, null, true, true);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test_constructor3() throws Exception {
        JobDescription desc = new JobDescription();

        Scheduler s = new SchedulerImplementation("test", "id1", new URI("test:///"), new String [] { "testq" }, null, 
                null, true, true, true);     

        new JobImplementation(desc, s, null, null, true, true);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test_constructor4() throws Exception {
        JobDescription desc = new JobDescription();

        Scheduler s = new SchedulerImplementation("test", "id1", new URI("test:///"), new String [] { "testq" }, null, 
                null, true, true, true);     

        UUID uuid = UUID.randomUUID();

        new JobImplementation(desc, s, uuid, null, true, true);
    }

    @org.junit.Test
    public void test_getters_and_setters() throws Exception {
        
        JobDescription desc = new JobDescription();

        Scheduler s = new SchedulerImplementation("test", "id1", new URI("test:///"), new String [] { "testq" }, null, 
                null, true, true, true);     
        
        UUID uuid = UUID.randomUUID();
        
        JobImplementation ji = new JobImplementation(desc, s, uuid, "id1", true, true);

        assertEquals(s, ji.getScheduler());
        assertEquals(desc, ji.getJobDescription());
        assertEquals(uuid, ji.getUUID());
        assertEquals("id1", ji.getIdentifier());
        assertTrue(ji.isInteractive());
        assertTrue(ji.isOnline());
    } 
    
    @org.junit.Test
    public void test_toString() throws Exception {

        JobDescription desc = new JobDescription();

        Scheduler s = new SchedulerImplementation("test", "id1", new URI("test:///"), new String [] { "testq" }, null, 
                null, true, true, true);     
        
        UUID uuid = UUID.randomUUID();
        
        JobImplementation ji = new JobImplementation(desc, s, uuid, "id1", true, true);

        assertTrue(ji.toString().equals(
                "JobImplementation [identifier=id1, uuid=" + uuid +  ", scheduler=" + s + 
                ", description=" + desc + ", isInteractive=true, isOnline=true]"));
    }

    @org.junit.Test
    public void test_hashcode_equals() throws Exception {

        JobDescription desc = new JobDescription();

        Scheduler s = new SchedulerImplementation("test", "id1", new URI("test:///"), new String [] { "testq" }, null, 
                null, true, true, true);     
        
        UUID uuid = UUID.randomUUID();
        
        JobImplementation ji = new JobImplementation(desc, s, uuid, "id1", true, true);

        int hash = uuid.hashCode();
        
        assertTrue(hash == ji.hashCode());

        
        assertTrue(ji.equals(ji));
        assertFalse(ji.equals(null));
        assertFalse(ji.equals("AAP"));
        
        JobImplementation ji2 = new JobImplementation(desc, s, uuid, "id1", true, true);

        assertTrue(ji.equals(ji2));
        
        JobImplementation ji3 = new JobImplementation(desc, s, UUID.randomUUID(), "id1", true, true);

        assertFalse(ji.equals(ji3));
    }
    
}

