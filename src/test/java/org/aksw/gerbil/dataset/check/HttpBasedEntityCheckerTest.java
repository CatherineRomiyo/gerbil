package org.aksw.gerbil.dataset.check;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HttpBasedEntityCheckerTest {

    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> testConfigs = new ArrayList<Object[]>();
        // DBpedia examples
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Berlin", true });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Joe_DeAngelo", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Carol_Tome", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Joe_DeAngelo", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Home_Depot_Supply", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Claudio_X._Gonzales", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Milledge_A._Hart_III", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/Jerry_Shields", false });
        testConfigs.add(new Object[] { "http://dbpedia.org/resource/James_Senn", false });

        // It does not seem to make sense to test using the German DBpedia,
        // since its server does not seem to be as reliable as expected.
        // testConfigs.add(new Object[] {
        // "http://de.dbpedia.org/resource/Berlin", true });
        // testConfigs.add(new Object[] {
        // "http://de.dbpedia.org/resource/Joe_DeAngelo", false });

        // Wikipedia examples
        testConfigs.add(new Object[] { "http://wikipedia.org/wiki/Berlin", true });
        testConfigs.add(new Object[] { "http://wikipedia.org/wiki/Joe_DeAngelo", false });
        return testConfigs;
    }

    private String uri;
    private boolean expectedDecision;

    public HttpBasedEntityCheckerTest(String uri, boolean expectedDecision) {
        this.uri = uri;
        this.expectedDecision = expectedDecision;
    }

    @Test
    public void test() {
        HttpBasedEntityChecker checker = new HttpBasedEntityChecker();
        Assert.assertEquals(expectedDecision, checker.entityExists(uri));
        IOUtils.closeQuietly(checker);
    }
}