package com.exo1.exo1;

import org.springframework.jdbc.core.JdbcTemplate;

public class Utils {
    private JdbcTemplate jdbcTemplate;

    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW adresse_par_ville");
    }
}
