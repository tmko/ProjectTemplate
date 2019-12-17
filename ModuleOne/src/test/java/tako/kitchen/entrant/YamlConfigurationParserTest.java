package tako.kitchen.entrant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static tako.kitchen.entrant.YamlConfigurationParser.toPojo;

public class YamlConfigurationParserTest {

    public <T> T loadAsResource(String inputYaml, Class<T> yamlPojoClass) throws IOException {
        ClassLoader thisLoader = this.getClass().getClassLoader();

        try (InputStream yamlInput = thisLoader.getResourceAsStream(inputYaml)) {
            return toPojo(yamlInput, yamlPojoClass);
        }
    }

    @Test
    public void simpleTest() throws IOException {
        /* Given */
        SampleConfigYamlPojo pojo;

        /* When */
        pojo = loadAsResource("sample.yaml", SampleConfigYamlPojo.class);

        /* Verify */
        Assert.assertThat(pojo, is(not(nullValue())));
        Assert.assertThat(pojo.getRetries(), is(2));
        Assert.assertThat(pojo.getHdfsDeletion().getMaxage(), is(50000));
        Assert.assertThat(pojo.getSftpConfigure().getCa().getHost(), is("sftp.some.host"));
        Assert.assertThat(pojo.getSftpConfigure().getCa().getSecretId(), is(nullValue()));
    }


    @Getter
    @Setter
    @ToString
    public static class SampleConfigYamlPojo {
        private SampleConfigYamlPojo.DeletaionConfiguration sftpDeletion;
        private SampleConfigYamlPojo.DeletaionConfiguration hdfsDeletion;
        private SampleConfigYamlPojo.CommonSftpConfigure sftpConfigure;
        private int retries;
        private String usCompanies;


        @Getter
        @Setter
        @ToString
        public static class DeletaionConfiguration {
            private int maxage;
        }

        @Getter
        @Setter
        @ToString
        public static class CommonSftpConfigure {
            private int timeout;
            private String serviceManagerURL;
            private SampleConfigYamlPojo.SftpRegionConfigure ca;
            private SampleConfigYamlPojo.SftpRegionConfigure us;
        }

        @Getter
        @Setter
        @ToString
        public static class SftpRegionConfigure {
            private int port;
            private String host;
            private Integer secretId; //This can be optional, so use Integer

            @ToString.Exclude
            private String password;
        }

    }
}
