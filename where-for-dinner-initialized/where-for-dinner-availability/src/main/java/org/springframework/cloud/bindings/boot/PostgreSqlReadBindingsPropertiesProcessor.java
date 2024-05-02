package org.springframework.cloud.bindings.boot;

import static org.springframework.cloud.bindings.boot.Guards.isTypeEnabled;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.bindings.Binding;
import org.springframework.cloud.bindings.Bindings;
import org.springframework.core.env.Environment;

/**
 * Spring cloud bindings doesn't currently support multiple connections properties for the same type.  This processor
 * defines a type of postgresql-ro which maps secret values to custom spring property names that Where For Dinner
 * uses for configuring a read only endpoint in the DBTransactionRouteConfig class.  This implementation effectively copies code from
 * the PostgreSqlBindingsPropertiesProcessor and uses prefixes of `spring.datasource.ro` and `spring.r2dbc.ro`
 * 
 * This implementation is intended as a stop gap, but potentially may be needed for a longer term.
 *
 */
public final class PostgreSqlReadBindingsPropertiesProcessor implements BindingsPropertiesProcessor {


    public static final String TYPE = "postgresql-ro";

    public static final String SSL_MODE = "sslmode";

    public static final String SSL_ROOT_CERT = "sslrootcert";

    public static final String OPTIONS = "options";
    public static final String SPRING_DATASOURCE_URL = "spring.datasource.ro.url";
    public static final String SPRING_R2DBC_URL = "spring.r2dbc.ro.url";

    @Override
    public void process(Environment environment, Bindings bindings, Map<String, Object> properties) {
        if (!isTypeEnabled(environment, TYPE)) {
            return;
        }

        bindings.filterBindings(TYPE).forEach(binding -> {
            MapMapper map = new MapMapper(binding.getSecret(), properties);

            //jdbc properties
            map.from("password").to("spring.datasource.ro.password");
            map.from("host", "port", "database").to(SPRING_DATASOURCE_URL,
                    (host, port, database) -> String.format("jdbc:postgresql://%s:%s/%s", host, port, database));

            String sslParam = buildSslModeParam(binding);
            String sslModeOptions = buildDbOptions(binding);
            if (!"".equals(sslParam) && !"".equals(sslModeOptions)) {
                sslModeOptions = sslParam + "&" + sslModeOptions;
            } else if (!"".equals(sslParam) ) {
                sslModeOptions = sslParam;
            }

            if (!"".equals(sslModeOptions)) {
                properties.put(SPRING_DATASOURCE_URL, properties.get(SPRING_DATASOURCE_URL) + "?" + sslModeOptions);
            }
            map.from("username").to("spring.datasource.ro.username");

            // jdbcURL takes precedence
            map.from("jdbc-url").to("spring.datasource.ro.url");

            properties.put("spring.datasource.ro.driver-class-name", "org.postgresql.Driver");

            //r2dbc properties
            map.from("password").to("spring.r2dbc.ro.password");
            map.from("host", "port", "database").to(SPRING_R2DBC_URL,
                    (host, port, database) -> String.format("r2dbc:postgresql://%s:%s/%s", host, port, database));
            if (!"".equals(sslModeOptions)) {
                properties.put(SPRING_R2DBC_URL, properties.get(SPRING_R2DBC_URL) + "?" + sslModeOptions);
            }
            map.from("username").to("spring.r2dbc.ro.username");

            // r2dbcURL takes precedence
            map.from("r2dbc-url").to("spring.r2dbc.ro.url");
        });
    }

    private String buildDbOptions(Binding binding) {
        String options = binding.getSecret().getOrDefault(OPTIONS, "");
        String crdbOption = "";
        List<String> dbOptions = new ArrayList<>();
        if (!options.equals("")) {
            String[] allOpts = options.split("&");
            for (String o : allOpts) {
                String[] keyval = o.split("=");
                if (keyval.length != 2 || keyval[0].length() == 0 || keyval[1].length() == 0) {
                    continue;
                }
                if (keyval[0].equals("--cluster")) {
                    crdbOption = keyval[0] + "=" + keyval[1];
                } else {
                    dbOptions.add("-c " + keyval[0] + "=" + keyval[1]);
                }
            }
        }
        String combinedOptions = crdbOption;
        if (dbOptions.size() > 0) {
            String otherOpts = String.join(" ", dbOptions);
            if (!combinedOptions.equals("")) {
                combinedOptions = combinedOptions + " " + otherOpts;
            } else {
                combinedOptions = otherOpts;
            }
        }
        if (!"".equals(combinedOptions)) {
            combinedOptions = "options=" + combinedOptions;
        }
        return combinedOptions;
    }

    private String buildSslModeParam(Binding binding) {
        //process ssl params
        //https://www.postgresql.org/docs/14/libpq-connect.html
        String sslmode = binding.getSecret().getOrDefault(SSL_MODE, "");
        String sslRootCert = binding.getSecret().getOrDefault(SSL_ROOT_CERT, "");
        StringBuilder sslparam = new StringBuilder();
        if (!"".equals(sslmode)) {
            sslparam.append(SSL_MODE).append("=").append(sslmode);
        }
        if (!"".equals(sslRootCert)) {
            if (!"".equals(sslmode)) {
                sslparam.append("&");
            }
            sslparam.append(SSL_ROOT_CERT).append("=")
                    .append(binding.getPath()).append(FileSystems.getDefault().getSeparator())
                    .append(sslRootCert);
        }
        return sslparam.toString();
    }
}
